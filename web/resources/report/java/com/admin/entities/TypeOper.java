/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entities;

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

/**
 *
 * @author firok
 */
@Entity
@Table(name = "TYPE_OPER")
public class TypeOper implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeOperId;
    
    @Column(name = "TYPE_OPER_DESC")
    private String typeOperDesc;
    
    @OneToMany( mappedBy = "typeOperId",fetch= FetchType.LAZY)
    private List<SysLog> sysLogs;

    public TypeOper() {
    }

    public String getTypeOperDesc() {
        return typeOperDesc;
    }

    public void setTypeOperDesc(String typeOperDesc) {
        this.typeOperDesc = typeOperDesc;
    }

    public List<SysLog> getSysLogs() {
        return sysLogs;
    }

    public void setSysLogs(List<SysLog> sysLogs) {
        this.sysLogs = sysLogs;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeOperId != null ? typeOperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeOper)) {
            return false;
        }
        TypeOper other = (TypeOper) object;
        if ((this.typeOperId == null && other.typeOperId != null) || (this.typeOperId != null && !this.typeOperId.equals(other.typeOperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeOper{" + "typeOperId=" + typeOperId + ", typeOperDesc=" + typeOperDesc + ", sysLogs=" + sysLogs + '}';
    }

   
    
}
