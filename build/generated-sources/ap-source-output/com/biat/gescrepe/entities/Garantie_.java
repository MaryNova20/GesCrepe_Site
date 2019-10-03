package com.biat.gescrepe.entities;

import com.biat.gescrepe.entities.Demande;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Garantie.class)
public class Garantie_ { 

    public static volatile SingularAttribute<Garantie, Integer> idGarantie;
    public static volatile ListAttribute<Garantie, Demande> demandeList;
    public static volatile SingularAttribute<Garantie, String> libGarantie;

}