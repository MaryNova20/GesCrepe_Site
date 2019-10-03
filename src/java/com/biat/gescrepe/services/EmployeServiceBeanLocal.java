package com.biat.gescrepe.services;

import com.biat.gescrepe.servicegeneric.BaseServiceBeanLocal;
import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.entities.Encours;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MENSAH Y.O.D.
 */
@Local
public interface EmployeServiceBeanLocal extends BaseServiceBeanLocal<Employe, Integer>{
    List<Encours> listencourspersonnel(Employe selemp);
}