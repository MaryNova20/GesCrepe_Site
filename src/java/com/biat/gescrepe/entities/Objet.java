/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biat.gescrepe.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

/**
 *
 * @author hp pro book
 */
@Entity
@Table(name = "objet")
@NamedQueries({
    @NamedQuery(name = "Objet.findAll", query = "SELECT o FROM Objet o")})
public class Objet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idObjet")
    private Integer idObjet;
    @Lob
    @Size(max = 65535)
    @Column(name = "libObjet")
    private String libObjet;
    @JoinColumn(name = "idType", referencedColumnName = "idType")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Typecredit idType;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjet", fetch = FetchType.LAZY)
    private List<Demande> demandeList;

    public Objet() {
    }

    public Objet(Integer idObjet) {
        this.idObjet = idObjet;
    }

    public Integer getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(Integer idObjet) {
        this.idObjet = idObjet;
    }

    public String getLibObjet() {
        return libObjet;
    }

    public void setLibObjet(String libObjet) {
        this.libObjet = libObjet;
    }

    public Typecredit getIdType() {
        return idType;
    }

    public void setIdType(Typecredit idType) {
        this.idType = idType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjet != null ? idObjet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objet)) {
            return false;
        }
        Objet other = (Objet) object;
        if ((this.idObjet == null && other.idObjet != null) || (this.idObjet != null && !this.idObjet.equals(other.idObjet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Objet[ idObjet=" + idObjet + " ]";
    }
    
}
