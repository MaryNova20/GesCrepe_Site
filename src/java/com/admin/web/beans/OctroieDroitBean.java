/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.web.beans;

import com.admin.entities.MenuItem;
import com.admin.entities.Profil;
import com.admin.entities.ProfilMenuItem;
import com.admin.services.MenuItemServiceBeanLocal;
import com.admin.services.ProfilMenuItemServiceBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author desire.mensah
 */
@ManagedBean
@ViewScoped
public class OctroieDroitBean implements Serializable{

    @EJB
    private ProfilMenuItemServiceBeanLocal profilMenuItemService;
    private List<ProfilMenuItem> datalistdejaaffecte;
    private ProfilMenuItem formprofilMenuitem;
    private ProfilMenuItem selprofilMenuitem;
    private String libellebouton = "activer";
    private String iconbouton = "ui-icon-check";

    @EJB
    private MenuItemServiceBeanLocal menuItemService;
    private List<MenuItem> datalistenonaffecte;
    private List<MenuItem> selMenuItem;

    /**
     * Creates a new instance of OctroieDroitBean
     */
    public OctroieDroitBean() {
        datalistdejaaffecte = new ArrayList<>();
        datalistenonaffecte = new ArrayList<>();
        formprofilMenuitem = new ProfilMenuItem();
        selMenuItem = new ArrayList<>();
        selprofilMenuitem = new ProfilMenuItem();
    }

    public void retirer() {
        MessageBean m = new MessageBean();
        Profil idprofil = formprofilMenuitem.getProfilId();
        if (selprofilMenuitem != null) {
            ProfilMenuItem suppprofil = selprofilMenuitem;
            profilMenuItemService.supprimer(suppprofil.getIddroit());
        }
        formprofilMenuitem.setProfilId(idprofil);
        datalistenonaffecte = menuItemService.menunonaffectes(idprofil.getProfilId());
        datalistdejaaffecte = profilMenuItemService.selectionnerListeProfilMenuItem(idprofil.getProfilId());
        m.addMessageInfo("le droit " + selprofilMenuitem.getMenuItemId().getMenuItemDesc() + " "
                + "a été retiré");
        selprofilMenuitem = new ProfilMenuItem();

    }

    public void rowselect() {
        MessageBean m = new MessageBean();
        if (selprofilMenuitem != null) {
            if ("actif".equals(selprofilMenuitem.getActive())) {
                libellebouton = "desactiver";
                iconbouton = "ui-icon-close";
            } else {
                libellebouton = "activer";
                iconbouton = "ui-icon-check";
            }
        }
    }

    public void valider() {
        MessageBean m = new MessageBean();
        Profil idprofil = formprofilMenuitem.getProfilId();
        if (selprofilMenuitem != null) {
            if ("actif".equals(selprofilMenuitem.getActive())) {
                libellebouton = "révoqué";
                iconbouton = "ui-icon-close";
                selprofilMenuitem.setActive("inactif");
                profilMenuItemService.modifier(selprofilMenuitem);

            } else {
                libellebouton = "octroyé";
                iconbouton = "ui-icon-check";
                selprofilMenuitem.setActive("actif");
                profilMenuItemService.modifier(selprofilMenuitem);
            }
        }
        formprofilMenuitem.setProfilId(idprofil);
        datalistenonaffecte = menuItemService.menunonaffectes(idprofil.getProfilId());
        datalistdejaaffecte = profilMenuItemService.selectionnerListeProfilMenuItem(idprofil.getProfilId());

        selprofilMenuitem = new ProfilMenuItem();
        m.addMessageInfo("le droit a été bien " + libellebouton);
    }

    public void remplir() {
        datalistenonaffecte = menuItemService.menunonaffectes(formprofilMenuitem.getProfilId().getProfilId());
        datalistdejaaffecte = profilMenuItemService.selectionnerListeProfilMenuItem(formprofilMenuitem.getProfilId().getProfilId());
        selMenuItem = new ArrayList<>();
    }

    public void traitement() {

        MessageBean m = new MessageBean();
        Profil idprofil = formprofilMenuitem.getProfilId();
        String profilstr = idprofil.getProfilDesc();
        int nbre = selMenuItem.size();
        if (selMenuItem.isEmpty()) {
            m.addMessageInfo("la liste est vide");
        } else {
            for (MenuItem datalist : selMenuItem) {
                try {
                    formprofilMenuitem.setMenuItemId(datalist);
                    formprofilMenuitem.setActive("actif");
                    formprofilMenuitem.setProfilId(idprofil);
                    System.out.println("profil"+idprofil);
                    System.out.println("Menu"+datalist);
                    profilMenuItemService.ajouter(formprofilMenuitem);
                    formprofilMenuitem = new ProfilMenuItem();
                } catch (Exception e) {
                    e.getCause().getMessage();
                }

            }
            formprofilMenuitem.setProfilId(idprofil);
            datalistenonaffecte = menuItemService.menunonaffectes(idprofil.getProfilId());
            datalistdejaaffecte = profilMenuItemService.selectionnerListeProfilMenuItem(idprofil.getProfilId());

            m.addMessageInfo(nbre + "  droits ont été affectés avec succès au profil " + profilstr);
            selMenuItem = new ArrayList<>();
        }

    }

    public ProfilMenuItemServiceBeanLocal getProfilMenuItemService() {
        return profilMenuItemService;
    }

    public void setProfilMenuItemService(ProfilMenuItemServiceBeanLocal profilMenuItemService) {
        this.profilMenuItemService = profilMenuItemService;
    }

    public List<ProfilMenuItem> getDatalistdejaaffecte() {
        return datalistdejaaffecte;
    }

    public void setDatalistdejaaffecte(List<ProfilMenuItem> datalistdejaaffecte) {
        this.datalistdejaaffecte = datalistdejaaffecte;
    }

  

    public List<MenuItem> getDatalistenonaffecte() {

        return datalistenonaffecte;
    }

    public void setDatalistenonaffecte(List<MenuItem> datalistenonaffecte) {
        this.datalistenonaffecte = datalistenonaffecte;
    }

    public ProfilMenuItem getFormprofilMenuitem() {
        return formprofilMenuitem;
    }

    public void setFormprofilMenuitem(ProfilMenuItem formprofilMenuitem) {
        this.formprofilMenuitem = formprofilMenuitem;
    }

    public List<MenuItem> getSelMenuItem() {
        return selMenuItem;
    }

    public void setSelMenuItem(List<MenuItem> selMenuItem) {
        this.selMenuItem = selMenuItem;
    }

    public ProfilMenuItem getSelprofilMenuitem() {
        return selprofilMenuitem;
    }

    public void setSelprofilMenuitem(ProfilMenuItem selprofilMenuitem) {
        this.selprofilMenuitem = selprofilMenuitem;
    }

    public String getLibellebouton() {
        return libellebouton;
    }

    public void setLibellebouton(String libellebouton) {
        this.libellebouton = libellebouton;
    }

    public String getIconbouton() {
        return iconbouton;
    }

    public void setIconbouton(String iconbouton) {
        this.iconbouton = iconbouton;
    }

}
