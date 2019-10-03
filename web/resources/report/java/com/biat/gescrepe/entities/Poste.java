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
@Table(name = "poste")
@NamedQueries({
    @NamedQuery(name = "Poste.findAll", query = "SELECT p FROM Poste p")})
public class Poste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPoste")
    private Integer idPoste;
    @Lob
    @Size(max = 65535)
    @Column(name = "libPoste")
    private String libPoste;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPoste", fetch = FetchType.LAZY)
    private List<Employe> employeList;

    public Poste() {
    }

    public Poste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public String getLibPoste() {
        return libPoste;
    }

    public void setLibPoste(String libPoste) {
        this.libPoste = libPoste;
    }

    public List<Employe> getEmployeList() {
        return employeList;
    }

    public void setEmployeList(List<Employe> employeList) {
        this.employeList = employeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoste != null ? idPoste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poste)) {
            return false;
        }
        Poste other = (Poste) object;
        if ((this.idPoste == null && other.idPoste != null) || (this.idPoste != null && !this.idPoste.equals(other.idPoste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biat.gescrepe.entities.Poste[ idPoste=" + idPoste + " ]";
    }
    
}
