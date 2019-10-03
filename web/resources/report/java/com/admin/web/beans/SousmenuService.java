/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.web.beans;

import com.admin.entities.MenuItem;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author desire.mensah
 */
@ManagedBean(name = "sousmenuService")
@ApplicationScoped
public class SousmenuService {

    private final static String[] administration;
    private final static String[] donneesbase;
    private final static String[] demandes;
    private final static String[] suividemande;
    private final static String[] editionetat;
    
    /**
     * Creates a new instance of SousmenuService
     */
    static {
        administration = new String[5];
        administration[0] = "USERS";
        administration[1] = "PROFIL";
        administration[2] = "HABILITATIONS";
        administration[3] = "SYSLOG";
        administration[4] = "MENUS";
        
        donneesbase = new String[3];
        donneesbase[0] = "OBJET CREDIT";
        donneesbase[1] = "TYPE CREDIT";
         donneesbase[2] = "GARANTIE";

        demandes = new String[2];
        demandes[0] = "NOUVELLE DEMANDE";
        demandes[1] = "ANNULER DEMANDE";
        
        

        suividemande = new String[3];
        suividemande[0] = "MISE A JOUR DES DEMANDES";
        suividemande[1] = "SITUATION DES DEMANDES";
        suividemande[2] = "DECAISSEMENT DES PRÊTS";

        editionetat = new String[4];
        editionetat[0] = "APPROBATION SYSTEMATIQUE";
        editionetat[1] = "DEMANDES EN COMITE";
        editionetat[2] = "SITUATION DES DECAISSEMENTS";
        editionetat[3] = "FICHE DE COMITE";
     

        
    }

    public List<MenuItem> createmenuadministration(int size) {
        List<MenuItem> list1 = new ArrayList<MenuItem>();
        for (int i = 0; i < size; i++) {
            list1.add(new MenuItem(i + 1, administration[i]));
        }

        return list1;
    }

    public List<MenuItem> createmenudemandes(int size) {
        List<MenuItem> list2 = new ArrayList<MenuItem>();
        for (int i = 0; i < size; i++) {
            list2.add(new MenuItem(i + 1, demandes[i]));
        }

        return list2;
    }

    public List<MenuItem> createmenusuividemande(int size) {
        List<MenuItem> list3 = new ArrayList<MenuItem>();
        for (int i = 0; i < size; i++) {
            list3.add(new MenuItem(i + 1, suividemande[i]));
        }

        return list3;
    }

    public List<MenuItem> createmenueditionetat(int size) {
        List<MenuItem> list4 = new ArrayList<MenuItem>();
        for (int i = 0; i < size; i++) {
            list4.add(new MenuItem(i + 1, editionetat[i]));
        }

        return list4;
    }
    
    
    public List<MenuItem> createmenudonneesbase(int size) {
        List<MenuItem> list5 = new ArrayList<MenuItem>();
        for (int i = 0; i < size; i++) {
            list5.add(new MenuItem(i + 1, donneesbase[i]));
        }

        return list5;
    }

    

}
