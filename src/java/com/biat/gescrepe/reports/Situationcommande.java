/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biat.gescrepe.reports;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public class Situationcommande implements EtatAvecSomme{
    private String idlignecde,datesaisiecde,idart,libart,idcde,cucde,qtecde,montant;

    public Situationcommande() {
    }
    public Situationcommande(Object[] tab) {
          SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.idlignecde = tab[0] == null ? "" : tab[0].toString();
        this.datesaisiecde = tab[1] == null ? "" : format.format((Date) tab[1]);      
        this.idart = tab[2] == null ? "" : tab[2].toString();    
        this.libart = tab[3] == null ? "" : tab[3].toString();  
        this.idcde = tab[4] == null ? "" : tab[4].toString();  
        this.cucde = tab[5] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[5].toString())); 
        this.qtecde = tab[6] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[6].toString()));  
        this.montant = tab[7] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[7].toString())); 
        
        
    }

    public String getIdlignecde() {
        return idlignecde;
    }

    public void setIdlignecde(String idlignecde) {
        this.idlignecde = idlignecde;
    }

    public String getDatesaisiecde() {
        return datesaisiecde;
    }

    public void setDatesaisiecde(String datesaisiecde) {
        this.datesaisiecde = datesaisiecde;
    }

    public String getIdart() {
        return idart;
    }

    public void setIdart(String idart) {
        this.idart = idart;
    }

    public String getLibart() {
        return libart;
    }

    public void setLibart(String libart) {
        this.libart = libart;
    }

    public String getIdcde() {
        return idcde;
    }

    public void setIdcde(String idcde) {
        this.idcde = idcde;
    }

    public String getCucde() {
        return cucde;
    }

    public void setCucde(String cucde) {
        this.cucde = cucde;
    }

    public String getQtecde() {
        return qtecde;
    }

    public void setQtecde(String qtecde) {
        this.qtecde = qtecde;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    @Override
    public List<String> listeDesChapsASommer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object construireTotal(Map<String, BigDecimal> mape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
