/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validador de los campos NIF de los formularios
 * @author Ventura
 */
public class ValidadorNIF implements Validator {

    /**
     *
     */
    public static final String [] NIF_STRING_ASOCIATION ={ "T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};

    /**
     * Comprobador de NIF correcto
     * @param context
     * @param component
     * @param value
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {

        if (!nifValido(value)) {
            FacesMessage msg = new FacesMessage("El NIF no es correcto");
            msg.setSeverity(FacesMessage.SEVERITY_FATAL);
            throw new ValidatorException(msg);
        } 
        

    }

    private boolean letraCorrecta(int dni, String letra) {
        return (letra.equalsIgnoreCase(NIF_STRING_ASOCIATION[dni % 23]));
    }

    private boolean nifValido(Object value) {
        String nif = value.toString();
        if (nif.length() == 9) {
            try {
                int dni = Integer.parseInt(nif.substring(0, 8));
                String letra = nif.substring(8);
                return letraCorrecta(dni, letra);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }

    }

}
