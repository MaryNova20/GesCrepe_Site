/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entities.MenuItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author firok
 */
@Stateless
public class MenuItemDAOBean extends BaseDaoBean<MenuItem, Integer> implements MenuItemDAOBeanLocal {

    public MenuItemDAOBean() {
        super(MenuItem.class);
    }

    @Override
    public List<MenuItem> menunonaffectes(int pr) {
        String nativequery="SELECT *FROM sous_menu \n" +
"WHERE\n" +
"MENU_ITEM_ID not IN\n" +
"(SELECT MENU_ITEM_ID FROM droit WHERE PROFIL_ID=?1)";
                Query q = this.em.createNativeQuery(nativequery,MenuItem.class);
              q.setParameter(1, pr);
              return q.getResultList();
    }

   
  
    

}
