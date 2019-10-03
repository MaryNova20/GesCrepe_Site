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
public class Detailcommandelivrer implements EtatAvecSomme{
    private String idart,libcateg,libart,qtecde,cucde,idcde,datecde,totalcde,libtypecde;

    public Detailcommandelivrer() {
        
    }
       public Detailcommandelivrer(Object[] tab) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.idart=tab[0]==null ? "" : tab[0].toString();
        this.libcateg=tab[1]==null ? "" : tab[1].toString();
        this.libart=tab[2]==null ? "" : tab[2].toString();
        this.qtecde=tab[3]==null ? "" : tab[3].toString();
        this.cucde = tab[4] == null ? "0" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[4].toString()));
        this.idcde=tab[5]==null ? "" : tab[5].toString();
        this.datecde = tab[6] == null ? "" : format.format((Date) tab[6]);
        this.totalcde=tab[7]==null ? "" : EcoBentaUtils.numBerFormat(new BigDecimal(tab[7].toString()));
        this.libtypecde=tab[8]==null ? "" : tab[8].toString();
    }

    public String getIdart() {
        return idart;
    }

    public void setIdart(String idart) {
        this.idart = idart;
    }

    public String getLibcateg() {
        return libcateg;
    }

    public void setLibcateg(String libcateg) {
        this.libcateg = libcateg;
    }

    public String getLibart() {
        return libart;
    }

    public void setLibart(String libart) {
        this.libart = libart;
    }

    public String getQtecde() {
        return qtecde;
    }

    public void setQtecde(String qtecde) {
        this.qtecde = qtecde;
    }

    public String getCucde() {
        return cucde;
    }

    public void setCucde(String cucde) {
        this.cucde = cucde;
    }

    public String getIdcde() {
        return idcde;
    }

    public void setIdcde(String idcde) {
        this.idcde = idcde;
    }

    public String getDatecde() {
        return datecde;
    }

    public void setDatecde(String datecde) {
        this.datecde = datecde;
    }

    public String getTotalcde() {
        return totalcde;
    }

    public void setTotalcde(String totalcde) {
        this.totalcde = totalcde;
    }

    public String getLibtypecde() {
        return libtypecde;
    }

    public void setLibtypecde(String libtypecde) {
        this.libtypecde = libtypecde;
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
