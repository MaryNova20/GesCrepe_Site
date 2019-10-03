package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.services.EncoursServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Encours;
import com.biat.gescrepe.reports.FicheComite;
import com.biat.gescrepe.reports.RemplisseurListe;
import com.biat.gescrepe.reports.SubFour;
import com.biat.gescrepe.reports.SubThree;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class EncoursServiceBean extends BaseServiceBean<Encours,Integer> implements EncoursServiceBeanLocal {

    public EncoursServiceBean() {
        super(Encours.class);
    }

    @Override
    public List<Object[]> comiteObjectslistobj(Employe selemploye) {
        
        String req;
        req = "SELECT objet as type, \n" +
                "dateMisePlace as datemiseplace, \n" +
                "montantOctroye as mtoctroye, \n" +
                "reste, \n" +
                "mensualite, \n" +
                "validite\n" +
                "From encours \n" +
                "WHERE matricule = ?1 ";
        
         Query q=this.em.createNativeQuery(req);
        q.setParameter(1,selemploye.getMatricule());
        
        return q.getResultList();
    }

    @Override
    public List<FicheComite> comiteList(Employe selemploye) {
        return RemplisseurListe.remplireListeSensSomme(comiteObjectslistobj(selemploye),new FicheComite());
        

    }

    @Override
    public List<Object[]> list4tobj(Employe selemploye) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubFour> list4(Employe selemploye) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> list3tobj(Employe selemploye) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubThree> list3(Employe selemploye) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
   
    

    
   
}

    
