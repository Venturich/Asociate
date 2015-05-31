/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AmistadDAO;
import com.asociate.dao.DirectorioDAO;
import com.asociate.dao.MensajeriaDAO;
import com.asociate.dao.NotificacionDAO;
import com.asociate.dao.SocioDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Amistad;
import com.asociate.modelo.Socio;
import com.asociate.modelo.Usuario;
import com.asociate.utils.Archivos;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "perfilMB")
@ViewScoped
public class PerfilManagedBean {

    private final String goToEventos = "";
    private final String goToForo = "";
    private final String goToDirectorio = "";
    private final String goToNuevoEvento = "";
    private final String goToListaAmigos = "";

    private Log logger = LogFactory.getLog(this.getClass().getName());
    private Flash flash;

    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;
    private Usuario user;
    private List<Amistad> amigos;
    private List<Socio> socios;
    private UsuarioDAO usuDAO;
    private NotificacionDAO notiDAO;
    private AmistadDAO amiDAO;
    private SocioDAO socDAO;
    private MensajeriaDAO menDAO;
    private DirectorioDAO dirDAO;

    private String tieneFPerfil;
    private boolean editarDatos = false;
    private boolean tieneDirectorio = false;

    /**
     * Creates a new instance of PerfilManagedBean
     */
    public PerfilManagedBean() {
    }

    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        if (this.flash.get("User") != null) {
            user = (Usuario) this.flash.get("User");
            menDAO = new MensajeriaDAO();
            dirDAO = new DirectorioDAO();
            if (datosSesion.getEsAsociacion()) {
                socDAO = new SocioDAO();
                socios = socDAO.getListaSocios(user.getAsociacion().getIdAsociacion());
                tieneDirectorio = dirDAO.hasDirectorioAsociacion(user.getAsociacion().getIdAsociacion());
            } else {
                amiDAO = new AmistadDAO();
                amigos = amiDAO.getListaAmigos(user.getPersona().getIdPersona());
                tieneDirectorio = dirDAO.hasDirectorioPersona(user.getPersona().getIdPersona());
            }
            tieneFPerfil = Archivos.comprobarFPerfl(user.getIdUsuario());
            datosSesion.setUsuarioLogeado(user);
        }

    }

    public void guardarCambios() {
        usuDAO = new UsuarioDAO();
        usuDAO.actualizar(user);
        editarDatos = false;
    }

    public void marcarComoVista(Long idNotificacion) {
        notiDAO = new NotificacionDAO();
        notiDAO.marcarComoVista(idNotificacion);
    }

    public void actualizarFPerfil() {
        tieneFPerfil = Archivos.comprobarFPerfl(user.getIdUsuario());
    }

    public String irNuevoEvento() {
        return goToNuevoEvento;
    }

    public String irListaEvento() {
        return goToEventos;
    }

    public String irDirectorio() {
        return goToDirectorio;
    }

    public String irForo() {
        return goToForo;
    }

    public String irListaAmigos() {
        flash.put("Amigos", this.amigos);
        return goToListaAmigos;
    }
    
    public String irListaSocios() {
        flash.put("Socios", this.socios);
        return goToListaAmigos;
    }

    //getter y setter
    public DatosSesion getDatosSesion() {
        return datosSesion;
    }

    public void setDatosSesion(DatosSesion datosSesion) {
        this.datosSesion = datosSesion;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }


    public List<Amistad> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Amistad> amigos) {
        this.amigos = amigos;
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    public String getTieneFPerfil() {
        return tieneFPerfil;
    }

    public void setTieneFPerfil(String tieneFPerfil) {
        this.tieneFPerfil = tieneFPerfil;
    }

    public boolean isEditarDatos() {
        return editarDatos;
    }

    public void setEditarDatos(boolean editarDatos) {
        this.editarDatos = editarDatos;
    }
    
    public boolean isTieneDirectorio() {
        return tieneDirectorio;
    }

    public void setTieneDirectorio(boolean tieneDirectorio) {
        this.tieneDirectorio = tieneDirectorio;
    }

}
