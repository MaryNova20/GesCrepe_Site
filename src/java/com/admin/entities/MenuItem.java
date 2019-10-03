/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desire.mensah
 */
@Entity
@Table(name = "SOUS_MENU")
@XmlRootElement
public class MenuItem implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ITEM_ID")
    private Integer menuItemId;
    @Size(max = 255)
    @Column(name = "ACTIVE")
    private String active;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Size(max = 255)
    @Column(name = "MENU_ITEM_DESC")
    private String menuItemDesc;
    @Column(name = "NUM_ORDRE")
    private Integer numOrdre;
    @OneToMany(mappedBy = "menuItemId", fetch = FetchType.LAZY)
    private List<ProfilMenuItem> droitList;
    @JoinColumn(name = "MENU_ID", referencedColumnName = "MENU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menuId;

    public MenuItem() {
    }

    public MenuItem(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public MenuItem(Integer numOrdre, String menuItemDesc) {
        this.menuItemDesc = menuItemDesc;
        this.numOrdre = numOrdre;
    }
    

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getMenuItemDesc() {
        return menuItemDesc;
    }

    public void setMenuItemDesc(String menuItemDesc) {
        this.menuItemDesc = menuItemDesc;
    }

    public Integer getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }

    @XmlTransient
    public List<ProfilMenuItem> getDroitList() {
        return droitList;
    }

    public void setDroitList(List<ProfilMenuItem> droitList) {
        this.droitList = droitList;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuItemId != null ? menuItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.menuItemId == null && other.menuItemId != null) || (this.menuItemId != null && !this.menuItemId.equals(other.menuItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admin.entities.SousMenu[ menuItemId=" + menuItemId + " ]";
    }
    
}
