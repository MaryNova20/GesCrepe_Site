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
import javax.persistence.Lob;
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
@Table(name = "garantie")
@NamedQueries({
    @NamedQuery(name = "Garantie.findAll", query = "SELECT g FROM Garantie g")})
public class Garantie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGarantie")
    private Integer idGarantie;
    @Lob
    @Size(max = 65535)
    @Column(name = "libGarantie")
    private String libGarantie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGarantie", fetch = FetchType.LAZY)
    private List<Demande> demandeList;

    public Garantie() {
    }

    public Garantie(Integer idGarantie) {
        this.idGarantie = idGarantie;
    }

    public Integer getIdGarantie() {
        return idGarantie;
    }

    public void setIdGarantie(Integer idGarantie) {
        this.idGarantie = idGarantie;
    }

    public String getLibGarantie() {
        return libGarantie;
    }

    public void setLibGarantie(String libGarantie) {
        this.libGarantie = libGarantie;
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
        hash += (idGarantie != null ? idGarantie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garantie)) {
            return false;
        }
        Garantie other = (Garantie) object;
        if ((this.idGarantie == null && other.idGarantie != null) || (this.idGarantie != null && !this.idGarantie.equals(other.idGarantie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Garantie[ idGarantie=" + idGarantie + " ]";
    }
    
}
