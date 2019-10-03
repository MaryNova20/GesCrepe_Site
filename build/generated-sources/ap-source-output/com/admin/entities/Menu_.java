package com.admin.entities;

import com.admin.entities.MenuItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile ListAttribute<Menu, MenuItem> sousMenuList;
    public static volatile SingularAttribute<Menu, Integer> menuId;
    public static volatile SingularAttribute<Menu, String> active;
    public static volatile SingularAttribute<Menu, String> menuDesc;

}