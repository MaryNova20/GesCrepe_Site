/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entities;
import com.biat.gescrepe.entities.Utilisateurs;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desire.mensah
 */
@Entity
@Table(name = "PROFIL")
@XmlRootElement
public class Profil implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFIL_ID")
    private Integer profilId;
    @Size(max = 255)
    @Column(name = "ACTIVE")
    private String active;
    @Size(max = 255)
    @Column(name = "PROFIL_DESC")
    private String profilDesc;
    @OneToMany(mappedBy = "profilId", fetch = FetchType.LAZY)
    private List<ProfilMenuItem> droitList;
    @OneToMany( mappedBy = "profilId", fetch = FetchType.LAZY)
    private List<Utilisateurs> utilisateurlist;
    public Profil() {
    }

    public Profil(Integer profilId) {
        this.profilId = profilId;
    }

    public Integer getProfilId() {
        return profilId;
    }

    public void setProfilId(Integer profilId) {
        this.profilId = profilId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getProfilDesc() {
        return profilDesc;
    }

    public void setProfilDesc(String profilDesc) {
        this.profilDesc = profilDesc;
    }

    @XmlTransient
    public List<ProfilMenuItem> getDroitList() {
        return droitList;
    }

    public void setDroitList(List<ProfilMenuItem> droitList) {
        this.droitList = droitList;
    }

    public List<Utilisateurs> getUtilisateurlist() {
        return utilisateurlist;
    }

    public void setUtilisateurlist(List<Utilisateurs> utilisateurlist) {
        this.utilisateurlist = utilisateurlist;
    }

  
  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profilId != null ? profilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.profilId == null && other.profilId != null) || (this.profilId != null && !this.profilId.equals(other.profilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admin.entities.Profil[ profilId=" + profilId + " ]";
    }
    
}
