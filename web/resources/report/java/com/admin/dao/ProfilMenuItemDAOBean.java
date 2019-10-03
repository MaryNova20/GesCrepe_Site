/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entities.ProfilMenuItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author firok
 */
@Stateless
public class ProfilMenuItemDAOBean extends BaseDaoBean<ProfilMenuItem, Integer> implements ProfilMenuItemDAOBeanLocal {

    public ProfilMenuItemDAOBean() {
        super(ProfilMenuItem.class);
    }

    @Override
    public List<ProfilMenuItem> selectionnerListeProfilMenuItem(int profilId) {
        Query q = this.em.createQuery("SELECT p FROM ProfilMenuItem p WHERE p.profilId.profilId=:valeur");
        q.setParameter("valeur", profilId);
        return q.getResultList();
    }
}
