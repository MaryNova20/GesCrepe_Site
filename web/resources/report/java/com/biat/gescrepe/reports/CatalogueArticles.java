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
public class CatalogueArticles implements EtatAvecSomme{
    private String libelle,stockalerte,qtedispo,categorie;

    public CatalogueArticles() {
    }
    
   public CatalogueArticles(Object[] tab) {
       this.libelle=tab[0]==null ? "" : tab[0].toString();
       this.stockalerte=tab[1]==null ? "" : EcoBentaUtils.numBerFormatInteger(new Integer(tab[1].toString()));
       this.qtedispo=tab[2]==null ? "" : EcoBentaUtils.numBerFormatInteger(new Integer(tab[2].toString()));
       this.categorie=tab[3]==null ? "" : tab[3].toString().toUpperCase();
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getStockalerte() {
        return stockalerte;
    }

    public void setStockalerte(String stockalerte) {
        this.stockalerte = stockalerte;
    }

    public String getQtedispo() {
        return qtedispo;
    }

    public void setQtedispo(String qtedispo) {
        this.qtedispo = qtedispo;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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
        final CatalogueArticles other = (CatalogueArticles) obj;
        return true;
    }

    
    
  
    
}
