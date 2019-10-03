package com.biat.gescrepe.services;

import com.biat.gescrepe.servicegeneric.BaseServiceBeanLocal;
import com.biat.gescrepe.entities.Demande;
import com.biat.gescrepe.reports.Suividemande;
import com.biat.gescrepe.reports.aprobSystematiq;
import com.biat.gescrepe.reports.demandeComite;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MENSAH Y.O.D.
 */
@Local
public interface DemandeServiceBeanLocal extends BaseServiceBeanLocal<Demande, Integer>{
    List<Demande> demandenondecaisse();
    List<Object[]> suividemandeobj(Date date1,Date date2);
    List<Suividemande> suividemandelist(Date date1,Date date2);
    
    List<Object[]> demandeComitelistobj(Date date1,Date date2);
    List<demandeComite> demandeComitelist(Date date1,Date date2);
    
    List<Object[]> aproSystematiquelistobj(Date date1,Date date2);
    List<aprobSystematiq> aproSystematiquelist(Date date1,Date date2);
    
   
}