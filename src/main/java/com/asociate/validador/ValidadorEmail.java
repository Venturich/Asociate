/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.validador;


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
public class ValidadorEmail implements Validator {

    private final String patron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;
    
    
    public ValidadorEmail() {
        pattern = Pattern.compile(patron);
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
        if (!formatoEmail(value.toString())) {
            error = true;
            txt = "Formato de email incorrecto";

        } else if (existeEmail(value.toString())) {
            error = true;
            txt = "Email ya registrado";
        }
        if (error) {
            FacesMessage msg = new FacesMessage(txt);
            msg.setSeverity(FacesMessage.SEVERITY_FATAL);
            throw new ValidatorException(msg);
        }
    }

    private boolean formatoEmail(String value) {
        matcher= pattern.matcher(value);
        return matcher.matches();
    }

    private boolean existeEmail(String value) {
        UsuarioDAO usdao = new UsuarioDAO();
        return usdao.comprobarEmail(value);
        
    }
}
