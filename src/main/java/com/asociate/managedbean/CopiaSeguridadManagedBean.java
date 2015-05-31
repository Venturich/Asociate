/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "copiaSeguridadMB")
@ViewScoped
public class CopiaSeguridadManagedBean  implements Serializable {

    private final String comando = "mysqldump";
    private final String database = "ASOCIATE";
    private final String usuario = "userjava";
    private final String pass = "2015";
    private final String ruta = "D:\\ASOCIATE\\copiaSeguridad";
    private String[] ficheros;
    private String resultado;

    /**
     * Creates a new instance of CopiaSeguridadManagedBean
     */
    public CopiaSeguridadManagedBean() {
    }

    /**
     * Configuración inicial de CopiaSeguridad
     */
    @PostConstruct
    public void init() {
        //this.ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/copiasSeguridad");
        ficheros = new File(ruta).list();

    }

    /**
     * Ejecuta el comando mysqldump y guarda el fichero de salida generado en el
     * directorio correspondiente
     */
    public void crearCopia() {

        Date dia = new Date();
        String fecha = new SimpleDateFormat("dd-MM-yy").format(dia);
        String cmd;

        try {
            cmd = ("" + comando + " " + " -u" + usuario + " -p" + pass + " " + database + " -r " + ruta + "/CopiaSeguridad_" + fecha + ".sql");
            System.out.println(cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            int exito = p.waitFor();
            if (exito == 0) {
                resultado = "Exito en el volcado de la base de datos";
                ficheros = new File(ruta).list();
            } else {
                resultado = "¡Error en el volcado de la base de datos!";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Elimina una copia de seguridad del directorio
     *
     * @param nombre
     */
    public void borrarCopia(String nombre) {
        File file = new File(ruta + "/" + nombre);
        file.delete();

        ficheros = new File(ruta).list();
    }

    /**
     * Ejecuta el script de restauración de la copia de seguridad de la base de
     * datos
     *
     * @param archivo
     */
    public void restaurarCopia(String archivo) {

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"shell", "-c", "mysql -u" + usuario + " -p" + pass + " " + database + " < " + archivo});

            int exito = p.waitFor();
            if (exito == 0) {
                resultado = "Exito en la restauracion de la base de datos";
            } else {
                resultado = "¡Error en la restauracion de la base de datos!";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String getResultado() {
        return resultado;
    }

    /**
     *
     * @param resultado
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     *
     * @return
     */
    public String[] getFicheros() {
        return ficheros;
    }

    /**
     *
     * @param ficheros
     */
    public void setFicheros(String[] ficheros) {
        this.ficheros = ficheros;
    }

}
