package com.admin.web.beans;

import com.admin.entities.Profil;
import com.admin.services.ProfilServiceBeanLocal;

import com.biat.gescrepe.entities.Utilisateurs;
import com.biat.gescrepe.services.UtilisateursServiceBeanLocal;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MANU
 */
@ManagedBean(name = "employeManagedBean")
@ViewScoped
public class EmployeManagedBean implements Serializable {

    @EJB
    private UtilisateursServiceBeanLocal employeService;
    private Utilisateurs formUtilisateurs = new Utilisateurs();
    private Utilisateurs selectedUtilisateurs;
    private List<Utilisateurs> dataListUtilisateurs;
    private Boolean desactiversuppr = true;
    private String btnactiver = "valider";
    private int index;

    @EJB
    private ProfilServiceBeanLocal profilService;
    private List<Profil> dataListProfil;

    public EmployeManagedBean() {
        formUtilisateurs = new Utilisateurs();
        selectedUtilisateurs = new Utilisateurs();
        dataListUtilisateurs = new ArrayList<Utilisateurs>();
        btnactiver = "valider";
    }
      public String hashpassword(char[] c){
           try{
               MessageDigest digsts=MessageDigest.getInstance("MD5");
               digsts.update(new String(c).getBytes("UTF8"));
               String resultmpd=new String(digsts.digest());
                  return  resultmpd; 
           }
           catch(Exception e){
               return "";
           }
       
       }
    public void enregistrer() throws NoSuchAlgorithmException {
        List<Utilisateurs> list = employeService.selectionnerTout();
        MessageBean m = new MessageBean();
        int i = 0;
        if (formUtilisateurs.getNomuser().trim().isEmpty()
                || formUtilisateurs.getLoginuser().trim().isEmpty() ) {
            m.addMessageWarn("veuilllez renseigner les champs");
        } else {
            if (selectedUtilisateurs != null) {
                employeService.modifier(formUtilisateurs);
                btnactiver = "valider";
                selectedUtilisateurs = new Utilisateurs();
                m.addMessageInfo("employe " + formUtilisateurs.getNomuser() + " modifié");

            } else {

                for (Utilisateurs list1 : list) {
                    if (formUtilisateurs.getNomuser().equalsIgnoreCase(list1.getNomuser()) && formUtilisateurs.getLoginuser().equalsIgnoreCase(list1.getLoginuser())) {
                        i++;
                    }
                }
                if (i == 0) {
                    String password = formUtilisateurs.getLoginuser().toLowerCase().substring(0, 2) + formUtilisateurs.getLoginuser().toLowerCase();
                    //cryptage du mot de passe
                     password=codeCrypter(password);
                     formUtilisateurs.setPwduser(password);
                    employeService.ajouter(formUtilisateurs);
                    m.addMessageInfo("enregistré");
                    this.desactiversuppr = true;
                } else {
                    m.addMessageWarn("deja enregisrté");
                }

            }
        }
        formUtilisateurs = new Utilisateurs();
        this.desactiversuppr = true;
    }

    public void reinitialiser() throws NoSuchAlgorithmException {
        MessageBean m = new MessageBean();
        String mp= formUtilisateurs.getLoginuser().toLowerCase().substring(0, 2) + formUtilisateurs.getLoginuser().toLowerCase();
        System.out.println("mot de passe en clair "+mp);
        formUtilisateurs.setPwduser(EmployeManagedBean.codeCrypter(mp));
        employeService.modifier(formUtilisateurs);
        m.addMessageInfo("le mot de pase de " + formUtilisateurs.getLoginuser() + " a été bien réinitialisé");
        formUtilisateurs = new Utilisateurs();
        selectedUtilisateurs = new Utilisateurs();
        desactiversuppr = true;
    }

    public void effacer() {
        formUtilisateurs = new Utilisateurs();
        selectedUtilisateurs = null;
        selectedUtilisateurs = new Utilisateurs();
    }

    public void rowSelected() {
        formUtilisateurs = selectedUtilisateurs;
        this.index = this.dataListUtilisateurs.indexOf(this.selectedUtilisateurs);
        this.desactiversuppr = false;
        btnactiver = "activer";
    }

    public Utilisateurs getFormUtilisateurs() {
        return formUtilisateurs;
    }

    public void setFormUtilisateurs(Utilisateurs formUtilisateurs) {
        this.formUtilisateurs = formUtilisateurs;
    }

    public Utilisateurs getSelectedUtilisateurs() {
        return selectedUtilisateurs;
    }

    public void setSelectedUtilisateurs(Utilisateurs selectedUtilisateurs) {
        this.selectedUtilisateurs = selectedUtilisateurs;
    }

    public List<Utilisateurs> getDataListUtilisateurs() {
        dataListUtilisateurs = employeService.selectionnerTout();
        return dataListUtilisateurs;
    }

    public void setDataListUtilisateurs(List<Utilisateurs> dataListUtilisateurs) {
        this.dataListUtilisateurs = dataListUtilisateurs;
    }

    public Boolean getDesactiversuppr() {
        return desactiversuppr;
    }

    public void setDesactiversuppr(Boolean desactiversuppr) {
        this.desactiversuppr = desactiversuppr;
    }

    public String getBtnactiver() {
        return btnactiver;
    }

    public void setBtnactiver(String btnactiver) {
        this.btnactiver = btnactiver;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Profil> getDataListProfil() {
        dataListProfil = profilService.selectionnerTout();
        return dataListProfil;
    }

    public void setDataListProfil(List<Profil> dataListProfil) {
        this.dataListProfil = dataListProfil;
    }

    public static  String codeCrypter(String text) throws NoSuchAlgorithmException {
        // TODO code application logic here

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] code = md.digest(text.getBytes());

        return bytesToHex(code);
    }

    public static String bytesToHex(byte[] b) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'};
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < b.length; j++) {
            buffer.append(hexDigits[(b[j] >> 4) & 0x0f]);
            buffer.append(hexDigits[b[j] & 0x0f]);
        }
        return buffer.toString();
    }
}
