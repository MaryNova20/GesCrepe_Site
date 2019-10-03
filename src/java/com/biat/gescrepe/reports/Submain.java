/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biat.gescrepe.reports;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public class Submain implements EtatAvecSomme{
    
   private String type, datemiseplace, mtoctroye, reste, mensualite, validite;

    public Submain() {
    }
    
   public Submain(Object[] tab) {
       
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.type = tab[0] == null ? "" : tab[0].toString();
        this.datemiseplace = tab[1] == null ? "" :  format.format((Date)tab[1]);
        this.mtoctroye = tab[2] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[2].toString()));
        this.reste = tab[3] == null ? "" :EcoBentaUtils.numBerFormat(new BigDecimal(tab[3].toString()));
        this.mensualite = tab[4] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[4].toString()));
        this.validite = tab[5] == null ? "" : format.format((Date)tab[5]);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatemiseplace() {
        return datemiseplace;
    }

    public void setDatemiseplace(String datemiseplace) {
        this.datemiseplace = datemiseplace;
    }

    public String getMtoctroye() {
        return mtoctroye;
    }

    public void setMtoctroye(String mtoctroye) {
        this.mtoctroye = mtoctroye;
    }

    public String getReste() {
        return reste;
    }

    public void setReste(String reste) {
        this.reste = reste;
    }

    public String getMensualite() {
        return mensualite;
    }

    public void setMensualite(String mensualite) {
        this.mensualite = mensualite;
    }

    public String getValidite() {
        return validite;
    }

    public void setValidite(String validite) {
        this.validite = validite;
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
