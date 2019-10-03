/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biat.gescrepe.reports;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public class CatalogueFournisseur implements EtatAvecSomme{
    private String nom,nif,contact,adresse,ncp,prestation,cltbia;

    public CatalogueFournisseur() {
    }

   
    
   public CatalogueFournisseur(Object[] tab) {
       this.nom=tab[0]==null ? "" : tab[0].toString();
       this.nif=tab[1]==null ? "" : tab[1].toString();
       this.contact=tab[2]==null ? "" : tab[2].toString();
       this.adresse=tab[3]==null ? "" : tab[3].toString();
       this.ncp=tab[4]==null ? "" : tab[4].toString();
       this.prestation=tab[5]==null ? "" : tab[5].toString();
       if(tab[6].toString().equalsIgnoreCase("oui")){
          this.cltbia="CLIENT BIA"; 
       }else if(tab[6].toString().equalsIgnoreCase("non") || tab[6].toString()==null){
           this.cltbia="AUTRES CLIENTS";
       }else{
           this.cltbia=tab[6].toString();
       }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNcp() {
        return ncp;
    }

    public void setNcp(String ncp) {
        this.ncp = ncp;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }

    public String getCltbia() {
        return cltbia;
    }

    public void setCltbia(String cltbia) {
        this.cltbia = cltbia;
    }
    


    @Override
    public List<String> listeDesChapsASommer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object construireTotal(Map<String, BigDecimal> mape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatalogueFournisseur other = (CatalogueFournisseur) obj;
        return true;
    }

    
    
  
    
}
