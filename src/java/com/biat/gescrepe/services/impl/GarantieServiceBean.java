package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.services.GarantieServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Garantie;
import javax.ejb.Stateless;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class GarantieServiceBean extends BaseServiceBean<Garantie,Integer> implements GarantieServiceBeanLocal {

    public GarantieServiceBean() {
        super(Garantie.class);
    }

    
}
