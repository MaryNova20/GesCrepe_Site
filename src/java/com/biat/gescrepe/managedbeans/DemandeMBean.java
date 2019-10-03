package com.biat.gescrepe.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.biat.gescrepe.entities.Demande;
import com.biat.gescrepe.entities.Garantie;
import com.biat.gescrepe.entities.Typecredit;
import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.entities.Encours;
import com.biat.gescrepe.entities.Objet;
import com.biat.gescrepe.reports.ConteneurRequeteManagedBean;
import com.biat.gescrepe.reports.ExportManagedBean;
import com.biat.gescrepe.reports.Suividemande;
import com.biat.gescrepe.reports.aprobSystematiq;
import com.biat.gescrepe.reports.demandeComite;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Ajax;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.biat.gescrepe.services.DemandeServiceBeanLocal;
import com.biat.gescrepe.services.GarantieServiceBeanLocal;
import com.biat.gescrepe.services.TypecreditServiceBeanLocal;
import com.biat.gescrepe.services.EmployeServiceBeanLocal;
import com.biat.gescrepe.services.ObjetServiceBeanLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class DemandeMBean implements Serializable {

    @EJB
    private DemandeServiceBeanLocal service;
    private List<Demande> dataList = new ArrayList<Demande>();
    private Demande selectedObject;
    private Demande formObject;
    private Date date1;
    private Date date2;
  
    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    @ManagedProperty(value = "#{globalExportManagedBean}")
    private ExportManagedBean exportManagedBean;
    
    private List<Suividemande> suividemandelist=new ArrayList<Suividemande>();
    private List<demandeComite> demandeComitelist=new ArrayList<demandeComite>();
    private List<aprobSystematiq> aprobSystematiqlist=new ArrayList<aprobSystematiq>();
    
    
    @EJB
    private GarantieServiceBeanLocal serviceGarantie;
    @EJB
    private TypecreditServiceBeanLocal serviceTypecredit;
    @EJB
    private ObjetServiceBeanLocal serviceobjet;
    private Objet selobjetcredit;
    @EJB
    private EmployeServiceBeanLocal serviceEmploye;
    private Employe selemploye;
    private List<Encours> listencours = new ArrayList<Encours>();
    private List<Encours> sellistencours = new ArrayList<Encours>();

    private List<Garantie> dataListGarantie = new ArrayList<>();
    private List<Typecredit> dataListTypecredit = new ArrayList<>();
    private List<Objet> dataListObjet = new ArrayList<>();
    private List<Employe> dataListEmploye = new ArrayList<>();

    private String message;
    private Boolean desactiverBoutonSuppr = true, desactiverCode;
    private Integer index;
    private Date maxdate = new Date();
    private double quotitecessible = 0;
    private double quotiteatteinte = 0;
    private BigDecimal montantcessible = BigDecimal.ZERO;
    private BigDecimal salaireresiduel = BigDecimal.ZERO;
    private BigDecimal montantdemande;

    /**
     * Constructeur
     */
    public DemandeMBean() {
        this.selectedObject = new Demande();
        this.formObject = new Demande();
        montantdemande = BigDecimal.ZERO;
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
    public void comite() {
        BigDecimal unitairecomite;
        BigDecimal recuptotal;
        BigDecimal recuptotalsalaire;
        BigDecimal quotitecomite;
        BigDecimal salaireresiduele;
        if (formObject.getMontantCessible() != null) {
            unitairecomite = formObject.getMontantCessible().divide(BigDecimal.valueOf(selobjetcredit.getIdType().getDuree()), 2, RoundingMode.HALF_UP);
            //calcul du pourcentage reel
            recuptotal = unitairecomite.multiply(BigDecimal.valueOf(100));
            if (selemploye != null) {
                quotitecomite = recuptotal.divide(selemploye.getSalaire(), 2, RoundingMode.HALF_UP);

                formObject.setQuotiteAtteinte(quotitecomite.add(BigDecimal.valueOf(50)));
                recuptotalsalaire = selemploye.getSalaire().multiply(formObject.getQuotiteAtteinte());
                salaireresiduele = recuptotalsalaire.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

                System.out.println("quotite finale " + formObject.getQuotiteAtteinte());
                if (formObject.getQuotiteAtteinte().compareTo(BigDecimal.valueOf(100)) == 1) {
                    Ajax.oncomplete("PF('erreurdepassement').show()");

                } else {
                    quotiteatteinte = formObject.getQuotiteAtteinte().doubleValue();
                    salaireresiduel = selemploye.getSalaire().subtract(salaireresiduele);
                    formObject.setDecision("COMITE");
                    formObject.setIdObjet(selobjetcredit);
                    formObject.setMatricule(selemploye);
                    formObject.setQuotiteCessible(quotitecomite);
                }
            }
        }
    }

    public void montant() {
        BigDecimal salaireresidu;
        BigDecimal recu;
        BigDecimal unitaire;
        BigDecimal quotitepret = null;
        System.out.println("entrer");
        System.out.println("montant demande " + formObject.getMontantCessible());
        if (formObject.getMontantCessible() != null) {
            System.out.println("bon");
            listencours.removeAll(sellistencours);
            for (Encours en : listencours) {
                System.out.println(" encours " + en.getObjet());
            }
            quotiteatteinte();
            montantcessible();
            quotitecessible();
            if (quotiteatteinte == 0.0 && formObject.getMontantCessible().compareTo(montantcessible) == 1) {
                Ajax.oncomplete("PF('confirmComite').show()");
            } else {
                if (selemploye != null) {
                    recu = selemploye.getSalaire().multiply(BigDecimal.valueOf(quotiteatteinte));
                    salaireresidu = recu.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                    salaireresiduel = selemploye.getSalaire().subtract(salaireresidu);
                    if (selobjetcredit != null) {
                        unitaire = formObject.getMontantCessible().divide(BigDecimal.valueOf(selobjetcredit.getIdType().getDuree()), 2, RoundingMode.HALF_UP);
                        BigDecimal all;
                        all = unitaire.multiply(BigDecimal.valueOf(100));
                        quotitepret = all.divide(selemploye.getSalaire(), 2, RoundingMode.HALF_UP);

                    }
                    formObject.setIdObjet(selobjetcredit);
                    formObject.setMatricule(selemploye);
                    formObject.setQuotiteAtteinte(BigDecimal.valueOf(quotiteatteinte));
                    formObject.setQuotiteCessible(quotitepret);
                }
            }

        }
    }

    public void quotiteatteinte() {
        BigDecimal qtces = BigDecimal.ZERO;
        BigDecimal subsistence = BigDecimal.ZERO;
        BigDecimal totaleng = BigDecimal.ZERO;
        BigDecimal percent = BigDecimal.ZERO;
        BigDecimal unitaire = BigDecimal.ZERO;
        double quotite = 0;
        if (selemploye != null) {
            subsistence = selemploye.getSalaire().divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
            System.out.println("subsistence " + subsistence);
            totaleng = totengagement();
            montantdemande = formObject.getMontantCessible();
            if (formObject.getMontantCessible() != null) {
                unitaire = montantdemande.divide(BigDecimal.valueOf(selobjetcredit.getIdType().getDuree()), 2, RoundingMode.HALF_UP);
                System.out.println("montant demande" + montantdemande);
            }
            System.out.println("unitaire " + unitaire);
            BigDecimal totalencours = totaleng.add(unitaire);
            System.out.println("totalencours" + totalencours);
            if (subsistence.compareTo(totalencours) == 1 || subsistence.compareTo(totalencours) == 0) {
                System.out.println("peut prendre un prêt");
                // qtces = subsistence.subtract(totaleng);
                qtces = totalencours.multiply(BigDecimal.valueOf(50));
                System.out.println("recup " + qtces);
                percent = qtces.divide(subsistence, 2, RoundingMode.HALF_UP);
                System.out.println("percent " + percent);
                System.out.println("atteinte %" + percent);
                quotiteatteinte = percent.doubleValue();
                System.out.println("quotite " + quotiteatteinte);
            } else {
                System.out.println("erreur quotite atteinte");
            }

        }
    }

    public void montantcessible() {
        BigDecimal qtces = BigDecimal.ZERO;
        BigDecimal subsistence = BigDecimal.ZERO;
        BigDecimal totaleng = BigDecimal.ZERO;
        BigDecimal percent = BigDecimal.ZERO;
        double quotite = 0;
        if (selemploye != null) {
            subsistence = selemploye.getSalaire().divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
            System.out.println("subsistence " + subsistence);
            totaleng = totengagement();
//            qtces = unitaire.multiply(BigDecimal.valueOf(50));
            if (subsistence.compareTo(totaleng) == 1) {
                System.out.println("peut prendre un prêt");
                qtces = subsistence.subtract(totaleng);
                montantcessible = qtces.multiply(BigDecimal.valueOf(selobjetcredit.getIdType().getDuree()));
                System.out.println("monant cessible" + montantcessible);
            } else {
                System.out.println("peut pas prendre de prêt");
            }
        }
    }

    public void quotitecessible() {
        BigDecimal qtces = BigDecimal.ZERO;
        BigDecimal subsistence = BigDecimal.ZERO;
        BigDecimal totaleng = BigDecimal.ZERO;
        BigDecimal percent = BigDecimal.ZERO;
        double quotite = 0;
        if (selemploye != null) {
            subsistence = selemploye.getSalaire().divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
            System.out.println("subsistence " + subsistence);
            totaleng = totengagement();
//            qtces = unitaire.multiply(BigDecimal.valueOf(50));
            if (subsistence.compareTo(totaleng) == 1) {
                System.out.println("peut prendre un prêt");
                qtces = subsistence.subtract(totaleng);
                BigDecimal recup = qtces.multiply(BigDecimal.valueOf(50));
                System.out.println("recup " + recup);
                percent = recup.divide(subsistence, 2, RoundingMode.HALF_UP);
                System.out.println("percent " + percent);
                System.out.println(" %" + percent);
                quotitecessible = percent.doubleValue();
                System.out.println("quotite " + quotitecessible);
            } else {
                System.out.println("erreur quotite atteinte");
            }

        }

    }

    public BigDecimal unitairemontantcessible() {
        BigDecimal subsistence;
        BigDecimal unitaire = BigDecimal.ZERO;
        BigDecimal diff = BigDecimal.ZERO;
        if (selemploye != null) {
            subsistence = selemploye.getSalaire().divide(BigDecimal.valueOf(2));
            //System.out.println("subsistence " + subsistence);
            diff = subsistence.add(totengagement());
            //System.out.println("diff " + diff);
            unitaire = selemploye.getSalaire().subtract(subsistence.add(totengagement()));
            //  System.out.println(" Unitaire cessible " + unitaire);
        } else {
            System.out.println("erreur choisir employe");
        }
        return unitaire;

    }

    public BigDecimal totengagement() {
        BigDecimal totalengagement = BigDecimal.ZERO;
        if (!listencours.isEmpty()) {
            for (Encours engagement : listencours) {
                totalengagement = totalengagement.add(engagement.getMensualite());
            }
            System.out.println("Total des engagements " + totalengagement);
        }
        return totalengagement;
    }

    public void enregistrer() {
        MessageBean m = new MessageBean();

        if (formObject != null) {
            service.ajouter(formObject);
            System.out.println("ok erase");
            m.addMessageInfo("Demande enregistrée avec succès");
            cleanUp();

        }

    }

    public void modifierdem() {

        MessageBean m = new MessageBean();
                         //  this.service.ajouter(formObject);
        // dataList.add(formObject);

        if (selectedObject != null) {
            service.modifier(formObject);
            cleanUp();
            m.addMessageInfo("Garantie modifiée sur la demande");
        }
    }

    /**
     */
    public void supprimer() {
        try {
            MessageBean m = new MessageBean();
            if (this.selectedObject != null) {
                this.service.supprimer(selectedObject.getIdDemande());
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
        formObject = new Demande();
        quotiteatteinte = 0;
        montantcessible = BigDecimal.ZERO;
        salaireresiduel = BigDecimal.ZERO;
        quotitecessible = 0;
        selemploye = new Employe();
        selobjetcredit = new Objet();
        listencours = new ArrayList<>();
        sellistencours = new ArrayList<>();
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
        listencours = serviceEmploye.listencourspersonnel(selemploye);

    }

    public void rowSelectObjetcredit() {
        formObject = selectedObject;

    }

   public void suividemande() {
        suividemandelist = service.suividemandelist(date1, date2);
    }
     public void imprimersuividemande(){
          SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
          Map<String, Object> parametres = new HashMap();
          parametres.put("date1",format.format(date1));
          parametres.put("date2",format.format(date2));
          suividemande();
             this.conteneur.setListeEtat(suividemandelist);
       
//        this.parametres.put("para_logo", context.getRealPath("/resources/report/bia.png"));
        this.conteneur.setParametres(parametres);
         this.conteneur.setCompileFileName("situationdemande");
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
       this.exportManagedBean.setFormatDocument("PDF");
        this.exportManagedBean.exporter();
    }
     
     
     public void demandeComite() {
        demandeComitelist = service.demandeComitelist(date1, date2);
    }
     public void imprimerdemandeComite(){
          SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
          Map<String, Object> parametres = new HashMap();
          parametres.put("date1",format.format(date1));
          parametres.put("date2",format.format(date2));
          suividemande();
             this.conteneur.setListeEtat(demandeComitelist);
       
//        this.parametres.put("para_logo", context.getRealPath("/resources/report/bia.png"));
        this.conteneur.setParametres(parametres);
        this.conteneur.setCompileFileName("demandecomites");
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
        this.exportManagedBean.setFormatDocument("PDF");
        this.exportManagedBean.exporter();
    }
     
     
      public void aproSystematiq() {
        aprobSystematiqlist = service.aproSystematiquelist(date1, date2);
    }
     public void imprimeraproSystematiq(){
          SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
          Map<String, Object> parametres = new HashMap();
          parametres.put("date1",format.format(date1));
          parametres.put("date2",format.format(date2));
          suividemande();
             this.conteneur.setListeEtat(aprobSystematiqlist);
       
//        this.parametres.put("para_logo", context.getRealPath("/resources/report/bia.png"));
        this.conteneur.setParametres(parametres);
         this.conteneur.setCompileFileName("demandesystematique");
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
       this.exportManagedBean.setFormatDocument("PDF");
        this.exportManagedBean.exporter();
    }



    /**
     *
     */
    public void confirmerEnregistrer() {
        if (quotiteatteinte != 0.0) {
            System.out.println("ok");
            Ajax.oncomplete("PF('confirmEnregistre').show()");
        } else {
            Ajax.oncomplete("PF('confirmcalcul').show()");
        }

    }

    public void openpersonnel() {
        Ajax.oncomplete("PF('dlgRecherchpersonnel').show()");
    }

    public void openObjet() {
        Ajax.oncomplete("PF('dlgRecherobjet').show()");
    }

    public void openDemande() {
        Ajax.oncomplete("PF('dlgRecherchdemande').show()");
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

    public Demande getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Demande selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Garantie> getDataListGarantie() {
        dataListGarantie = serviceGarantie.selectionnerTout();
        return dataListGarantie;
    }

    public List<Typecredit> getDataListTypecredit() {
        dataListTypecredit = serviceTypecredit.selectionnerTout();
        return dataListTypecredit;
    }

    public List<Employe> getDataListEmploye() {
        dataListEmploye = serviceEmploye.selectionnerTout();
        return dataListEmploye;
    }

    public void setDataListGarantie(List<Garantie> dataListGarantie) {
        this.dataListGarantie = dataListGarantie;
    }

    public void setDataListTypecredit(List<Typecredit> dataListTypecredit) {
        this.dataListTypecredit = dataListTypecredit;
    }

    public void setDataListEmploye(List<Employe> dataListEmploye) {
        this.dataListEmploye = dataListEmploye;
    }

    public List<Demande> getDataList() {
        dataList=service.selectionnerTout();
        return dataList;
    }

    public void setDataList(List<Demande> listeType) {
        this.dataList = listeType;
    }

    /**
     * @return the formObject
     */
    public Demande getFormObject() {
        return formObject;
    }

    public void setFormObject(Demande formObject) {
        this.formObject = formObject;
    }

    public List<Objet> getDataListObjet() {
        dataListObjet = serviceobjet.selectionnerTout();
        return dataListObjet;
    }

    public void setDataListObjet(List<Objet> dataListObjet) {
        this.dataListObjet = dataListObjet;
    }

    public Employe getSelemploye() {
        return selemploye;
    }

    public void setSelemploye(Employe selemploye) {
        this.selemploye = selemploye;
    }

    public List<Encours> getListencours() {
        return listencours;
    }

    public void setListencours(List<Encours> listencours) {
        this.listencours = listencours;
    }

    public Objet getSelobjetcredit() {
        return selobjetcredit;
    }

    public void setSelobjetcredit(Objet selobjetcredit) {
        this.selobjetcredit = selobjetcredit;
    }

    public Date getMaxdate() {
        return maxdate;
    }

    public void setMaxdate(Date maxdate) {
        this.maxdate = maxdate;
    }

    public double getQuotitecessible() {
        return quotitecessible;
    }

    public void setQuotitecessible(double quotitecessible) {
        this.quotitecessible = quotitecessible;
    }

    public BigDecimal getMontantcessible() {
        return montantcessible;
    }

    public void setMontantcessible(BigDecimal montantcessible) {
        this.montantcessible = montantcessible;
    }

    public double getQuotiteatteinte() {
        return quotiteatteinte;
    }

    public void setQuotiteatteinte(double quotiteatteinte) {
        this.quotiteatteinte = quotiteatteinte;
    }

    public BigDecimal getMontantdemande() {
        return montantdemande;
    }

    public void setMontantdemande(BigDecimal montantdemande) {
        this.montantdemande = montantdemande;
    }

    public List<Encours> getSellistencours() {
        return sellistencours;
    }

    public void setSellistencours(List<Encours> sellistencours) {
        this.sellistencours = sellistencours;
    }

    public BigDecimal getSalaireresiduel() {
        return salaireresiduel;
    }

    public void setSalaireresiduel(BigDecimal salaireresiduel) {
        this.salaireresiduel = salaireresiduel;
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

    public List<Suividemande> getSuividemandelist() {
        return suividemandelist;
    }

    public void setSuividemandelist(List<Suividemande> suividemandelist) {
        this.suividemandelist = suividemandelist;
    }
    
     public List<demandeComite> getDemandeComitelist() {
        return demandeComitelist;
    }

    public void setDemandeComitelist(List<demandeComite> demandeComitelist) {
        this.demandeComitelist = demandeComitelist;
    }

    public List<aprobSystematiq> getAprobSystematiqlist() {
        return aprobSystematiqlist;
    }

    public void setAprobSystematiqlist(List<aprobSystematiq> aprobSystematiqlist) {
        this.aprobSystematiqlist = aprobSystematiqlist;
    }

}
