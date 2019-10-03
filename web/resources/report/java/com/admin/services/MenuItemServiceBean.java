/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.services;

import com.admin.dao.BaseDaoBeanLocal;
import com.admin.dao.MenuItemDAOBeanLocal;
import com.admin.entities.MenuItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author firok
 */
@Stateless
public class MenuItemServiceBean extends BaseServiceBean<MenuItem, Integer> implements MenuItemServiceBeanLocal {

    @EJB
    private MenuItemDAOBeanLocal menuItemDAO;

    @Override
    protected BaseDaoBeanLocal<MenuItem, Integer> getDao() {
        return this.menuItemDAO;
    }

    @Override
    public List<MenuItem> menunonaffectes(int pr) {
        return this.menuItemDAO.menunonaffectes(pr);
    }

}
