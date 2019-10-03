package com.biat.gescrepe.services;




import com.biat.gescrepe.entities.Utilisateurs;
import com.biat.gescrepe.servicegeneric.BaseServiceBeanLocal;
import javax.ejb.Local;

/**
 *
 * @author MENSAH Y.O.D.
 */
@Local
public interface UtilisateursServiceBeanLocal extends BaseServiceBeanLocal<Utilisateurs, Integer>{
    Utilisateurs findUser(String username,String password);
}
