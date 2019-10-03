/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biat.gescrepe.reports;

import java.math.BigDecimal;

/**
 *
 * @author Administrateur
 */
public class Tableaudebord {
    private String idcateg,libcateg,entrees,sorties,stockfinal;

    public Tableaudebord() {
    }
    public Tableaudebord(Object[] tab) {
         this.idcateg = tab[0] == null ? "" : tab[0].toString();
        this.libcateg = tab[1] == null ? "" : tab[1].toString();
        this.entrees = tab[2] == null ? "" : tab[2].toString(); 
        this.sorties = tab[3] == null ? "" : tab[3].toString(); 
        this.stockfinal = tab[4] == null ? "" :tab[4].toString(); 
    }

    
    public String getIdcateg() {
        return idcateg;
    }

    public void setIdcateg(String idcateg) {
        this.idcateg = idcateg;
    }

    public String getLibcateg() {
        return libcateg;
    }

    public void setLibcateg(String libcateg) {
        this.libcateg = libcateg;
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

    public String getStockfinal() {
        return stockfinal;
    }

    public void setStockfinal(String stockfinal) {
        this.stockfinal = stockfinal;
    }
    
}
