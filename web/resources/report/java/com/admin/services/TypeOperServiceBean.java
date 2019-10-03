/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.services;

import com.admin.dao.BaseDaoBeanLocal;
import com.admin.dao.TypeOperDAOBeanLocal;
import com.admin.entities.TypeOper;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author firok
 */
@Stateless
public class TypeOperServiceBean extends BaseServiceBean<TypeOper, Integer> implements TypeOperServiceBeanLocal {

    @EJB
    private TypeOperDAOBeanLocal typeOperDAO;

    @Override
    protected BaseDaoBeanLocal<TypeOper, Integer> getDao() {
        return this.typeOperDAO;
    }

    @Override
    public TypeOper typeoperselectionner(String type) {
        return this.typeOperDAO.typeoperselectionner(type);
    }

}
