package com.biat.gescrepe.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.entities.Poste;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Ajax;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.biat.gescrepe.services.EmployeServiceBeanLocal;
import com.biat.gescrepe.services.PosteServiceBeanLocal;

/**
 * @author MENSAH Y.A.O.D 
 */
@ManagedBean
@ViewScoped
public class EmployeMBean {

    @EJB
    private EmployeServiceBeanLocal service;
    private List<Employe> dataList = new ArrayList<Employe>();
    private Employe selectedObject;
    private Employe formObject;
    @EJB
    private PosteServiceBeanLocal servicePoste;

    private List<Poste> dataListPoste = new ArrayList<>();

    private String message;
    private Boolean desactiverBoutonSuppr = true, desactiverCode;
    private Integer index;

    /**
     * Constructeur
     */
    public EmployeMBean() {
        this.selectedObject = new Employe();
        this.formObject = new Employe();
    }

    /**
     * Initialize the concrete controller bean.
     */
    @PostConstruct
    public final void loadFromDb() {
        dataList.clear();
        dataList.addAll(service.selectionnerTout());
    }

    /**
     *
     */
    public void enregistrer() {
        System.out.println("ça marche");
         System.out.println(selectedObject.getDateEmb());
        try {
        MessageBean m=new MessageBean();
            if (this.selectedObject != null) {
                System.out.println(formObject.getIdPoste().getLibPoste());
                this.service.modifier(formObject);
                dataList.set(index, formObject);
                m.addMessageWarn("modifié avec succes");
            } else {
              this.service.ajouter(formObject);
                dataList.add(formObject);
                m.addMessageWarn("enregistré avec succès");
            }

            cleanUp();

        } catch (Exception ex) {
        }
    }

    /**
     */
    public void supprimer() {
        try {
        MessageBean m=new MessageBean();
            if (this.selectedObject != null) {
                this.service.supprimer(selectedObject.getMatricule());
                this.dataList.remove(selectedObject);
                m.addMessageWarn("supprimé avec succès");
                cleanUp();
            } else {
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Exception: " + ex.getMessage());
        }
    }

    /**
     */
    public void rafraichir() {
        MessageBean m=new MessageBean();
        loadFromDb();
        cleanUp();
   m.addMessageWarn("liste raffraichie");
    }

    /**
     * Efface le formulaire
     */
    public void effacer() {
        cleanUp();
    }

    /**
     */
    private void cleanUp() {
        formObject = new Employe();
        this.selectedObject = null;
        this.desactiverCode = false;
        this.desactiverBoutonSuppr = true;
    }

    /**
     */
    public void rowSelect() {
        formObject = selectedObject;
        this.desactiverCode = true;
        this.index = this.dataList.indexOf(this.selectedObject);
        this.desactiverBoutonSuppr = false;
    }

    /**
     *
     */
    public void confirmerEnregistrer() {
    Ajax.oncomplete("PF('confirmEnregistre').show()");
    }

    /**
     */
    public void confirmeSuppression() {
   Ajax.oncomplete("PF('confirmSuppression').show()");
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getDesactiverCode() {
        return desactiverCode;
    }

    public void setDesactiverCode(Boolean desactiverCode) {
        this.desactiverCode = desactiverCode;
    }

    public Boolean getDesactiverBoutonSuppr() {
        return desactiverBoutonSuppr;
    }

    public void setDesactiverBoutonSuppr(Boolean desactiverBoutonM) {
        this.desactiverBoutonSuppr = desactiverBoutonM;
    }

    public Employe getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Employe selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Poste> getDataListPoste() {
        dataListPoste = servicePoste.selectionnerTout();
        return dataListPoste;
    }

    public void setDataListPoste(List<Poste> dataListPoste) {
        this.dataListPoste = dataListPoste;
    }
    public List<Employe> getDataList() {
        return dataList;
    }

    public void setDataList(List<Employe> listeType) {
        this.dataList = listeType;
    }

    /**
     * @return the formObject
     */
    public Employe getFormObject() {
        return formObject;
    }

    /**
     * @param formObject the formObject to set
     */
    public void setFormObject(Employe formObject) {
        this.formObject = formObject;
    }
}