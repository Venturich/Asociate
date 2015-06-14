/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AsociacionDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Asociacion;
import com.asociate.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "adminGeneralMB")
@RequestScoped
public class AdminGeneralManagedBean extends AsociateError implements Serializable {

    private String goToAltaAsociacion = "altaAsociacion";
    private String goToBloqueo = "bloqueoUsuario";
    private String goToCopiaSeguridad = "copiaSeguridad";
    private String goToMenuPrincipal = "menuPrincipal";

    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;

    private Log logger = LogFactory.getLog(this.getClass().getName());
    private Flash flash;

    private StreamedContent descarga;
    private List<Asociacion> listaAsocPendiente;
    private List<Usuario> listaUsuarios;

    private AsociacionDAO asoDAO;

    /**
     * Creates a new instance of AdminGeneralManagedBean
     */
    public AdminGeneralManagedBean() {

    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        if (datosSesion.getUsuarioLogeado().getTipo().equals("A")) {
            //va a otra navegacion que no sea el menu
            logger.info("Es aadmin y tal");
            asoDAO = new AsociacionDAO();
            this.listaAsocPendiente = asoDAO.getListaAsocPendiente();
            if (flash.get("Destino") != null) {
                if (flash.get("Destino").equals("altaAsociacion")) {

                } else if (flash.get("Destino").equals("copiaSeguridad")) {

                } else if (flash.get("Destino").equals("bloqueoUsuario")) {
                    UsuarioDAO userdao = new UsuarioDAO();

                    this.listaUsuarios = userdao.getTodos();
                } else if (flash.get("Destino").equals("volver")) {

                }
            }
        } else {
            try {
                logger.info("Se da el piro");
                FacesContext.getCurrentInstance()
                        .getExternalContext().redirect("index.xhtml");
                return;
            } catch (IOException e) {

            }
        }
    }

    /**
     *
     * @param cif
     * @throws java.io.FileNotFoundException
     */
    public void descargarComprobante(String cif) throws FileNotFoundException {
        File file= new File("/home/daw/ASOCIATE/comprobante/" + cif + ".jpg");
        InputStream stream = new FileInputStream(file);
        //InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("D:/ASOCIATE/comprobante/" + cif + ".jpg");
        descarga = new DefaultStreamedContent(stream, "image/jpg", "comprobante_" + cif + ".jpg");
    }

    /**
     *
     * @param cif
     */
    public void okAsoc(String cif) {
        logger.info("Actualizamos"+cif);
        asoDAO = new AsociacionDAO();
        asoDAO.actualizarAsociacion(cif, "S");
        vaciarListaAsocPendiente(cif);

    }

    /**
     *
     * @param cif
     */
    public void noAsoc(String cif) {
        logger.info("Actualizamos"+cif);
        asoDAO = new AsociacionDAO();
        asoDAO.actualizarAsociacion(cif, "R");
        vaciarListaAsocPendiente(cif);
    }

    /**
     *
     * @return
     */
    public String altaAsociacion() {
        flash.put("Destino", goToAltaAsociacion);
        return goToAltaAsociacion;
    }

    /**
     *
     * @return
     */
    public String bloquearUsuarios() {
        flash.put("Destino", goToBloqueo);
        return goToBloqueo;
    }

    /**
     *
     * @return
     */
    public String copiaSeguridad() {
        flash.put("Destino", goToCopiaSeguridad);
        return goToCopiaSeguridad;
    }

    /**
     *
     * @param codigo
     * @param condicion
     */
    public void bloquearUsuario(Long codigo, boolean condicion) {
        UsuarioDAO userdao = new UsuarioDAO();
        userdao.bloquear(codigo, (condicion) ? "n" : "s");
        this.recargarListaUsuarios();
    }

    /**
     *
     */
    public void recargarListaUsuarios() {
        UsuarioDAO userdao = new UsuarioDAO();
        this.listaUsuarios = userdao.getTodos();
    }

    private void vaciarListaAsocPendiente(String cif) {
        for (Asociacion asoc : listaAsocPendiente) {
            if (asoc.getCif().equalsIgnoreCase(cif)) {
                listaAsocPendiente.remove(asoc);
                break;
            }
        }
    }

    /**
     *
     * @return
     */
    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    /**
     *
     * @param datosSesion
     */
    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }

    /**
     *
     * @return
     */
    public StreamedContent getDescarga() {
        return descarga;
    }

    /**
     *
     * @param descarga
     */
    public void setDescarga(StreamedContent descarga) {
        this.descarga = descarga;
    }

    /**
     *
     * @return
     */
    public List<Asociacion> getListaAsocPendiente() {
        return listaAsocPendiente;
    }

    /**
     *
     * @param listaAsocPendiente
     */
    public void setListaAsocPendiente(List<Asociacion> listaAsocPendiente) {
        this.listaAsocPendiente = listaAsocPendiente;
    }

    /**
     *
     * @return
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     *
     * @param listaUsuarios
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    
}
