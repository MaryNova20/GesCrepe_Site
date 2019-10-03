package com.admin.entities;

import com.admin.entities.Menu;
import com.admin.entities.ProfilMenuItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(MenuItem.class)
public class MenuItem_ { 

    public static volatile SingularAttribute<MenuItem, Integer> menuItemId;
    public static volatile SingularAttribute<MenuItem, Date> dateCreation;
    public static volatile ListAttribute<MenuItem, ProfilMenuItem> droitList;
    public static volatile SingularAttribute<MenuItem, String> active;
    public static volatile SingularAttribute<MenuItem, Menu> menuId;
    public static volatile SingularAttribute<MenuItem, Integer> numOrdre;
    public static volatile SingularAttribute<MenuItem, String> menuItemDesc;

}