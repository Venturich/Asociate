/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.modelo.Notificacion;
import com.asociate.modelo.Usuario;
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

        esAsociacion = false;
    }

    /**
     *
     * @param event
     */
    public void subirFotoPerfil() {
        try {
            String ficheroSalida;
            String urlFotos;
            //this.urlFotos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/productos");

            urlFotos = "D:/ASOCIATE/usuario/" + usuarioLogeado.getIdUsuario();
            ficheroSalida = "\\perfil.jpg";

            //String prFoto = fPerfil.getFileName().substring(0, fPerfil.getFileName().lastIndexOf("."));
            File targetFolder = new File(urlFotos);
            InputStream inputStream = fPerfil.getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, ficheroSalida));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            //if (esAsociacion) {
            //  this.usuarioLogeado.getAsociacion().setLogo();
            //} 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    .redirect(contextPath + "/index.xhtml");
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

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

}
