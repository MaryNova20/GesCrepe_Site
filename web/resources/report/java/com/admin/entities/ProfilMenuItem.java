/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desire.mensah
 */
@Entity
@Table(name = "DROIT")
@XmlRootElement
public class ProfilMenuItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDROIT")
    private Integer iddroit;
    @Size(max = 255)
    @Column(name = "ACTIVE")
    private String active;
    @Size(max = 255)
    @Column(name = "DROIT")
    private String droit;
    @JoinColumn(name = "PROFIL_ID", referencedColumnName = "PROFIL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Profil profilId;
    @JoinColumn(name = "MENU_ITEM_ID", referencedColumnName = "MENU_ITEM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItemId;

    public ProfilMenuItem() {
    }

    public ProfilMenuItem(Integer iddroit) {
        this.iddroit = iddroit;
    }

    public Integer getIddroit() {
        return iddroit;
    }

    public void setIddroit(Integer iddroit) {
        this.iddroit = iddroit;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDroit() {
        return droit;
    }

    public void setDroit(String droit) {
        this.droit = droit;
    }

    public Profil getProfilId() {
        return profilId;
    }

    public void setProfilId(Profil profilId) {
        this.profilId = profilId;
    }

    public MenuItem getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(MenuItem menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddroit != null ? iddroit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfilMenuItem)) {
            return false;
        }
        ProfilMenuItem other = (ProfilMenuItem) object;
        if ((this.iddroit == null && other.iddroit != null) || (this.iddroit != null && !this.iddroit.equals(other.iddroit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admin.entities.Droit[ iddroit=" + iddroit + " ]";
    }
    
}
