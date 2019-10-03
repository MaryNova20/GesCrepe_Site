/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entities.Menu;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import java.util.Collection;
import javax.ejb.Stateless;


/**
 *
 * @author firok
 */
@Stateless
public class MenuDAOBean extends BaseServiceBean<Menu,Integer> implements MenuDAOBeanLocal {

    public MenuDAOBean() {
        super(Menu.class);
    }

    @Override
    public void ajouter(Collection<Menu> ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
