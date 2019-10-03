package com.biat.gescrepe.entities;

import com.biat.gescrepe.entities.Demande;
import com.biat.gescrepe.entities.Typecredit;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Objet.class)
public class Objet_ { 

    public static volatile ListAttribute<Objet, Demande> demandeList;
    public static volatile SingularAttribute<Objet, String> libObjet;
    public static volatile SingularAttribute<Objet, Typecredit> idType;
    public static volatile SingularAttribute<Objet, Integer> idObjet;

}