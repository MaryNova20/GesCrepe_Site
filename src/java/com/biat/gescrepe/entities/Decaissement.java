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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hp pro book
 */
@Entity
@Table(name = "decaissement")
@NamedQueries({
    @NamedQuery(name = "Decaissement.findAll", query = "SELECT d FROM Decaissement d")})
public class Decaissement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDecaissement")
    private Integer idDecaissement;
    @Column(name = "dateDecaissement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDecaissement;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montantDecaisse")
    private BigDecimal montantDecaisse;
    @JoinColumn(name = "idDemande", referencedColumnName = "idDemande")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Demande idDemande;

    public Decaissement() {
    }

    public Decaissement(Integer idDecaissement) {
        this.idDecaissement = idDecaissement;
    }

    public Integer getIdDecaissement() {
        return idDecaissement;
    }

    public void setIdDecaissement(Integer idDecaissement) {
        this.idDecaissement = idDecaissement;
    }

    public Date getDateDecaissement() {
        return dateDecaissement;
    }

    public void setDateDecaissement(Date dateDecaissement) {
        this.dateDecaissement = dateDecaissement;
    }

    public BigDecimal getMontantDecaisse() {
        return montantDecaisse;
    }

    public void setMontantDecaisse(BigDecimal montantDecaisse) {
        this.montantDecaisse = montantDecaisse;
    }

    public Demande getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Demande idDemande) {
        this.idDemande = idDemande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDecaissement != null ? idDecaissement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decaissement)) {
            return false;
        }
        Decaissement other = (Decaissement) object;
        if ((this.idDecaissement == null && other.idDecaissement != null) || (this.idDecaissement != null && !this.idDecaissement.equals(other.idDecaissement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Decaissement[ idDecaissement=" + idDecaissement + " ]";
    }
    
}
