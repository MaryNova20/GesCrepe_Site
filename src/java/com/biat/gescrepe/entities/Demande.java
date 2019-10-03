/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biat.gescrepe.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author hp pro book
 */
@Entity
@Table(name = "demande")
public class Demande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDemande")
    private Integer idDemande;
    @Column(name = "dateDemande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montantCessible")
    private BigDecimal montantCessible;
    @Column(name = "quotiteCessible")
    private BigDecimal quotiteCessible;
    @Column(name = "quotiteAtteinte")
    private BigDecimal quotiteAtteinte;
    @Lob
    @Size(max = 65535)
    @Column(name = "appreciation")
    private String appreciation;
    @Lob
    @Size(max = 65535)
    @Column(name = "decision")
    private String decision;
    @Lob
    @Size(max = 65535)
    @Column(name = "commentaire")
    private String commentaire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDemande", fetch = FetchType.LAZY)
    private List<Decaissement> decaissementList;
    @JoinColumn(name = "matricule", referencedColumnName = "matricule")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employe matricule;
    @JoinColumn(name = "idObjet", referencedColumnName = "idObjet")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Objet idObjet;
    @JoinColumn(name = "idGarantie", referencedColumnName = "idGarantie")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Garantie idGarantie;

    public Demande() {
    }

    public Demande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public BigDecimal getMontantCessible() {
        return montantCessible;
    }

    public void setMontantCessible(BigDecimal montantCessible) {
        this.montantCessible = montantCessible;
    }

    public BigDecimal getQuotiteCessible() {
        return quotiteCessible;
    }

    public void setQuotiteCessible(BigDecimal quotiteCessible) {
        this.quotiteCessible = quotiteCessible;
    }

    public BigDecimal getQuotiteAtteinte() {
        return quotiteAtteinte;
    }

    public void setQuotiteAtteinte(BigDecimal quotiteAtteinte) {
        this.quotiteAtteinte = quotiteAtteinte;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public List<Decaissement> getDecaissementList() {
        return decaissementList;
    }

    public void setDecaissementList(List<Decaissement> decaissementList) {
        this.decaissementList = decaissementList;
    }

    public Employe getMatricule() {
        return matricule;
    }

    public void setMatricule(Employe matricule) {
        this.matricule = matricule;
    }

    public Objet getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(Objet idObjet) {
        this.idObjet = idObjet;
    }

    

    public Garantie getIdGarantie() {
        return idGarantie;
    }

    public void setIdGarantie(Garantie idGarantie) {
        this.idGarantie = idGarantie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDemande != null ? idDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.idDemande == null && other.idDemande != null) || (this.idDemande != null && !this.idDemande.equals(other.idDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Demande[ idDemande=" + idDemande + " ]";
    }
    
}
