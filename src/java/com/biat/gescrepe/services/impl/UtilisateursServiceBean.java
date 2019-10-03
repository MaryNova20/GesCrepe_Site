package com.biat.gescrepe.services.impl;





import com.biat.gescrepe.entities.Utilisateurs;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.services.UtilisateursServiceBeanLocal;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class UtilisateursServiceBean extends BaseServiceBean<Utilisateurs,Integer> implements UtilisateursServiceBeanLocal {

    public UtilisateursServiceBean() {
        super(Utilisateurs.class);
    }

    @Override
    public Utilisateurs findUser(String username, String password) {
         Query q=this.em.createQuery("SELECT u FROM Utilisateurs u WHERE u.loginuser=:param1 AND u.pwduser=:param2");
        q.setParameter("param1", username);
        q.setParameter("param2", password);
        return (Utilisateurs) q.getSingleResult();
    }

    
}
