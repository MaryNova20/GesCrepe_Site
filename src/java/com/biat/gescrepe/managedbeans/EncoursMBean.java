
package com.biat.gescrepe.managedbeans;

import com.biat.gescrepe.entities.Demande;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.biat.gescrepe.entities.Encours;
import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.reports.ConteneurRequeteManagedBean;
import com.biat.gescrepe.reports.ExportManagedBean;
import com.biat.gescrepe.reports.FicheComite;
import com.biat.gescrepe.reports.SubFour;
import com.biat.gescrepe.reports.SubThree;
import com.biat.gescrepe.reports.Submain;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Ajax;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.biat.gescrepe.services.EncoursServiceBeanLocal;
import com.biat.gescrepe.services.EmployeServiceBeanLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author MENSAH Y.A.O.D 
 */
@ManagedBean
@ViewScoped
public class EncoursMBean implements Serializable{

    @EJB
    private EncoursServiceBeanLocal service;
    private List<Encours> dataList = new ArrayList<Encours>();
    private Encours selectedObject;
    private Encours formObject;
   
    
    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    @ManagedProperty(value = "#{globalExportManagedBean}")
    private ExportManagedBean exportManagedBean;
    
    //private List<FicheComite> comitelist=new ArrayList<FicheComite>();
     private List<Submain> comitelist=new ArrayList<>();
    private String nomprenom;
    private String datembauche;
    private String matricule;
    private String poste;
    private String indiceclient;
    private String salaire;
    private String subsistence;
   
     private List<SubFour> list4= new ArrayList<>();
     private List<SubThree> list3= new ArrayList<>();
    private List<Demande> dataListDemande = new ArrayList<>();
    
    @EJB
    private EmployeServiceBeanLocal serviceEmploye;

    private List<Employe> dataListEmploye = new ArrayList<>();
    private Employe selemploye;
    private Employe ficheFormObject;
    private String message;
    private Boolean desactiverBoutonSuppr = true, desactiverCode;
    private Integer index;
    private Integer indexdem;
    private  List<Object> listessai;

    /**
     * Constructeur
     */
    public EncoursMBean() {
        this.selectedObject = new Encours();
        this.formObject = new Encours();
        this.selemploye= new Employe();
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
                dataList.add(formObject);
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
                this.service.supprimer(selectedObject.getIdEncours());
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
        formObject = new Encours();
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
    
    public void rowSelectNew() {
        ficheFormObject = selemploye;
        this.desactiverCode = true;
        this.index = this.dataListEmploye.indexOf(this.selemploye);
       // this.indexdem = this.dataListDemande.indexOf(this.selemploye.getMatricule());
        System.out.println(selemploye.getNom());
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
    
    public void openpersonnel() {
        Ajax.oncomplete("PF('dlgRecherchpersonnelNew').show()");
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

    public Encours getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Encours selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIndexdem() {
        return indexdem;
    }

    public void setIndexdem(Integer indexdem) {
        this.indexdem = indexdem;
    }

    public List<Demande> getDataListDemande() {
        return dataListDemande;
    }

    public void setDataListDemande(List<Demande> dataListDemande) {
        this.dataListDemande = dataListDemande;
    }

    public List<SubFour> getList4() {
        return list4;
    }

    public void setList4(List<SubFour> list4) {
        this.list4 = list4;
    }

    public List<SubThree> getList3() {
        return list3;
    }

    public void setList3(List<SubThree> list3) {
        this.list3 = list3;
    }

    public List<Submain> getComitelist() {
        return comitelist;
    }

    public void setComitelist(List<Submain> comitelist) {
        this.comitelist = comitelist;
    }
    
    
    
    public Employe getSelemploye() {
        return selemploye;
    }

    public void setSelemploye(Employe selemploye) {
        this.selemploye = selemploye;
    }

    public Employe getFicheFormObject() {
        return ficheFormObject;
    }

    public void setFicheFormObject(Employe ficheFormObject) {
        this.ficheFormObject = ficheFormObject;
    }

    public String getNomprenom() {
        return nomprenom;
    }

    public void setNomprenom(String nomprenom) {
        this.nomprenom = nomprenom;
    }

    public String getDatembauche() {
        return datembauche;
    }

    public void setDatembauche(String datembauche) {
        this.datembauche = datembauche;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getIndiceclient() {
        return indiceclient;
    }

    public void setIndiceclient(String indiceclient) {
        this.indiceclient = indiceclient;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getSubsistence() {
        return subsistence;
    }

    public void setSubsistence(String subsistence) {
        this.subsistence = subsistence;
    }

    
    public ConteneurRequeteManagedBean getConteneur() {
        return conteneur;
    }

    public void setConteneur(ConteneurRequeteManagedBean conteneur) {
        this.conteneur = conteneur;
    }

    public ExportManagedBean getExportManagedBean() {
        return exportManagedBean;
    }

    public void setExportManagedBean(ExportManagedBean exportManagedBean) {
        this.exportManagedBean = exportManagedBean;
    }

//    public List<FicheComite> getComitelist() {
//        return comitelist;
//    }
//
//    public void setComitelist(List<FicheComite> comitelist) {
//        this.comitelist = comitelist;
//    }
    
    

    public List<Employe> getDataListEmploye() {
        dataListEmploye = serviceEmploye.selectionnerTout();
        return dataListEmploye;
    }

    public void setDataListEmploye(List<Employe> dataListEmploye) {
        this.dataListEmploye = dataListEmploye;
    }
    public List<Encours> getDataList() {
        return dataList;
    }

    public void setDataList(List<Encours> listeType) {
        this.dataList = listeType;
    }

    /**
     * @return the formObject
     */
    public Encours getFormObject() {
        return formObject;
    }

    /**
     * @param formObject the formObject to set
     */
    public void setFormObject(Encours formObject) {
        this.formObject = formObject;
    }
    
    
     public void ficheComite() {
        comitelist = service.comiteList(selemploye);
        list3= service.list3(selemploye);
        list4= service.list4(selemploye);
    }
     public void imprimerFicheComite(){
          System.out.println("imprimerFicheComite");
          ficheComite();
          Map<String, Object> parametres = new HashMap();
          parametres.put("nomprenom", selemploye.getNom()+" "+ selemploye.getPrenom());
          parametres.put("datembauche", selemploye.getDateEmb().toString());
          parametres.put("matricule",(selemploye.getMatricule()).toString());
          parametres.put("poste", selemploye.getIdPoste().getLibPoste());
          parametres.put("indiceclient", selemploye.getIndice().toString());
          parametres.put("salaire", selemploye.getSalaire().toString());
          parametres.put("subsistence", BigDecimal.ONE.toString());
         // ficheComite();
         
           parametres.put("datasourcemain", new JRBeanCollectionDataSource(comitelist));
           parametres.put("datasource2", new JRBeanCollectionDataSource(list3));
           parametres.put("datasource3", new JRBeanCollectionDataSource(list4));
           
         
          System.out.println(selemploye.getIdPoste().getLibPoste());
        
          listessai=new ArrayList<>();
          this.conteneur.setListeEtat(listessai);
       
//        this.parametres.put("para_logo", context.getRealPath("/resources/report/bia.png"));
        this.conteneur.setParametres(parametres);
        this.conteneur.setCompileFileName("fichecomite");
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
       //this.exportManagedBean.setFormatDocument("PDF");
        this.exportManagedBean.exporter();
         
          
    }

    public List<Object> getListessai() {
        return listessai;
    }

    public void setListessai(List<Object> listessai) {
        this.listessai = listessai;
    }

   

   
     
     
}