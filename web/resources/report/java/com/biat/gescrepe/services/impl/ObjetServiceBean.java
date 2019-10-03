package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.services.ObjetServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Objet;
import javax.ejb.Stateless;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class ObjetServiceBean extends BaseServiceBean<Objet,Integer> implements ObjetServiceBeanLocal {

    public ObjetServiceBean() {
        super(Objet.class);
    }

    
}
