/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AsociacionDAO;
import com.asociate.dao.NotificacionDAO;
import com.asociate.dao.PersonaDAO;
import com.asociate.dao.ProvinciaDAO;
import com.asociate.dao.PuebloDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Asociacion;
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
@ViewScoped
public class CompletarRegistroAsociacionManagedBean extends AsociateError implements Serializable{

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
    private Persona regPersona;
    private Usuario regUser;
  
    private Boolean verDescripcion;
    private Long idPersonaAsoc;
    

    /**
     * Creates a new instance of completarRegistroManagedBean
     */
    public CompletarRegistroAsociacionManagedBean() {
    }

    @PostConstruct
    public void init() {
        logger.error("Compltar Asociacion");
        
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        if (flash.get("Asociacion") != null) {
            regAsoc = (Asociacion) flash.get("Asociacion");
        } else {
            regAsoc = new Asociacion();
        }
                    this.verDescripcion=false;

    }

    public String completarRegistroAsociacion() {
        asocDAO = new AsociacionDAO();
        notifDAO = new NotificacionDAO();
        if (verDescripcion && asocDAO.registrar(this.regAsoc)) {
            notifDAO = new NotificacionDAO();
            notifDAO.generarNotificacion(this.regAsoc.getIdUsuario(), Notificaciones.REGUSU, null, null, true);
            flash.put("Email", this.regUser.getLogin());
            return goToExito;
        } else {
            if(!verDescripcion)
            addError("Debes subir un comprobante antes de completar el registro");
            else
                addError("¡Ups! Ha ocurrido un error inesperado, vuelva a intentarlo más tarde");
            return null;
        }
    }

    public void subirComprobante(FileUploadEvent event) {
        this.getComprobante();
        try {
            String ficheroSalida;
            //this.urlFotos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/productos");
            String urlFotos = "D:/ASOCIATE/comprobante";
            String prFoto = //event.getFile().getFileName().substring(0, event.getFile().getFileName().lastIndexOf("."));
                    this.regAsoc.getCif();
            ficheroSalida = "\\" + prFoto + ".jpg";
            File targetFolder = new File(urlFotos);
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, ficheroSalida));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            this.getRegAsoc().setLogo(ficheroSalida);

        
            this.verDescripcion=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        //agregar el mensaje de que el registro se ha completado y que le llegara el correo
    }

    public String atras() {
        if (regAsoc != null) {
            flash.put("Asociacion", regAsoc);
        }
        return goToInicio;
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public Flash getFlash() {
        return flash;
    }

    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    public UploadedFile getComprobante() {
        return comprobante;
    }

    public void setComprobante(UploadedFile comprobante) {
        this.comprobante = comprobante;
    }

    public NotificacionDAO getNotifDAO() {
        return notifDAO;
    }

    public void setNotifDAO(NotificacionDAO notifDAO) {
        this.notifDAO = notifDAO;
    }
    public UsuarioDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UsuarioDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AsociacionDAO getAsocDAO() {
        return asocDAO;
    }

    public void setAsocDAO(AsociacionDAO asocDAO) {
        this.asocDAO = asocDAO;
    }

    public PersonaDAO getPersDAO() {
        return persDAO;
    }

    public void setPersDAO(PersonaDAO persDAO) {
        this.persDAO = persDAO;
    }

    public Asociacion getRegAsoc() {
        return regAsoc;
    }

    public void setRegAsoc(Asociacion regAsoc) {
        this.regAsoc = regAsoc;
    }

    public Persona getRegPersona() {
        return regPersona;
    }

    public void setRegPersona(Persona regPersona) {
        this.regPersona = regPersona;
    }

    public Usuario getRegUser() {
        return regUser;
    }

    public void setRegUser(Usuario regUser) {
        this.regUser = regUser;
    }


    public Boolean getVerDescripcion() {
        return verDescripcion;
    }

    public void setVerDescripcion(Boolean verDescripcion) {
        this.verDescripcion = verDescripcion;
    }

    public Long getIdPersonaAsoc() {
        return idPersonaAsoc;
    }

    public void setIdPersonaAsoc(Long idPersonaAsoc) {
        this.idPersonaAsoc = idPersonaAsoc;
    }

}
