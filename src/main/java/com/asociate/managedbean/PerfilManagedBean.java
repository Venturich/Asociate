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

    private final String goToEventos = "calendarioEventos";
    private final String goToForo = "";
    private final String goToDirectorio = "";
    private final String goToNuevoEvento = "nuevoEvento";
    private final String goToListaAmigos = "buscadorUsuarios";

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

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        this.editarDatos = false;

        if (this.flash.get("User") != null) {
            user = (Usuario) this.flash.get("User");
        } else {
            user = datosSesion.getUsuarioLogeado();
        }

        logger.info("PerfilManageBean: " + user.getIdUsuario());
        menDAO = new MensajeriaDAO();
        dirDAO = new DirectorioDAO();
        if (datosSesion.getEsAsociacion()) {
            socDAO = new SocioDAO();
            socios = socDAO.getListaSocios(user.getAsociacion().getIdAsociacion());
            //tieneDirectorio = dirDAO.hasDirectorioAsociacion(user.getAsociacion().getIdAsociacion());
        } else {
            amiDAO = new AmistadDAO();
            amigos = amiDAO.getListaAmigos(user.getPersona().getIdPersona());
            //tieneDirectorio = dirDAO.hasDirectorioPersona(user.getPersona().getIdPersona());
        }
        logger.info("init de perfilmanagedbean");
        tieneFPerfil = Archivos.comprobarFPerfl(user.getIdUsuario());
        logger.info(tieneFPerfil+" el perfil");
        datosSesion.setUsuarioLogeado(user);

    }

    /**
     *
     */
    public void guardarCambios() {
        usuDAO = new UsuarioDAO();
        usuDAO.actualizar(user);
        editarDatos = false;
    }

    /**
     *
     * @param idNotificacion
     */
    public void marcarComoVista(Long idNotificacion) {
        notiDAO = new NotificacionDAO();
        notiDAO.marcarComoVista(idNotificacion);
    }

    /**
     *
     */
    public void actualizarFPerfil() {
        tieneFPerfil = Archivos.comprobarFPerfl(user.getIdUsuario());
    }

    /**
     *
     * @return
     */
    public String irNuevoEvento() {
        return goToNuevoEvento;
    }

    /**
     *
     * @return
     */
    public String irListaEvento() {
        return goToEventos;
    }

    /**
     *
     * @return
     */
    public String irDirectorio() {
        return goToDirectorio;
    }

    /**
     *
     * @return
     */
    public String irForo() {
        return goToForo;
    }

    /**
     *
     * @return
     */
    public String irListaAmigos() {
        flash.put("Amigos", this.amigos);
        return goToListaAmigos;
    }

    /**
     *
     * @return
     */
    public String irListaSocios() {
        flash.put("Socios", this.socios);
        return goToListaAmigos;
    }

    //getter y setter
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
    public Usuario getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(Usuario user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public List<Amistad> getAmigos() {
        return amigos;
    }

    /**
     *
     * @param amigos
     */
    public void setAmigos(List<Amistad> amigos) {
        this.amigos = amigos;
    }

    /**
     *
     * @return
     */
    public List<Socio> getSocios() {
        return socios;
    }

    /**
     *
     * @param socios
     */
    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    /**
     *
     * @return
     */
    public String getTieneFPerfil() {
        return tieneFPerfil;
    }

    /**
     *
     * @param tieneFPerfil
     */
    public void setTieneFPerfil(String tieneFPerfil) {
        this.tieneFPerfil = tieneFPerfil;
    }

    /**
     *
     * @return
     */
    public boolean isEditarDatos() {
        return editarDatos;
    }

    /**
     *
     * @param editarDatos
     */
    public void setEditarDatos(boolean editarDatos) {
        this.editarDatos = editarDatos;
    }

    /**
     *
     * @return
     */
    public boolean isTieneDirectorio() {
        return tieneDirectorio;
    }

    /**
     *
     * @param tieneDirectorio
     */
    public void setTieneDirectorio(boolean tieneDirectorio) {
        this.tieneDirectorio = tieneDirectorio;
    }

}
