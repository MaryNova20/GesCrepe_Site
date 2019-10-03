package com.biat.gescrepe.entities;

import com.biat.gescrepe.entities.Objet;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Typecredit.class)
public class Typecredit_ { 

    public static volatile SingularAttribute<Typecredit, Integer> idType;
    public static volatile SingularAttribute<Typecredit, String> nature;
    public static volatile ListAttribute<Typecredit, Objet> objetList;
    public static volatile SingularAttribute<Typecredit, Integer> duree;

}