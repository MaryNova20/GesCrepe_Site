package com.admin.entities;

import com.admin.entities.MenuItem;
import com.admin.entities.Profil;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(ProfilMenuItem.class)
public class ProfilMenuItem_ { 

    public static volatile SingularAttribute<ProfilMenuItem, MenuItem> menuItemId;
    public static volatile SingularAttribute<ProfilMenuItem, Profil> profilId;
    public static volatile SingularAttribute<ProfilMenuItem, String> active;
    public static volatile SingularAttribute<ProfilMenuItem, Integer> iddroit;
    public static volatile SingularAttribute<ProfilMenuItem, String> droit;

}