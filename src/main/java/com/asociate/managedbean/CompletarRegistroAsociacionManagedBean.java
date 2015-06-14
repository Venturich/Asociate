/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AsociacionDAO;
import com.asociate.dao.DirectorioDAO;
import com.asociate.dao.NotificacionDAO;
import com.asociate.dao.PersonaDAO;
import com.asociate.dao.ProvinciaDAO;
import com.asociate.dao.PuebloDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Asociacion;
import com.asociate.modelo.Directorio;
import com.asociate.modelo.Pais;
import com.asociate.modelo.Persona;
import com.asociate.modelo.Provincia;
import com.asociate.modelo.Pueblo;
import com.asociate.modelo.Usuario;
import com.asociate.utils.Ambitos;
import com.asociate.utils.Notificaciones;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "completarRegistroAsocMB")
@SessionScoped
public class CompletarRegistroAsociacionManagedBean extends AsociateError implements Serializable {

    private final String goToExito = "exitoRegistro";
    private final String goToInicio = "index";

    private Log logger = LogFactory.getLog(this.getClass().getName());
    private Flash flash;
    private UploadedFile comprobante;
    private NotificacionDAO notifDAO;
    private UsuarioDAO userDAO;
    private AsociacionDAO asocDAO;
    private PersonaDAO persDAO;
    private Asociacion regAsoc;
    private String prueba;
    private Usuario regUser;

    private Boolean verDescripcion;
    private Long idPersonaAsoc;

    /**
     * Creates a new instance of completarRegistroManagedBean
     */
    public CompletarRegistroAsociacionManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        logger.error("Compltar Asociacion");
        prueba="Un último paso para registrar tu asociacion... ";
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        if(flash==null){
            logger.error("flash es nulo");
        }
        if (flash.get("Asociacion") != null) {
            regAsoc = (Asociacion) flash.get("Asociacion");
        } else {
            regAsoc = new Asociacion();
            logger.error("Else !!!!");
        }
        
        this.verDescripcion = false;
        logger.info(this.regAsoc.getCif());
    }

    /**
     *
     * @return
     */
    public String completarRegistroAsociacion() {
        UsuarioDAO usuDAO = new UsuarioDAO();
        
        asocDAO = new AsociacionDAO();
        notifDAO = new NotificacionDAO();
        if (verDescripcion) {
            usuDAO.guardar(this.regAsoc.getIdUsuario());
            asocDAO.registrar(this.regAsoc);
            notifDAO = new NotificacionDAO();
            notifDAO.generarNotificacion(this.regAsoc.getIdUsuario(), Notificaciones.REGUSU, null, regAsoc.getIdUsuario().getIdUsuario(), true);
            flash.put("Email", this.regAsoc.getIdUsuario().getLogin());
            return goToExito;
        } else {
            if (!verDescripcion) {
                addError("Debes subir un comprobante antes de completar el registro");
            } else {
                addError("¡Ups! Ha ocurrido un error inesperado, vuelva a intentarlo más tarde");
            }
            return null;
        }
    }

    /**
     *
     * @param event
     */
    public void subirComprobante(FileUploadEvent event) {
        this.getComprobante();
        try {
            String ficheroSalida;
            //this.urlFotos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/productos");
            String urlFotos = "/home/daw/ASOCIATE/comprobante";
            String prFoto = //event.getFile().getFileName().substring(0, event.getFile().getFileName().lastIndexOf("."));
                    this.regAsoc.getCif();
            ficheroSalida = "\\" + prFoto + ".jpg";
            File targetFolder = new File(urlFotos);
            InputStream inputStream = event.getFile().getInputstream();
            logger.info("El prFoto;" + prFoto + " y el ficheroSalida;" + ficheroSalida);
            OutputStream out = new FileOutputStream(new File(targetFolder, ficheroSalida));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            //this.getRegAsoc().setLogo(prFoto + ".jpg");

            this.verDescripcion = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //agregar el mensaje de que el registro se ha completado y que le llegara el correo
    }

    /**
     *
     * @return
     */
    public String atras() {
        if (regAsoc != null) {
            flash.put("Asociacion", regAsoc);
        }
        return goToInicio;
    }

    /**
     *
     * @return
     */
    public Log getLogger() {
        return logger;
    }

    /**
     *
     * @param logger
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     *
     * @return
     */
    public Flash getFlash() {
        return flash;
    }

    /**
     *
     * @param flash
     */
    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    /**
     *
     * @return
     */
    public UploadedFile getComprobante() {
        return comprobante;
    }

    /**
     *
     * @param comprobante
     */
    public void setComprobante(UploadedFile comprobante) {
        this.comprobante = comprobante;
    }

    /**
     *
     * @return
     */
    public NotificacionDAO getNotifDAO() {
        return notifDAO;
    }

    /**
     *
     * @param notifDAO
     */
    public void setNotifDAO(NotificacionDAO notifDAO) {
        this.notifDAO = notifDAO;
    }

    /**
     *
     * @return
     */
    public UsuarioDAO getUserDAO() {
        return userDAO;
    }

    /**
     *
     * @param userDAO
     */
    public void setUserDAO(UsuarioDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     *
     * @return
     */
    public AsociacionDAO getAsocDAO() {
        return asocDAO;
    }

    /**
     *
     * @param asocDAO
     */
    public void setAsocDAO(AsociacionDAO asocDAO) {
        this.asocDAO = asocDAO;
    }

    /**
     *
     * @return
     */
    public PersonaDAO getPersDAO() {
        return persDAO;
    }

    /**
     *
     * @param persDAO
     */
    public void setPersDAO(PersonaDAO persDAO) {
        this.persDAO = persDAO;
    }

    /**
     *
     * @return
     */
    public Asociacion getRegAsoc() {
        return regAsoc;
    }

    /**
     *
     * @param regAsoc
     */
    public void setRegAsoc(Asociacion regAsoc) {
        this.regAsoc = regAsoc;
    }

    /**
     *
     * @return
     */
    public Usuario getRegUser() {
        return regUser;
    }

    /**
     *
     * @param regUser
     */
    public void setRegUser(Usuario regUser) {
        this.regUser = regUser;
    }

    /**
     *
     * @return
     */
    public Boolean getVerDescripcion() {
        return verDescripcion;
    }

    /**
     *
     * @param verDescripcion
     */
    public void setVerDescripcion(Boolean verDescripcion) {
        this.verDescripcion = verDescripcion;
    }

    /**
     *
     * @return
     */
    public Long getIdPersonaAsoc() {
        return idPersonaAsoc;
    }

    /**
     *
     * @param idPersonaAsoc
     */
    public void setIdPersonaAsoc(Long idPersonaAsoc) {
        this.idPersonaAsoc = idPersonaAsoc;
    }

    /**
     *
     * @return
     */
    public String getPrueba() {
        return prueba;
    }

    /**
     *
     * @param prueba
     */
    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

}
