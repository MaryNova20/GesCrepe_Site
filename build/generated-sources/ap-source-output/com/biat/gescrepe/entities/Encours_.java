package com.biat.gescrepe.entities;

import com.biat.gescrepe.entities.Employe;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Encours.class)
public class Encours_ { 

    public static volatile SingularAttribute<Encours, BigDecimal> montantOctroye;
    public static volatile SingularAttribute<Encours, String> objet;
    public static volatile SingularAttribute<Encours, Integer> idEncours;
    public static volatile SingularAttribute<Encours, BigDecimal> reste;
    public static volatile SingularAttribute<Encours, Date> dateComptable;
    public static volatile SingularAttribute<Encours, BigDecimal> mensualite;
    public static volatile SingularAttribute<Encours, Integer> numCredit;
    public static volatile SingularAttribute<Encours, Employe> matricule;

}