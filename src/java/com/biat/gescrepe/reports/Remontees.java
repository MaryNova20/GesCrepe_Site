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
public class Remontees implements EtatAvecSomme{
    String nomprenom,datecomite,objet,nature,montant,taux,totalencours,totalmensualite,salaire;

    public Remontees() {
    }

    public Remontees(Object[] tab) {
        this.nomprenom = tab[0] == null ? "" : tab[0].toString();
        this.objet = tab[1] == null ? "" : tab[1].toString();
        this.nature = tab[2] == null ? "" : tab[2].toString();
        this.montant = tab[3] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[3].toString()));
        this.taux = tab[4] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[4].toString()));
        this.totalencours = tab[5] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[5].toString()));
        this.totalmensualite = tab[6] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[6].toString()));
        this.salaire = tab[7] == null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[7].toString()));
    }
  

    @Override
        public List<String> listeDesChapsASommer() {
        List<String> resultat = new ArrayList();
        resultat.add("montant");
        return resultat;    }

    @Override
    public Object construireTotal(Map<String, BigDecimal> mape) {
        Remontees resultat = new Remontees();
        resultat.montant = EcoBentaUtils.numBerFormat(mape.get("montant"));
        resultat.nomprenom = "Total";
        return resultat;
    }

    public String getNomprenom() {
        return nomprenom;
    }

    public void setNomprenom(String nomprenom) {
        this.nomprenom = nomprenom;
    }

    public String getDatecomite() {
        return datecomite;
    }

    public void setDatecomite(String datecomite) {
        this.datecomite = datecomite;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    public String getTotalencours() {
        return totalencours;
    }

    public void setTotalencours(String totalencours) {
        this.totalencours = totalencours;
    }

    public String getTotalmensualite() {
        return totalmensualite;
    }

    public void setTotalmensualite(String totalmensualite) {
        this.totalmensualite = totalmensualite;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }
    
    
}
