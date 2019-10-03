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
public class JournalES implements EtatAvecSomme{
    private String idart,artbkstock,datehisstock,stockavant,entrees,sorties,stockapres,qteactu;

    public JournalES() {
    }
    public JournalES(Object[] tab) {
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.idart = tab[0] == null ? "" : tab[0].toString();
        this.artbkstock = tab[1] == null ? "" : tab[1].toString();   
        this.datehisstock = tab[2] == null ? "" : format.format((Date) tab[2]); 
        this.stockavant = tab[3] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[3].toString())); 
        this.entrees = tab[4] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[4].toString()));   
        this.sorties = tab[5] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[5].toString())); 
        this.stockapres = tab[6] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[6].toString()));   
        this.qteactu = tab[7] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[7].toString()));      
    }

    public String getIdart() {
        return idart;
    }

    public void setIdart(String idart) {
        this.idart = idart;
    }

    public String getArtbkstock() {
        return artbkstock;
    }

    public void setArtbkstock(String artbkstock) {
        this.artbkstock = artbkstock;
    }

    public String getDatehisstock() {
        return datehisstock;
    }

    public void setDatehisstock(String datehisstock) {
        this.datehisstock = datehisstock;
    }

    public String getStockavant() {
        return stockavant;
    }

    public void setStockavant(String stockavant) {
        this.stockavant = stockavant;
    }

    public String getEntrees() {
        return entrees;
    }

    public void setEntrees(String entrees) {
        this.entrees = entrees;
    }

    public String getSorties() {
        return sorties;
    }

    public void setSorties(String sorties) {
        this.sorties = sorties;
    }

    public String getStockapres() {
        return stockapres;
    }

    public void setStockapres(String stockapres) {
        this.stockapres = stockapres;
    }

    public String getQteactu() {
        return qteactu;
    }

    public void setQteactu(String qteactu) {
        this.qteactu = qteactu;
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
