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
@Table(name = "typecredit")
@NamedQueries({
    @NamedQuery(name = "Typecredit.findAll", query = "SELECT t FROM Typecredit t")})
public class Typecredit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idType")
    private Integer idType;
    @Size(max = 254)
    @Column(name = "nature")
    private String nature;
    @Column(name = "duree")
    private Integer duree;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idType", fetch = FetchType.LAZY)
    private List<Objet> objetList;
   

    public Typecredit() {
    }

    public Typecredit(Integer idType) {
        this.idType = idType;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public List<Objet> getObjetList() {
        return objetList;
    }

    public void setObjetList(List<Objet> objetList) {
        this.objetList = objetList;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typecredit)) {
            return false;
        }
        Typecredit other = (Typecredit) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Typecredit[ idType=" + idType + " ]";
    }
    
}
