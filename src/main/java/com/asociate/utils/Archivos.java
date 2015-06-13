/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.utils;

import java.io.File;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Ventura
 */
public class Archivos {
    private static String dir= FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/usuarios");
    /**
     *
     * @param idUsuario
     * @return
     */
    public static String comprobarFPerfl(Long idUsuario){
        String uri = dir+"/"+idUsuario+"\\perfil.jpg";
        File file = new File(uri);
        System.out.println("El archivo: "+file.getAbsolutePath()+" es "+file.exists());
        return file.exists()?file.getAbsolutePath():"";
    }

    public static void comprobarCarpetaUsuario(Long idUsuario) {
        File file =new File(dir+"/"+idUsuario);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
