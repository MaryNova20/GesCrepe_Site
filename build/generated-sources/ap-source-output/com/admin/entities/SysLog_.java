package com.admin.entities;

import com.admin.entities.TypeOper;
import com.biat.gescrepe.entities.Utilisateurs;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2019-09-27T20:57:20")
@StaticMetamodel(SysLog.class)
public class SysLog_ { 

    public static volatile SingularAttribute<SysLog, Utilisateurs> iduser;
    public static volatile SingularAttribute<SysLog, Integer> logId;
    public static volatile SingularAttribute<SysLog, Date> logDate;
    public static volatile SingularAttribute<SysLog, TypeOper> typeOperId;
    public static volatile SingularAttribute<SysLog, String> logDesc;

}