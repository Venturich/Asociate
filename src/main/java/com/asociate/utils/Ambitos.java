/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.utils;

import java.io.Serializable;

/**
 *
 * @author Ventura
 */
public enum Ambitos implements Serializable {

    JUVENIL("J"),
    DEPORTIVA("D"),
    CULTURAL("U"),
    ESTUDIANTIL("E"),
    CLUB("C"),
    OTRO("O");
    
    private final String valor;

    public String getValor() {
        return valor;
    }
    
    private Ambitos(String valor){
        this.valor=valor;
    }

}
