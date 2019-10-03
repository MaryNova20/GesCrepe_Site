/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.services;

import com.admin.dao.BaseDaoBeanLocal;
import com.admin.dao.SysLogDAOBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.admin.entities.SysLog;

/**
 *
 * @author firok
 */
@Stateless
public class SysLogServiceBean extends BaseServiceBean<SysLog, Integer> implements SysLogServiceBeanLocal {

    
    @EJB
    private SysLogDAOBeanLocal sysLogDAO;
    
   

    @Override
    protected BaseDaoBeanLocal<SysLog, Integer> getDao() {
        return this.sysLogDAO;
    }

    

}
