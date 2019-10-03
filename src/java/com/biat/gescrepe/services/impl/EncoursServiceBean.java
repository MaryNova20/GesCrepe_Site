package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.services.EncoursServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Encours;
import com.biat.gescrepe.reports.FicheComite;
import com.biat.gescrepe.reports.RemplisseurListe;
import com.biat.gescrepe.reports.SubFour;
import com.biat.gescrepe.reports.SubThree;
import com.biat.gescrepe.reports.Submain;
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
    public List<Submain> comiteList(Employe selemploye) {
        return RemplisseurListe.remplireListeSensSomme(comiteObjectslistobj(selemploye),new Submain());
        

    }

    @Override
    public List<Object[]> list4tobj(Employe selemploye) {
        
        String req2;
        req2 = "SELECT b.objet as type,\n" +
"b.montantOctroye as encours,\n" +
"b.mensualite\n" +
"FROM employe a\n" +
"LEFT JOIN encours b\n" +
"On a.matricule= b.matricule\n" +
"where a.matricule = ?1";
        
         Query q2=this.em.createNativeQuery(req2);
        q2.setParameter(1,selemploye.getMatricule());
        
        return q2.getResultList();
    
    }

    @Override
    public List<SubFour> list4(Employe selemploye) {
     return RemplisseurListe.remplireListeSensSomme(list4tobj(selemploye),new SubFour());  
    }

    @Override
    public List<Object[]> list3tobj(Employe selemploye) {
String req1;
        req1 = "SELECT a.indice,\n" +
"b.numCredit as numcredit,\n" +
"b.reste,\n" +
"b.objet as typecredit,\n" +
"b.montantOctroye as mtoctroye,\n" +
"c.montantCessible as mtdemande,\n" +
"b.validite\n" +
"FROM employe a\n" +
"LEFT JOIN encours b\n" +
"On a.matricule= b.matricule\n" +
"LEFT JOIN demande c On A.matricule= c.matricule\n" +
"where a.matricule = ?1";
        
         Query q1=this.em.createNativeQuery(req1);
        q1.setParameter(1,selemploye.getMatricule());
        
        return q1.getResultList();    }

    @Override
    public List<SubThree> list3(Employe selemploye) {
    return RemplisseurListe.remplireListeSensSomme(list3tobj(selemploye),new SubThree());        }

    
    
   
    

    
   
}

    
