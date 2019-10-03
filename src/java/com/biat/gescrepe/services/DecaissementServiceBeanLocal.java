package com.biat.gescrepe.services;

import com.biat.gescrepe.servicegeneric.BaseServiceBeanLocal;
import com.biat.gescrepe.entities.Decaissement;
import com.biat.gescrepe.reports.Remontees;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MENSAH Y.O.D.
 */
@Local
public interface DecaissementServiceBeanLocal extends BaseServiceBeanLocal<Decaissement, Integer>{
    
    List<Object[]> RemonObjects(Date date1,Date date2);
    List<Remontees> Remontelist(Date date1,Date date2);
}