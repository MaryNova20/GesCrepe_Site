package com.admin.entities;

import com.admin.entities.ProfilMenuItem;
import com.biat.gescrepe.entities.Utilisateurs;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Profil.class)
public class Profil_ { 

    public static volatile SingularAttribute<Profil, String> profilDesc;
    public static volatile ListAttribute<Profil, ProfilMenuItem> droitList;
    public static volatile SingularAttribute<Profil, Integer> profilId;
    public static volatile SingularAttribute<Profil, String> active;
    public static volatile ListAttribute<Profil, Utilisateurs> utilisateurlist;

}