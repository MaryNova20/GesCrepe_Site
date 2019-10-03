package com.biat.gescrepe.entities;

import com.biat.gescrepe.entities.Demande;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Decaissement.class)
public class Decaissement_ { 

    public static volatile SingularAttribute<Decaissement, BigDecimal> montantDecaisse;
    public static volatile SingularAttribute<Decaissement, Demande> idDemande;
    public static volatile SingularAttribute<Decaissement, Integer> idDecaissement;
    public static volatile SingularAttribute<Decaissement, Date> dateDecaissement;

}