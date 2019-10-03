package com.biat.gescrepe.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.biat.gescrepe.entities.Decaissement;
import com.biat.gescrepe.entities.Demande;
import com.biat.gescrepe.reports.ConteneurRequeteManagedBean;
import com.biat.gescrepe.reports.ExportManagedBean;
import com.biat.gescrepe.reports.Remontees;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Ajax;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.biat.gescrepe.services.DecaissementServiceBeanLocal;
import com.biat.gescrepe.services.DemandeServiceBeanLocal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedProperty;

/**
 * @author MENSAH Y.A.O.D
 */
@ManagedBean
@ViewScoped
public class DecaissementMBean {

    @EJB
    private DecaissementServiceBeanLocal service;
    private List<Decaissement> dataList = new ArrayList<Decaissement>();
    private Decaissement selectedObject;
    private Decaissement formObject;
    
     private Date date1;
    private Date date2;
    
     @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    @ManagedProperty(value = "#{globalExportManagedBean}")
    private ExportManagedBean exportManagedBean;
     private List<Remontees> remontelist=new ArrayList<Remontees>();
            
    
    
    
    @EJB
    private DemandeServiceBeanLocal serviceDemande;
    private Demande seldemande;

    private List<Demande> dataListDemande = new ArrayList<>();

    private String message;
    private Boolean desactiverBoutonSuppr = true, desactiverCode;
    private Integer index;
    private  Date maxdate = new Date();
    private  Date mindate;

    
    /**
     * Constructeur
     */
    public DecaissementMBean() {
        this.selectedObject = new Decaissement();
        this.formObject = new Decaissement();
        seldemande = new Demande();
        mindate = seldemande.getDateDemande();
        System.out.println("mindate "+ mindate);
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
    public void test(){
        mindate=seldemande.getDateDemande();
        System.out.println("date "+formObject.getDateDecaissement());
        System.out.println("min date "+mindate);
    }
    public void enregistrer() {
        System.out.println("rentre");
        MessageBean m = new MessageBean();
        if (formObject != null) {
            formObject.setIdDemande(seldemande);
            this.service.ajouter(formObject);
            formObject.setDateDecaissement(new Date());
            dataList.add(formObject);
            m.addMessageWarn("enregistré avec succès");
            cleanUp();
        }
    }

    /**
     */
    public void supprimer() {
        try {
            MessageBean m = new MessageBean();
            if (this.selectedObject != null) {
                this.service.supprimer(selectedObject.getIdDecaissement());
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
        MessageBean m = new MessageBean();
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
        formObject = new Decaissement();
        this.selectedObject = null;
        this.desactiverCode = false;
        this.desactiverBoutonSuppr = true;
    }

    /**
     */
    public void rowSelect() {
        formObject = selectedObject;
        formObject.setMontantDecaisse(seldemande.getMontantCessible());
        this.desactiverCode = true;
        this.index = this.dataList.indexOf(this.selectedObject);
        this.desactiverBoutonSuppr = false;
    }
    
    public void Remonte() {
        System.out.println("bon");
       remontelist = service.Remontelist(date1, date2);
    }
     public void imprimerRemontees(){
          SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
          Map<String, Object> parametres = new HashMap();
          parametres.put("date1",format.format(date1));
          parametres.put("date2",format.format(date2));
          Remonte();
             this.conteneur.setListeEtat(remontelist);
       
//        this.parametres.put("para_logo", context.getRealPath("/resources/report/bia.png"));
        this.conteneur.setParametres(parametres);
         this.conteneur.setCompileFileName("remontees");
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
       this.exportManagedBean.setFormatDocument("PDF");
        this.exportManagedBean.exporter();
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

    public Decaissement getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Decaissement selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Demande> getDataListDemande() {
        dataListDemande = serviceDemande.demandenondecaisse();
        return dataListDemande;
    }

    public void setDataListDemande(List<Demande> dataListDemande) {
        this.dataListDemande = dataListDemande;
    }

    public List<Decaissement> getDataList() {
        return dataList;
    }

    public void setDataList(List<Decaissement> listeType) {
        this.dataList = listeType;
    }
    
    public Date getMaxdate() {
        return maxdate;
    }

    public void setMaxdate(Date maxdate) {
        this.maxdate = maxdate;
    }
    
    
     public Date getMindate() {
        return mindate;
    }

    public void setMindate(Date mindate) {
        this.mindate = mindate;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public ExportManagedBean getExportManagedBean() {
        return exportManagedBean;
    }

    public void setExportManagedBean(ExportManagedBean exportManagedBean) {
        this.exportManagedBean = exportManagedBean;
    }

    public List<Remontees> getRemontelist() {
        return remontelist;
    }

    public void setRemontelist(List<Remontees> remontelist) {
        this.remontelist = remontelist;
    }
    

   public ConteneurRequeteManagedBean getConteneur() {
        return conteneur;
    }

    public void setConteneur(ConteneurRequeteManagedBean conteneur) {
        this.conteneur = conteneur;
    }
    
    
    

    
    

    /**
     * @return the formObject
     */
    public Decaissement getFormObject() {
        return formObject;
    }

    /**
     * @param formObject the formObject to set
     */
    public void setFormObject(Decaissement formObject) {
        this.formObject = formObject;
    }

    public Demande getSeldemande() {
        return seldemande;
    }

    public void setSeldemande(Demande seldemande) {
        this.seldemande = seldemande;
    }

    
}
