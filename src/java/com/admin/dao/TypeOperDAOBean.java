/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entities.TypeOper;
import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author desire
 */
@Stateless
public class TypeOperDAOBean extends BaseDaoBean<TypeOper, Integer> implements TypeOperDAOBeanLocal {

    public TypeOperDAOBean() {
        super(TypeOper.class);
    }

    @Override
    public TypeOper typeoperselectionner(String type) {
        Query q=this.em.createQuery("SELECT t FROM TypeOper t WHERE t.typeOperDesc=:valeur");
        q.setParameter("valeur", type);
        return (TypeOper) q.getSingleResult();
    }

    
}
