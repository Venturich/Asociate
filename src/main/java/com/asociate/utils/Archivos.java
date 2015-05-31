/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.utils;

import java.io.File;

/**
 *
 * @author Ventura
 */
public class Archivos {
    
    public static String comprobarFPerfl(Long idUsuario){
        File file = new File("D:\\ASOCIATE\\usuarios\\"+idUsuario+"\\perfil.jpg");
        return file.exists()?file.getAbsolutePath():"";
    }
}
