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
public enum Notificaciones implements Serializable {

   REGASOC("A"),
   REGUSU("U"),
   NUEVOEVENTO("E"),
   EVENTOCOMENTARIO("X"),
   COMENTARIO("C"),
   MENSAJE("M"),
   NUEVOFICHERO("F"),
   NUEVOAMIGO("L");
    
    private final String valor;

    public String getValor() {
        return valor;
    }
    private Notificaciones(String valor){
        this.valor=valor;
    }
    
}
