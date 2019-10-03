/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biat.gescrepe.managedbeans;

import com.admin.entities.Profil;
import com.admin.entities.ProfilMenuItem;
import com.admin.entities.SysLog;
import com.admin.services.ProfilMenuItemServiceBeanLocal;
import com.admin.services.ProfilServiceBeanLocal;
import com.admin.services.SysLogServiceBeanLocal;
import com.admin.services.TypeOperServiceBeanLocal;
import com.admin.web.beans.EmployeManagedBean;
import static com.admin.web.beans.EmployeManagedBean.codeCrypter;
import com.admin.web.beans.Util;
import com.biat.gescrepe.entities.Utilisateurs;
import com.biat.gescrepe.services.UtilisateursServiceBeanLocal;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Ajax;

/**
 *
 * @author Olivier-pc
 */
@ManagedBean
@SessionScoped
public class LoginManagedBean {

    @EJB
    private UtilisateursServiceBeanLocal UtilisateurService;
    private Utilisateurs connectutilisateur, formUtilisateur;
    private String login, password, userName;
    private String nouveauMotPasse;
    private String confirmerMotPasse;
    @EJB
    private ProfilServiceBeanLocal profilservice;
    private Profil selprofil;
    @EJB
    private SysLogServiceBeanLocal syslogservice;

    @EJB
    private TypeOperServiceBeanLocal typeoperationservice;

    @EJB
    private ProfilMenuItemServiceBeanLocal droitservice;
    private List<ProfilMenuItem> listdroits;
   
    
    private List<SysLog> listjournal =new ArrayList<SysLog>();
            

    //les booleens de menus
    private boolean menuadministrationGriser = false;
    private boolean menudonnebaseGriser = false;
    private boolean menudemandeGriser = false;
    private boolean menusuiviGriser = false;
    private boolean menueditionGriser = false;
    
    //les booleens des sous-menus
    private boolean userEditGriser = false;
    private boolean profilEditGriser = false;
    private boolean menuEditGriser = false;
    private boolean habilitationEditGriser = false;
    private boolean syslogEditGriser = false;
    private boolean garantieGRiser = false;
    private boolean objetCreditGriser = false;
    private boolean typeCreditGriser = false;
    private boolean nouvelleDemandeGriser = false;
    private boolean misaJourDemandeGriser = false;
    private boolean situationDemandeGriser = false;
    private boolean decaissementPretGriser = false;
    private boolean approbationSystematiqueGriser = false;
    private boolean demandeComiteGriser = false;
    private boolean situationDecaissementGriser = false;
    private boolean ficheComiteGriser = false;

    private int admin = 0;

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
        connectutilisateur = new Utilisateurs();
    }
    
    
    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public String connexion() throws NoSuchAlgorithmException, UnknownHostException {
        connectutilisateur = null;
        MessageBean m = new MessageBean();
        String page = "login";
        if ("admin".equals(login) && "Adminbia;".equals(password)) {
            //liste des menus à afficher
          menuadministrationGriser = true;
    menudonnebaseGriser = true;
    menudemandeGriser = true;
    menusuiviGriser = true;
    menueditionGriser = true;
   

    //les booleens des sous-menus
    userEditGriser = true;
    profilEditGriser = true;
    menuEditGriser = true;
    habilitationEditGriser = true;
    syslogEditGriser = true;
    garantieGRiser = true;
    objetCreditGriser = true;
    typeCreditGriser = true;
    nouvelleDemandeGriser = true;
    misaJourDemandeGriser = true;
    situationDemandeGriser = true;
    decaissementPretGriser = true;
    approbationSystematiqueGriser = true;
    demandeComiteGriser = true;
    situationDecaissementGriser = true;
    ficheComiteGriser = true;
    
            admin = 1;

            HttpSession session = Util.getsession();
            session.setAttribute("username", login);
            page = "/faces/pages/dashboard?faces-redirect=true";
            return page;
        } else {
            try {
                System.out.println("password "+password);
                password = EmployeManagedBean.codeCrypter(password);
                System.out.println("password crypté "+password);
                connectutilisateur = UtilisateurService.findUser(login, password);
                if (connectutilisateur != null) {
                    System.out.println("utilisateur trouvé");
                    //recuperation de ses droits
                    selprofil=connectutilisateur.getProfilId();
                    if(selprofil!=null){
                        listdroits=droitservice.selectionnerListeProfilMenuItem(selprofil.getProfilId());
                        System.out.println("profil actif");
                    }
                    if(!listdroits.isEmpty()){
                        for (ProfilMenuItem droit : listdroits) {
                            System.out.println("récupération des menus");
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("USERS")){
                                System.out.println("droit "+droit.getMenuItemId().getMenuItemDesc());
                                menuadministrationGriser=true;
                                userEditGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("PROFIL")){
                                menuadministrationGriser=true;
                                profilEditGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("HABILITATIONS")){
                                menuadministrationGriser=true;
                                habilitationEditGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("SYSLOG")){
                                menuadministrationGriser=true;
                                syslogEditGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("MENUS")){
                                menuadministrationGriser=true;
                                menuEditGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("GARANTIE")){
                                menudonnebaseGriser=true;
                                garantieGRiser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("OBJET CREDIT")){
                                menudonnebaseGriser=true;
                                objetCreditGriser=true;
                            }
                            
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("TYPE CREDIT")){
                                menudonnebaseGriser=true;
                                typeCreditGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("NOUVELLE DEMANDE")){
                                menudemandeGriser=true;
                               nouvelleDemandeGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("MISE A JOUR DES DEMANDES")){
                                menudemandeGriser=true;
                               misaJourDemandeGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("SITUATION DES DEMANDES")){
                                menusuiviGriser=true;
                               situationDemandeGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("DECAISSEMENT DES PRÊTS")){
                                menusuiviGriser=true;
                               decaissementPretGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("APPROBATION SYSTEMATIQUE")){
                                menueditionGriser=true;
                               approbationSystematiqueGriser=true;
                            }
                            
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("DEMANDES EN COMITE")){
                               menueditionGriser=true;
                               demandeComiteGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("SITUATION DES DECAISSEMENTS")){
                                menueditionGriser=true;
                              situationDecaissementGriser=true;
                            }
                            if(droit.getMenuItemId().getMenuItemDesc().equalsIgnoreCase("FICHE DE COMITE")){
                                menueditionGriser=true;
                              ficheComiteGriser=true;
                            }
                            
                     
                        }
                    }
                    
                    
                    
                    //  droitgestionnaire=true;
//                  if(!connectutilisateur.getGes().equalsIgnoreCase("CAIS")){
//                      droitportefeuille=false;
//                      droitcaisse=true;
//                  }

                    HttpSession session = Util.getsession();
                    session.setAttribute("username", login);
                    page = "/faces/pages/dashboard?faces-redirect=true";
                }
            } catch (Exception e) {
                m.addMessageWarn("erreur d'authentification");
                return page;
            }
             String ip = InetAddress.getLocalHost().getHostAddress();
                                System.out.println("ADRESSE SERVEUR " + ip);
                                String ip2 = getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
                                SysLog syslog=new SysLog();
                                syslog.setLogDesc("ADRESSE IP UTILISEE " + ip2);
                                syslog.setTypeOperId(typeoperationservice.typeoperselectionner("CONNEXION"));
                                syslog.setIduser(connectutilisateur);
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                syslog.setLogDate(new Date());
                                syslogservice.ajouter(syslog);
            return page;
        }
        
    }

    public String deconnexionuser() {
        HttpSession hs = Util.getsession();
        hs.invalidate();
        return "login";
    }

    public String checkdeconnexionimprimecommande() {
         if (admin == 1) {
            return "/faces/pages/doccde.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {

            return "/faces/pages/doccde.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }

    public String checkdeconnexionuser() {
        if (admin == 1) {
            return "/faces/pages/employe.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {

            return "/faces/pages/employe.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }

    }

    public String checkdeconnexionprofil() {
        if (admin == 1) {
            return "/faces/pages/profil.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/profil.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }

    }

    public String checkdeconnexionmenu() {
          if (admin == 1) {
            return "/faces/pages/menu.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/menu.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }

    }

    public String checkdeconnexiondroit() {
         if (admin == 1) {
            return "/faces/pages/droit.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/droit.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }

    }

    public String checkdeconnexionsyslog() {
         if (admin == 1) {
            return "/faces/pages/syslog.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/syslog.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }

    }


    public String misajourDemande() {
       if (admin == 1) {
            return "/faces/pages/majDemande.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/majDemande.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }

    public String newdecaissement() {
         if (admin == 1) {
            return "/faces/pages/newDecaissement.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/newDecaissement.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }
    
    public String demandecomite() {
         if (admin == 1) {
             System.out.println("entrer admin");
            return "/faces/pages/demandeComite.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/demandeComite.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }
    
    public String aprosystematiq() {
         if (admin == 1) {
             System.out.println("entrer admin");
            return "/faces/pages/aproSystematiq.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/aproSystematiq.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }

    

    public String newdemande() {
          if (admin == 1) {
                System.out.println("entrer admin");
            return "/faces/pages/newdemande.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/newdemande.xhtml";
        } else {
            HttpSession hs = Util.getsession();
           hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
          
    }

    public String journalES() {
        if (admin == 1) {
            return "/faces/pages/journal.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/journal.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }

    public String situationdemande() {
        if (admin == 1) {
            System.out.println("entrer");
            return "/faces/pages/situationDemande.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/situationDemande.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }
    
    
    public String situationdecaissement() {
        if (admin == 1) {
            System.out.println("entrer");
            return "/faces/pages/situationDecaissement.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/situationDecaissement.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }
    
    
     public String ficheComite() {
        if (admin == 1) {
            System.out.println("entrer");
            return "/faces/pages/FicheComite.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/FicheComite.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }

    
     public String garantie() {
        if (admin == 1) {
            System.out.println("entrer");
            return "/faces/pages/garantie.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/garantie.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }
     
     
    public String objetCredit() {
        if (admin == 1) {
            System.out.println("entrer");
            return "/faces/pages/objet.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/objet.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }
    
    
     public String typeCredit() {
        if (admin == 1) {
            System.out.println("entrer");
            return "/faces/pages/typecredit.xhtml";
        } else if (connectutilisateur.getLoginuser() != null) {
            return "/faces/pages/typecredit.xhtml";
        } else {
            HttpSession hs = Util.getsession();
            hs.invalidate();
            return "/faces/pages/login.xhtml";
        }
    }
  

  
  

    public String changerMotPasse() throws NoSuchAlgorithmException {
        MessageBean m = new MessageBean();

        if (nouveauMotPasse != null) {

            if (nouveauMotPasse.equals(confirmerMotPasse)) {
               String mp = codeCrypter(nouveauMotPasse);
                connectutilisateur.setPwduser(mp);
                UtilisateurService.modifier(connectutilisateur);

                HttpSession hs = Util.getsession();
                hs.invalidate();
                connectutilisateur = new Utilisateurs();
                m.addMessageInfo("mot de passe modifié avec succès");

            } else {
                FacesMessage msg = new FacesMessage("Erreur de validation", "Mot de passe incorrecte");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage("Erreur de validation", "Entrer le nouveau mot de passe");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "login?faces-redirect=true";

    }

    public void dialogchangerpasword() {
        Ajax.oncomplete("PF('confirmEnregistrech').show()");
    }

    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent cse) {
        MessageBean m = new MessageBean();
        formUtilisateur = UtilisateurService.findUser(userName, password);
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (formUtilisateur != null || ("Admin".equalsIgnoreCase(userName) && "olivier".equalsIgnoreCase(password))) {
            //  m.addMessageWarn("bienvenue désiré");
        } else {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/faces/pages/login?faces-redirect=true");
            //  m.addMessageWarn("vous tenter d'accéder a cet url"+viewId+" sans être authentifé");
        }
    }

    public String hashpassword(char[] c) {
        try {
            MessageDigest digsts = MessageDigest.getInstance("MD5");
            digsts.update(new String(c).getBytes("UTF8"));
            String resultmpd = new String(digsts.digest());
            return resultmpd;
        } catch (Exception e) {
            return "";
        }

    }

    public Utilisateurs getFormUtilisateur() {
        return formUtilisateur;
    }

    public void setFormUtilisateur(Utilisateurs formUtilisateur) {
        this.formUtilisateur = formUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNouveauMotPasse() {
        return nouveauMotPasse;
    }

    public void setNouveauMotPasse(String nouveauMotPasse) {
        this.nouveauMotPasse = nouveauMotPasse;
    }

    public String getConfirmerMotPasse() {
        return confirmerMotPasse;
    }

    public void setConfirmerMotPasse(String confirmerMotPasse) {
        this.confirmerMotPasse = confirmerMotPasse;
    }

    public Utilisateurs getConnectutilisateur() {
        return connectutilisateur;
    }

    public void setConnectutilisateur(Utilisateurs connectutilisateur) {
        this.connectutilisateur = connectutilisateur;
    }

    public Profil getSelprofil() {
        return selprofil;
    }

    public void setSelprofil(Profil selprofil) {
        this.selprofil = selprofil;
    }

    public List<ProfilMenuItem> getListdroits() {
        return listdroits;
    }

    public void setListdroits(List<ProfilMenuItem> listdroits) {
        this.listdroits = listdroits;
    }

    public boolean isMenuadministrationGriser() {
        return menuadministrationGriser;
    }

    public void setMenuadministrationGriser(boolean menuadministrationGriser) {
        this.menuadministrationGriser = menuadministrationGriser;
    }

    public boolean isMenudonnebaseGriser() {
        return menudonnebaseGriser;
    }

    public void setMenudonnebaseGriser(boolean menudonnebaseGriser) {
        this.menudonnebaseGriser = menudonnebaseGriser;
    }



    public boolean isUserEditGriser() {
        return userEditGriser;
    }

    public void setUserEditGriser(boolean userEditGriser) {
        this.userEditGriser = userEditGriser;
    }

    public boolean isProfilEditGriser() {
        return profilEditGriser;
    }

    public void setProfilEditGriser(boolean profilEditGriser) {
        this.profilEditGriser = profilEditGriser;
    }

    public boolean isMenuEditGriser() {
        return menuEditGriser;
    }

    public void setMenuEditGriser(boolean menuEditGriser) {
        this.menuEditGriser = menuEditGriser;
    }

    public boolean isHabilitationEditGriser() {
        return habilitationEditGriser;
    }

    public void setHabilitationEditGriser(boolean habilitationEditGriser) {
        this.habilitationEditGriser = habilitationEditGriser;
    }

    public boolean isSyslogEditGriser() {
        return syslogEditGriser;
    }

    public void setSyslogEditGriser(boolean syslogEditGriser) {
        this.syslogEditGriser = syslogEditGriser;
    }

    public UtilisateursServiceBeanLocal getUtilisateurService() {
        return UtilisateurService;
    }

    public void setUtilisateurService(UtilisateursServiceBeanLocal UtilisateurService) {
        this.UtilisateurService = UtilisateurService;
    }

    public ProfilServiceBeanLocal getProfilservice() {
        return profilservice;
    }

    public void setProfilservice(ProfilServiceBeanLocal profilservice) {
        this.profilservice = profilservice;
    }

    public SysLogServiceBeanLocal getSyslogservice() {
        return syslogservice;
    }

    public void setSyslogservice(SysLogServiceBeanLocal syslogservice) {
        this.syslogservice = syslogservice;
    }

    public TypeOperServiceBeanLocal getTypeoperationservice() {
        return typeoperationservice;
    }

    public void setTypeoperationservice(TypeOperServiceBeanLocal typeoperationservice) {
        this.typeoperationservice = typeoperationservice;
    }

    public ProfilMenuItemServiceBeanLocal getDroitservice() {
        return droitservice;
    }

    public void setDroitservice(ProfilMenuItemServiceBeanLocal droitservice) {
        this.droitservice = droitservice;
    }

    public boolean isMenudemandeGriser() {
        return menudemandeGriser;
    }

    public void setMenudemandeGriser(boolean menudemandeGriser) {
        this.menudemandeGriser = menudemandeGriser;
    }

    public boolean isMenusuiviGriser() {
        return menusuiviGriser;
    }

    public void setMenusuiviGriser(boolean menusuiviGriser) {
        this.menusuiviGriser = menusuiviGriser;
    }

    public boolean isMenueditionGriser() {
        return menueditionGriser;
    }

    public void setMenueditionGriser(boolean menueditionGriser) {
        this.menueditionGriser = menueditionGriser;
    }

    public boolean isGarantieGRiser() {
        return garantieGRiser;
    }

    public void setGarantieGRiser(boolean garantieGRiser) {
        this.garantieGRiser = garantieGRiser;
    }

    public boolean isObjetCreditGriser() {
        return objetCreditGriser;
    }

    public void setObjetCreditGriser(boolean objetCreditGriser) {
        this.objetCreditGriser = objetCreditGriser;
    }

    public boolean isTypeCreditGriser() {
        return typeCreditGriser;
    }

    public void setTypeCreditGriser(boolean typeCreditGriser) {
        this.typeCreditGriser = typeCreditGriser;
    }

    public boolean isNouvelleDemandeGriser() {
        return nouvelleDemandeGriser;
    }

    public void setNouvelleDemandeGriser(boolean nouvelleDemandeGriser) {
        this.nouvelleDemandeGriser = nouvelleDemandeGriser;
    }

    public boolean isMisaJourDemandeGriser() {
        return misaJourDemandeGriser;
    }

    public void setMisaJourDemandeGriser(boolean misaJourDemandeGriser) {
        this.misaJourDemandeGriser = misaJourDemandeGriser;
    }

    public boolean isSituationDemandeGriser() {
        return situationDemandeGriser;
    }

    public void setSituationDemandeGriser(boolean situationDemandeGriser) {
        this.situationDemandeGriser = situationDemandeGriser;
    }

    public boolean isDecaissementPretGriser() {
        return decaissementPretGriser;
    }

    public void setDecaissementPretGriser(boolean decaissementPretGriser) {
        this.decaissementPretGriser = decaissementPretGriser;
    }

    public boolean isApprobationSystematiqueGriser() {
        return approbationSystematiqueGriser;
    }

    public void setApprobationSystematiqueGriser(boolean approbationSystematiqueGriser) {
        this.approbationSystematiqueGriser = approbationSystematiqueGriser;
    }

    public boolean isDemandeComiteGriser() {
        return demandeComiteGriser;
    }

    public void setDemandeComiteGriser(boolean demandeComiteGriser) {
        this.demandeComiteGriser = demandeComiteGriser;
    }

    public boolean isSituationDecaissementGriser() {
        return situationDecaissementGriser;
    }

    public void setSituationDecaissementGriser(boolean situationDecaissementGriser) {
        this.situationDecaissementGriser = situationDecaissementGriser;
    }

    public boolean isFicheComiteGriser() {
        return ficheComiteGriser;
    }

    public void setFicheComiteGriser(boolean ficheComiteGriser) {
        this.ficheComiteGriser = ficheComiteGriser;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public List<SysLog> getListjournal() {
        listjournal=syslogservice.selectionnerTout();
        return listjournal;
    }

    public void setListjournal(List<SysLog> listjournal) {
        this.listjournal = listjournal;
    }

    
    
   
    }

   

