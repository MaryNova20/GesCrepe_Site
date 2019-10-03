/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biat.gescrepe.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author hp pro book
 */
@Entity
@Table(name = "encours")
@NamedQueries({
    @NamedQuery(name = "Encours.findAll", query = "SELECT e FROM Encours e")})
public class Encours implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEncours")
    private Integer idEncours;
    @Column(name = "numCredit")
    private Integer numCredit;
    @Lob
    @Size(max = 65535)
    @Column(name = "objet")
    private String objet;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montantOctroye")
    private BigDecimal montantOctroye;
    @Column(name = "reste")
    private BigDecimal reste;
    @Column(name = "mensualite")
    private BigDecimal mensualite;
    @Column(name = "dateComptable")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateComptable;
    @JoinColumn(name = "matricule", referencedColumnName = "matricule")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employe matricule;

    public Encours() {
    }

    public Encours(Integer idEncours) {
        this.idEncours = idEncours;
    }

    public Integer getIdEncours() {
        return idEncours;
    }

    public void setIdEncours(Integer idEncours) {
        this.idEncours = idEncours;
    }

    public Integer getNumCredit() {
        return numCredit;
    }

    public void setNumCredit(Integer numCredit) {
        this.numCredit = numCredit;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public BigDecimal getMontantOctroye() {
        return montantOctroye;
    }

    public void setMontantOctroye(BigDecimal montantOctroye) {
        this.montantOctroye = montantOctroye;
    }

    public BigDecimal getReste() {
        return reste;
    }

    public void setReste(BigDecimal reste) {
        this.reste = reste;
    }

    public BigDecimal getMensualite() {
        return mensualite;
    }

    public void setMensualite(BigDecimal mensualite) {
        this.mensualite = mensualite;
    }

    public Date getDateComptable() {
        return dateComptable;
    }

    public void setDateComptable(Date dateComptable) {
        this.dateComptable = dateComptable;
    }

    public Employe getMatricule() {
        return matricule;
    }

    public void setMatricule(Employe matricule) {
        this.matricule = matricule;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncours != null ? idEncours.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encours)) {
            return false;
        }
        Encours other = (Encours) object;
        if ((this.idEncours == null && other.idEncours != null) || (this.idEncours != null && !this.idEncours.equals(other.idEncours))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Encours[ idEncours=" + idEncours + " ]";
    }
    
}
