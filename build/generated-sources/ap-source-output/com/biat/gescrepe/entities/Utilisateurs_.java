package com.biat.gescrepe.entities;

import com.admin.entities.Profil;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Utilisateurs.class)
public class Utilisateurs_ { 

    public static volatile SingularAttribute<Utilisateurs, Integer> iduser;
    public static volatile SingularAttribute<Utilisateurs, String> loginuser;
    public static volatile SingularAttribute<Utilisateurs, Profil> profilId;
    public static volatile SingularAttribute<Utilisateurs, String> pwduser;
    public static volatile SingularAttribute<Utilisateurs, String> nomuser;

}