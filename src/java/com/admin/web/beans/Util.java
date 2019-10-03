/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.web.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author desire.mensah
 */
public class Util {
    public static HttpSession getsession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    public static HttpServletRequest getrequest(){
        return  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
}
