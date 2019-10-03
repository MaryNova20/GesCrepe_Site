/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entities.MenuItem;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author firok
 */
@Local
public interface MenuItemDAOBeanLocal extends BaseDaoBeanLocal<MenuItem, Integer> {
   List<MenuItem> menunonaffectes(int pr);
  
    
}
