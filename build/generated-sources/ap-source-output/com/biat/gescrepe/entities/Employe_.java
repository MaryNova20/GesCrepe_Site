package com.biat.gescrepe.entities;

import com.biat.gescrepe.entities.Demande;
import com.biat.gescrepe.entities.Encours;
import com.biat.gescrepe.entities.Poste;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile ListAttribute<Employe, Demande> demandeList;
    public static volatile SingularAttribute<Employe, Date> dateExtraction;
    public static volatile SingularAttribute<Employe, Integer> indice;
    public static volatile SingularAttribute<Employe, BigDecimal> salaire;
    public static volatile SingularAttribute<Employe, Poste> idPoste;
    public static volatile SingularAttribute<Employe, Integer> matricule;
    public static volatile SingularAttribute<Employe, Date> dateEmb;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile ListAttribute<Employe, Encours> encoursList;

}