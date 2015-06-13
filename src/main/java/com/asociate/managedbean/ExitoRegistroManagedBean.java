/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.UsuarioDAO;
import com.asociate.utils.Email;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "exitoRegistroMB")
@ViewScoped
public class ExitoRegistroManagedBean {

    private Flash flash;
    private String email;
    private String mensaje;

    /**
     * Creates a new instance of ExitoRegistroManagedBean
     */
    public ExitoRegistroManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
         mensaje="En breve recibiras un email con la confirmación de tu registro.";
        if (flash.get("Email") != null) {
            this.email = (String) flash.get("Email");

            mandarEmailRegistro(this.email);
        }

    }

    /**
     *
     * @param email
     */
    public void mandarEmailRegistro(String email) {
        String []params={email};
        Email.SendMail(Email.getMensajeDePlantilla(1, params), email, "Registro Asociate");
    }

    /**
     *
     * @param email
     * @param idUsuario
     */
    public void mandarEmailConfirmacion(String email, Long idUsuario) {
        UsuarioDAO usuDAO = new UsuarioDAO();
        
        //mandar email;
        
        usuDAO.setRegistroCompleto(idUsuario);
        
    }

}
