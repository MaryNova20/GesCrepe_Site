/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biat.gescrepe.reports;

import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public class SubThree implements EtatAvecSomme {

    private String indice,numcredit,reste,typecredit,mtoctroye,  mtdemande,validite;

    public SubThree() {
    }

    public SubThree(Object[] tab) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.indice = tab[0] == null ? "" : tab[0].toString();
        this.numcredit = tab[1] == null ? "" : tab[1].toString();
        this.reste = tab[2] == null ? "" :  EcoBentaUtils.numBerFormat(new BigDecimal(tab[2].toString()));
        this.typecredit = tab[3] == null ? "" : tab[3].toString();
        this.mtoctroye = tab[4] == null ? "" :  EcoBentaUtils.numBerFormat(new BigDecimal(tab[4].toString()));
        this.mtdemande = tab[5] == null ? "" :  EcoBentaUtils.numBerFormat(new BigDecimal(tab[5].toString()));
        this.validite = tab[6]== null ? "" : format.format((Date) tab[6]); 
        

    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getNumcredit() {
        return numcredit;
    }

    public void setNumcredit(String numcredit) {
        this.numcredit = numcredit;
    }

    public String getReste() {
        return reste;
    }

    public void setReste(String reste) {
        this.reste = reste;
    }

    public String getTypecredit() {
        return typecredit;
    }

    public void setTypecredit(String typecredit) {
        this.typecredit = typecredit;
    }

    public String getMtoctroye() {
        return mtoctroye;
    }

    public void setMtoctroye(String mtoctroye) {
        this.mtoctroye = mtoctroye;
    }

    public String getMtdemande() {
        return mtdemande;
    }

    public void setMtdemande(String mtdemande) {
        this.mtdemande = mtdemande;
    }

    public String getValidite() {
        return validite;
    }

    public void setValidite(String validite) {
        this.validite = validite;
    }

    
    @Override
    public List<String> listeDesChapsASommer() {
        List<String> resultat = new ArrayList();
        resultat.add("mtoctroye");
        return resultat; 
    }

    @Override
    public Object construireTotal(Map<String, BigDecimal> mape) {
       SubThree resultat = new SubThree();
        resultat.mtoctroye = EcoBentaUtils.numBerFormat(mape.get("mtoctroye"));
        resultat.typecredit = "Total";
        return resultat; 
    }
   
  

}
