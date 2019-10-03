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
public class aprobSystematiq implements EtatAvecSomme {

    private String matricule, nomprenom, salaire, datedemande, objetcredit, garantie,valeur, quotitecredit, quotitetotal, categorie;

    
    public aprobSystematiq() {
    }

    public aprobSystematiq(Object[] tab) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.matricule = tab[0] == null ? "" : tab[0].toString();
        this.nomprenom = tab[1] == null ? "" : tab[1].toString();
        this.salaire = tab[2] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[2].toString()));
        this.datedemande = tab[3] == null ? "" : format.format((Date) tab[3]); 
        this.objetcredit = tab[4] == null ? "" : tab[4].toString();
        this.garantie = tab[5] == null ? "" : tab[5].toString();
        this.valeur = tab[6] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[6].toString()));
        this.quotitecredit = tab[7] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[7].toString()));
        this.quotitetotal = tab[8] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[8].toString()));
        // this.valeur=(BigDecimal) tab[7];
        this.categorie = tab[9] == null ? "" : tab[9].toString();
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNomprenom() {
        return nomprenom;
    }

    public void setNomprenom(String nomprenom) {
        this.nomprenom = nomprenom;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getDatedemande() {
        return datedemande;
    }

    public void setDatedemande(String datedemande) {
        this.datedemande = datedemande;
    }

    public String getObjetcredit() {
        return objetcredit;
    }

    public void setObjetcredit(String objetcredit) {
        this.objetcredit = objetcredit;
    }

    public String getGarantie() {
        return garantie;
    }

    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getQuotitecredit() {
        return quotitecredit;
    }

    public void setQuotitecredit(String quotitecredit) {
        this.quotitecredit = quotitecredit;
    }

    public String getQuotitetotal() {
        return quotitetotal;
    }

    public void setQuotitetotal(String quotitetotal) {
        this.quotitetotal = quotitetotal;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }


    @Override
    public List<String> listeDesChapsASommer() {
        List<String> resultat = new ArrayList();
        resultat.add("valeur");
        return resultat;
    }

    @Override
    public Object construireTotal(Map<String, BigDecimal> mape) {
        aprobSystematiq resultat = new aprobSystematiq();
        resultat.valeur = EcoBentaUtils.numBerFormat(mape.get("valeur"));
        resultat.nomprenom = "Total";
        return resultat;
    }

}
