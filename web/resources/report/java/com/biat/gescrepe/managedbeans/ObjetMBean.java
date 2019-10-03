package com.biat.gescrepe.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.biat.gescrepe.entities.Objet;
import com.biat.gescrepe.entities.Typecredit;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Ajax;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.biat.gescrepe.services.ObjetServiceBeanLocal;
import com.biat.gescrepe.services.TypecreditServiceBeanLocal;

/**
 * @author MENSAH Y.A.O.D 
 */
@ManagedBean
@ViewScoped
public class ObjetMBean {

    @EJB
    private ObjetServiceBeanLocal service;
    private List<Objet> dataList = new ArrayList<Objet>();
    private Objet selectedObject;
    private Objet formObject;
    @EJB
    private TypecreditServiceBeanLocal serviceTypecredit;
    private List<Typecredit> dataListTypecredit = new ArrayList<>();

    private String message;
    private Boolean desactiverBoutonSuppr = true, desactiverCode;
    private Integer index;

    /**
     * Constructeur
     */
    public ObjetMBean() {
        this.selectedObject = new Objet();
        this.formObject = new Objet();
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
        System.out.println("rentrer");
        try {
        MessageBean m=new MessageBean();
            if (this.selectedObject != null) {
                this.service.modifier(formObject);
                dataList.set(index, formObject);
                m.addMessageWarn("modifié avec succès");
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
                this.service.supprimer(selectedObject.getIdObjet());
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
        formObject = new Objet();
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

    public Objet getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Objet selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Typecredit> getDataListTypecredit() {
        dataListTypecredit = serviceTypecredit.selectionnerTout();
        return dataListTypecredit;
    }

    public void setDataListTypecredit(List<Typecredit> dataListTypecredit) {
        this.dataListTypecredit = dataListTypecredit;
    }
    public List<Objet> getDataList() {
        return dataList;
    }

    public void setDataList(List<Objet> listeType) {
        this.dataList = listeType;
    }

    /**
     * @return the formObject
     */
    public Objet getFormObject() {
        return formObject;
    }

    /**
     * @param formObject the formObject to set
     */
    public void setFormObject(Objet formObject) {
        this.formObject = formObject;
    }
}