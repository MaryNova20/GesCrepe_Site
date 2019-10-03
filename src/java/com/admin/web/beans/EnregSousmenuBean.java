/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.web.beans;

import com.admin.entities.Menu; 
import com.admin.entities.MenuItem;
import com.admin.entities.TypeOper;
import com.admin.services.MenuItemServiceBeanLocal;
import com.admin.services.MenuServiceBeanLocal;
import com.admin.services.TypeOperServiceBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author desire.mensah
 */
@ManagedBean
@ViewScoped
public class EnregSousmenuBean implements Serializable {

    private List<MenuItem> sousmenu1 = new ArrayList<>();
    private List<MenuItem> sousmenu2 = new ArrayList<>();
    private List<MenuItem> sousmenu3 = new ArrayList<>();
    private List<MenuItem> sousmenu4 = new ArrayList<>();
     private List<MenuItem> sousmenu5 = new ArrayList<>();
    private List<MenuItem> verifmenu = new ArrayList<>();
    private Boolean desactiver = true;

    @EJB
    private MenuServiceBeanLocal menuService;
    private Menu formMenu;

    @EJB
    private TypeOperServiceBeanLocal typeOperService;
    private TypeOper formtypeOper;

    @EJB
    private MenuItemServiceBeanLocal menuItemService;

    @ManagedProperty("#{sousmenuService}")
    private SousmenuService service;

    @PostConstruct
    public void init() {
        verifmenu = menuItemService.selectionnerTout();
        if (verifmenu.isEmpty()) {
            desactiver = false;
        }

        sousmenu1 = service.createmenuadministration(5);
        sousmenu2 = service.createmenudemandes(2);
        sousmenu3 = service.createmenusuividemande(3);
        sousmenu4 = service.createmenueditionetat(4);
        sousmenu4 = service.createmenudonneesbase(3);
        
    }

    /**
     * Creates a new instance of EnregSousmenuBean
     */
    public EnregSousmenuBean() {
        formMenu = new Menu();
        formtypeOper = new TypeOper();
    }

    public void valider() {
        MessageBean m = new MessageBean();

        // enregistrement des types operations
        formtypeOper.setTypeOperDesc("CONNEXION");
        typeOperService.ajouter(formtypeOper);
        formtypeOper = new TypeOper();

        formtypeOper.setTypeOperDesc("DECONNEXION");
        typeOperService.ajouter(formtypeOper);
        formtypeOper = new TypeOper();

        formtypeOper.setTypeOperDesc("DEMANDES");
        typeOperService.ajouter(formtypeOper);
        formtypeOper = new TypeOper();

        formtypeOper.setTypeOperDesc("SUIVI DES DEMANDES");
        typeOperService.ajouter(formtypeOper);
        formtypeOper = new TypeOper();

        formtypeOper.setTypeOperDesc("EDITION ETATS ");
        typeOperService.ajouter(formtypeOper);
        formtypeOper = new TypeOper();

        formtypeOper.setTypeOperDesc("CHANGEMENT DE MOT DE PASSE");
        typeOperService.ajouter(formtypeOper);
        formtypeOper = new TypeOper();
        
        //enregistrement menu1
        formMenu.setActive("actif");
        formMenu.setMenuDesc("ADMINISTRATION");
        menuService.ajouter(formMenu);
        int idmenu1 = formMenu.getMenuId();
        formMenu = new Menu();

        //enregistrement menu2
        formMenu.setActive("actif");
        formMenu.setMenuDesc("DEMANDES");
        menuService.ajouter(formMenu);
        int idmenu2 = formMenu.getMenuId();

        formMenu = new Menu();

        //enregistrement menu3
        formMenu.setActive("actif");
        formMenu.setMenuDesc("SUIVI DES DEMANDES");
        menuService.ajouter(formMenu);
        int idmenu3 = formMenu.getMenuId();

        formMenu = new Menu();

        //enregistrement menu 4
        formMenu.setActive("actif");
        formMenu.setMenuDesc("EDITION ETATS");
        menuService.ajouter(formMenu);
        int idmenu4 = formMenu.getMenuId();

        formMenu = new Menu();
        
        
        //enregistrement menu 5
        formMenu.setActive("actif");
        formMenu.setMenuDesc("DONNEES BASE");
        menuService.ajouter(formMenu);
        int idmenu5 = formMenu.getMenuId();

        formMenu = new Menu();
        

       //enregistrement des sous menus de chaque menu enregistré ci-haut
        //les sous menus du menu 1
        for (MenuItem sousmenutiers : sousmenu1) {
            sousmenutiers.setActive("actif");
            sousmenutiers.setDateCreation(new Date());
            sousmenutiers.setMenuId(menuService.selectionner(idmenu1));
            menuItemService.ajouter(sousmenutiers);
        }

        //les sous menus du menu 2
        for (MenuItem sousmenupartenaires : sousmenu2) {
            sousmenupartenaires.setActive("actif");
            sousmenupartenaires.setDateCreation(new Date());
            sousmenupartenaires.setMenuId(menuService.selectionner(idmenu2));
            menuItemService.ajouter(sousmenupartenaires);
        }
        //les sous menus du menu 3
        for (MenuItem sousmenucontrats : sousmenu3) {
            sousmenucontrats.setActive("actif");
            sousmenucontrats.setDateCreation(new Date());
            sousmenucontrats.setMenuId(menuService.selectionner(idmenu3));
            menuItemService.ajouter(sousmenucontrats);
        }
        //les sous menus du menu 4
        for (MenuItem sousmenusinistres : sousmenu4) {
            sousmenusinistres.setActive("actif");
            sousmenusinistres.setDateCreation(new Date());
            sousmenusinistres.setMenuId(menuService.selectionner(idmenu4));
            menuItemService.ajouter(sousmenusinistres);
        }
        
        
        //les sous menus du menu 5
        for (MenuItem sousmenusinistres : sousmenu5) {
            sousmenusinistres.setActive("actif");
            sousmenusinistres.setDateCreation(new Date());
            sousmenusinistres.setMenuId(menuService.selectionner(idmenu5));
            menuItemService.ajouter(sousmenusinistres);
        }
        
        
        desactiver = true;
        m.addMessageInfo("lES MENUS DE L'APPLICATION GesCREPE ONT ETES BIEN PARAMETRES");
    }

    //gettters et setters
    public List<MenuItem> getSousmenu1() {

        return sousmenu1;
    }

    public void setSousmenu1(List<MenuItem> sousmenu1) {
        this.sousmenu1 = sousmenu1;
    }

    public SousmenuService getService() {
        return service;
    }

    public void setService(SousmenuService service) {
        this.service = service;
    }

    public List<MenuItem> getSousmenu2() {
        return sousmenu2;
    }

    public void setSousmenu2(List<MenuItem> sousmenu2) {
        this.sousmenu2 = sousmenu2;
    }

    public List<MenuItem> getSousmenu3() {
        return sousmenu3;
    }

    public void setSousmenu3(List<MenuItem> sousmenu3) {
        this.sousmenu3 = sousmenu3;
    }

    public List<MenuItem> getSousmenu4() {
        return sousmenu4;
    }

    public void setSousmenu4(List<MenuItem> sousmenu4) {
        this.sousmenu4 = sousmenu4;
    }

    
    public List<MenuItem> getVerifmenu() {
        return verifmenu;
    }

    public void setVerifmenu(List<MenuItem> verifmenu) {
        this.verifmenu = verifmenu;
    }

    public MenuItemServiceBeanLocal getMenuItemService() {
        return menuItemService;
    }

    public void setMenuItemService(MenuItemServiceBeanLocal menuItemService) {
        this.menuItemService = menuItemService;
    }

    public MenuServiceBeanLocal getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuServiceBeanLocal menuService) {
        this.menuService = menuService;
    }

    public Menu getFormMenu() {
        return formMenu;
    }

    public List<MenuItem> getSousmenu5() {
        return sousmenu5;
    }

    public void setSousmenu5(List<MenuItem> sousmenu5) {
        this.sousmenu5 = sousmenu5;
    }

    public void setFormMenu(Menu formMenu) {
        this.formMenu = formMenu;
    }

    
    
    public Boolean getDesactiver() {
        return desactiver;
    }

    public void setDesactiver(Boolean desactiver) {
        this.desactiver = desactiver;
    }

    public TypeOperServiceBeanLocal getTypeOperService() {
        return typeOperService;
    }

    public void setTypeOperService(TypeOperServiceBeanLocal typeOperService) {
        this.typeOperService = typeOperService;
    }

    public TypeOper getFormtypeOper() {
        return formtypeOper;
    }

    public void setFormtypeOper(TypeOper formtypeOper) {
        this.formtypeOper = formtypeOper;
    }

}
