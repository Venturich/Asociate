/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import javax.inject.Named;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "adminGeneralManagedBean")
@SessionScoped
public class AdminGeneralManagedBean extends AsociateError implements Serializable {

    /**
     * Creates a new instance of AdminGeneralManagedBean
     */
    public AdminGeneralManagedBean() {
    }
    
}
