/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biat.gescrepe.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public class SubFour implements EtatAvecSomme {

    private String type, encours, mensualite;

    public SubFour() {
    }

    public SubFour(Object[] tab) {
        this.type = tab[0] == null ? "" : tab[0].toString();
        this.encours = tab[1] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[1].toString()));
        this.mensualite = tab[2] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[2].toString()));
       
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEncours() {
        return encours;
    }

    public void setEncours(String encours) {
        this.encours = encours;
    }

    public String getMensualite() {
        return mensualite;
    }

    public void setMensualite(String mensualite) {
        this.mensualite = mensualite;
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
