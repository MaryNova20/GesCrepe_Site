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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "employe")
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e")})
public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matricule")
    private Integer matricule;
    @Column(name = "indice")
    private Integer indice;
    @Lob
    @Size(max = 65535)
    @Column(name = "nom")
    private String nom;
    @Lob
    @Size(max = 65535)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "dateEmb")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salaire")
    private BigDecimal salaire;
    @Column(name = "dateExtraction")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExtraction;
    @JoinColumn(name = "idPoste", referencedColumnName = "idPoste")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Poste idPoste;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricule", fetch = FetchType.LAZY)
    private List<Encours> encoursList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricule", fetch = FetchType.LAZY)
    private List<Demande> demandeList;

    public Employe() {
    }

    public Employe(Integer matricule) {
        this.matricule = matricule;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateEmb() {
        return dateEmb;
    }

    public void setDateEmb(Date dateEmb) {
        this.dateEmb = dateEmb;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Date getDateExtraction() {
        return dateExtraction;
    }

    public void setDateExtraction(Date dateExtraction) {
        this.dateExtraction = dateExtraction;
    }

    public Poste getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Poste idPoste) {
        this.idPoste = idPoste;
    }

    public List<Encours> getEncoursList() {
        return encoursList;
    }

    public void setEncoursList(List<Encours> encoursList) {
        this.encoursList = encoursList;
    }

    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Employe[ matricule=" + matricule + " ]";
    }
    
}
