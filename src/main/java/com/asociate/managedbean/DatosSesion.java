/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.modelo.Notificacion;
import com.asociate.modelo.Usuario;
import com.asociate.utils.Archivos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "sesionMB")
@SessionScoped
public class DatosSesion extends AsociateError implements Serializable {

    private Flash flash;
    private Log logger = LogFactory.getLog(this.getClass().getName());

    private Usuario usuarioLogeado;
    private List<Notificacion> notificaciones;

    private Boolean esAsociacion;
    private UploadedFile fPerfil;

    /**
     * Creates a new instance of DatosSesion
     */
    public DatosSesion() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        esAsociacion = false;
    }

    /**
     *
     */
    public void subirFotoPerfil() {
        try {
            String ficheroSalida;
            String urlFotos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/usuarios/"+ this.usuarioLogeado.getIdUsuario());
            //String urlFotos = "D:/ASOCIATE/usuarios/" + this.usuarioLogeado.getIdUsuario();
            String prFoto = //event.getFile().getFileName().substring(0, event.getFile().getFileName().lastIndexOf("."));
                    "perfil";
            ficheroSalida = "\\" + prFoto + ".jpg";
            Archivos.comprobarCarpetaUsuario(this.usuarioLogeado.getIdUsuario());
            File targetFolder = new File(urlFotos);
            logger.info("El prFoto;" + targetFolder.getAbsolutePath() + " y el ficheroSalida;" + ficheroSalida + " lo subido" + fPerfil.getFileName());
            InputStream inputStream = fPerfil.getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, ficheroSalida));
            IOUtils.copy(inputStream, out);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(false);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(contextPath + "/faces/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    /**
     *
     * @param notificaciones
     */
    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     *
     * @return
     */
    public Boolean getEsAsociacion() {
        return esAsociacion;
    }

    /**
     *
     * @param esAsociacion
     */
    public void setEsAsociacion(Boolean esAsociacion) {
        this.esAsociacion = esAsociacion;
    }

    /**
     *
     * @return
     */
    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    /**
     *
     * @param usuarioLogeado
     */
    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public UploadedFile getfPerfil() {
        return fPerfil;
    }

    public void setfPerfil(UploadedFile fPerfil) {
        this.fPerfil = fPerfil;
    }

}
