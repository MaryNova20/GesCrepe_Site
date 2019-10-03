package com.biat.gescrepe.services;

import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.servicegeneric.BaseServiceBeanLocal;
import com.biat.gescrepe.entities.Encours;
import com.biat.gescrepe.reports.FicheComite;
import com.biat.gescrepe.reports.SubFour;
import com.biat.gescrepe.reports.SubThree;
import com.biat.gescrepe.reports.Submain;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MENSAH Y.O.D.
 */
@Local
public interface EncoursServiceBeanLocal extends BaseServiceBeanLocal<Encours, Integer>{
    
//    List<Object[]> comiteObjectslistobj(Employe selemploye);
//    List<FicheComite> comiteList(Employe selemploye);
    
    List<Object[]> comiteObjectslistobj(Employe selemploye);
    List<Submain> comiteList(Employe selemploye);
   
   List<Object[]> list4tobj(Employe selemploye);
   List<SubFour> list4(Employe selemploye); 
    
   List<Object[]> list3tobj(Employe selemploye);
    List<SubThree> list3(Employe selemploye); 
    
   
    
}