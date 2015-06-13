/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.validador;


import com.asociate.dao.AsociacionDAO;
import com.asociate.dao.UsuarioDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validador de los campos email de los formularios
 *
 * @author Ventura
 */
public class ValidadorCIF implements Validator {

    
    
    /**
     *
     */
    public ValidadorCIF() {
      
    }

    /**
     * Comprobador de email correcto
     *
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String txt = null;
        boolean error = false;
       if (existeCIF(value.toString())) {
            error = true;
            txt = "CIF ya registrado";
        }
        if (error) {
            FacesMessage msg = new FacesMessage(txt);
            msg.setSeverity(FacesMessage.SEVERITY_FATAL);
            throw new ValidatorException(msg);
        }
    }

    

    private boolean existeCIF(String value) {
        AsociacionDAO usdao = new AsociacionDAO();
        return usdao.comprobarCIF(value);
        
    }
}
