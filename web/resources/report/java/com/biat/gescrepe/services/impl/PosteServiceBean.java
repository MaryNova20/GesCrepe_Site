package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.services.PosteServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Poste;
import javax.ejb.Stateless;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class PosteServiceBean extends BaseServiceBean<Poste,Integer> implements PosteServiceBeanLocal {

    public PosteServiceBean() {
        super(Poste.class);
    }

    
}
