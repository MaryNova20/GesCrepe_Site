package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.services.TypecreditServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Typecredit;
import javax.ejb.Stateless;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class TypecreditServiceBean extends BaseServiceBean<Typecredit,Integer> implements TypecreditServiceBeanLocal {

    public TypecreditServiceBean() {
        super(Typecredit.class);
    }

    
}
