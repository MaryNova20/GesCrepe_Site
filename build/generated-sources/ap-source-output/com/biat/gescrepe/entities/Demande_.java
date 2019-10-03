package com.biat.gescrepe.entities;

import com.biat.gescrepe.entities.Decaissement;
import com.biat.gescrepe.entities.Employe;
import com.biat.gescrepe.entities.Garantie;
import com.biat.gescrepe.entities.Objet;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Demande.class)
public class Demande_ { 

    public static volatile SingularAttribute<Demande, Garantie> idGarantie;
    public static volatile SingularAttribute<Demande, BigDecimal> montantCessible;
    public static volatile SingularAttribute<Demande, String> appreciation;
    public static volatile SingularAttribute<Demande, Integer> idDemande;
    public static volatile SingularAttribute<Demande, BigDecimal> quotiteCessible;
    public static volatile ListAttribute<Demande, Decaissement> decaissementList;
    public static volatile SingularAttribute<Demande, Date> dateDemande;
    public static volatile SingularAttribute<Demande, Objet> idObjet;
    public static volatile SingularAttribute<Demande, String> decision;
    public static volatile SingularAttribute<Demande, String> commentaire;
    public static volatile SingularAttribute<Demande, Employe> matricule;
    public static volatile SingularAttribute<Demande, BigDecimal> quotiteAtteinte;

}