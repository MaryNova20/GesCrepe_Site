package com.biat.gescrepe.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.biat.gescrepe.entities.Poste;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Ajax;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.biat.gescrepe.services.PosteServiceBeanLocal;

/**
 * @author MENSAH Y.A.O.D 
 */
@ManagedBean
@ViewScoped
public class PosteMBean {

    @EJB
    private PosteServiceBeanLocal service;
    private List<Poste> dataList = new ArrayList<Poste>();
    private Poste selectedObject;
    private Poste formObject;


    private String message;
    private Boolean desactiverBoutonSuppr = true, desactiverCode;
    private Integer index;

    /**
     * Constructeur
     */
    public PosteMBean() {
        this.selectedObject = new Poste();
        this.formObject = new Poste();
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
        try {
        MessageBean m=new MessageBean();
            if (this.selectedObject != null) {
                this.service.modifier(formObject);
                dataList.set(index, formObject);
                m.addMessageWarn("modifie avec succes");
            } else {
              this.service.ajouter(formObject);
                dataList.add(index, formObject);
                m.addMessageWarn("enregistre avec succes");
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
                this.service.supprimer(selectedObject.getIdPoste());
                this.dataList.remove(selectedObject);
                m.addMessageWarn("supprimer avec succes");
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
        formObject = new Poste();
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

    public Poste getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Poste selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Poste> getDataList() {
        return dataList;
    }

    public void setDataList(List<Poste> listeType) {
        this.dataList = listeType;
    }

    /**
     * @return the formObject
     */
    public Poste getFormObject() {
        return formObject;
    }

    /**
     * @param formObject the formObject to set
     */
    public void setFormObject(Poste formObject) {
        this.formObject = formObject;
    }
}