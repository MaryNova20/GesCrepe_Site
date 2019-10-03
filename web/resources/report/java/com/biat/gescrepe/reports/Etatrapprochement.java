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
 * @author Administrator
 */
public class Etatrapprochement implements EtatAvecSomme{
     private String dateoperation, libelle, debit, credit;

    public Etatrapprochement() {
        
    }

    public Etatrapprochement(Object[] tab) {
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.dateoperation = tab[0] == null ? "-" : format.format((Date) tab[0]);
        this.libelle = tab[1] == null ? "" : tab[1].toString();

    }

    public String getDateoperation() {
        return dateoperation;
    }

    public void setDateoperation(String dateoperation) {
        this.dateoperation = dateoperation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

     @Override
    public List<String> listeDesChapsASommer() {
        List<String> resultat = new ArrayList();
        resultat.add("debit");
        resultat.add("credit");
        return resultat;
    }

    @Override
    public Object construireTotal(Map<String, BigDecimal> mape) {
        Etatrapprochement resultat =new Etatrapprochement();
       resultat.libelle="Total";
        resultat.dateoperation="-";
        return resultat;
    }
  
    


}
