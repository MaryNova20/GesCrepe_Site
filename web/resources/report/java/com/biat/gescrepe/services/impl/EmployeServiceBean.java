package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.services.EmployeServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.entities.Encours;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class EmployeServiceBean extends BaseServiceBean<Employe,Integer> implements EmployeServiceBeanLocal {

    public EmployeServiceBean() {
        super(Employe.class);
    }

    @Override
    public List<Encours> listencourspersonnel(Employe selemp) {
        Query q=this.em.createQuery("SELECT e from Encours e WHERE e.matricule=:param");
        q.setParameter("param", selemp);
        return q.getResultList();
    }

    
}
