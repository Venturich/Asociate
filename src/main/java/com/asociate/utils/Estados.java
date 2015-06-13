/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.utils;

/**
 *
 * @author Ventura
 */
public enum Estados {

    /**
     *
     */
    FINALIZADO("S"),

    /**
     *
     */
    NO_FINALIZADO("N"),
    
    /**
     *
     */
    PENDIENTE("P"),

    /**
     *
     */
    ASISTE("A"),

    /**
     *
     */
    NO_ASISTE("N"),

    /**
     *
     */
    QUIZA("Q");

    private final String valor;

    /**
     *
     * @return
     */
    public String getValor() {
        return valor;
    }

    private Estados(String valor) {
        this.valor = valor;
    }

}
