package com.biat.gescrepe.services;

import com.biat.gescrepe.servicegeneric.BaseServiceBeanLocal;
import com.biat.gescrepe.entities.Poste;
import javax.ejb.Local;

/**
 *
 * @author MENSAH Y.O.D.
 */
@Local
public interface PosteServiceBeanLocal extends BaseServiceBeanLocal<Poste, Integer>{
    
}