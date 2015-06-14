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
import com.asociate.modelo.Mensajeria;
import com.asociate.modelo.Notificacion;
import com.asociate.modelo.Socio;
import com.asociate.modelo.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "perfilMenuMB")
@SessionScoped
public class PerfilMenuManagedBean extends AsociateError implements Serializable{

    private final String goToBuscador = "buscadorUsuarios";
    private final String goToPerfilPrincipal = "perfilPrincipal";
    private final String goToMensajes = "mensajes";
    private final String goToConfiguracion = "configuracion";
    private Log logger = LogFactory.getLog(this.getClass().getName());
    private Flash flash;
    private MenuModel menuNotificaciones = new DefaultMenuModel();
            
    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;
    private Usuario user;
    private List<Notificacion> notificaciones;
    private List<Mensajeria> mensajesPendientes;
    private UsuarioDAO usuDAO;
    private NotificacionDAO notiDAO;
    private MensajeriaDAO menDAO;
    
    private String busqueda;

    /**
     * Creates a new instance of PerfilMenuManagedBean
     */
    public PerfilMenuManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        logger.info("PerfilManageMENUBean");
        if (this.flash.get("User") != null) {
            user = (Usuario) this.flash.get("User");
            menDAO = new MensajeriaDAO();
            mensajesPendientes = menDAO.getMensajesPendientes(user.getIdUsuario());
            if(mensajesPendientes ==null || mensajesPendientes.size()==0){
            mensajesPendientes = new ArrayList();
            }
            notiDAO = new NotificacionDAO();
            notificaciones = notiDAO.getListaNotificacionPendientes(user.getIdUsuario());
            if(notificaciones ==null || notificaciones.size()==0){
            notificaciones = new ArrayList();
            }
            generarMenuNotificaciones();
        } else {
            mensajesPendientes = new ArrayList();
            notificaciones = new ArrayList();
        }
    }

    private void generarMenuNotificaciones() {
        DefaultMenuItem item = new DefaultMenuItem();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        for (Notificacion not : notificaciones) {
             item.setValue(not.getTexto()+" ("+sdf.format(not.getFecha())+") ");
             item.setStyleClass(not.getTipo());
             item.setIcon("icon-"+not.getTipo());
             menuNotificaciones.addElement(item);
        }
    }
    
    /**
     *
     * @return
     */
    public String irMensajes() {
        flash.put("Mensajes", this.mensajesPendientes);
        return goToMensajes;
    }

    /**
     *
     * @return
     */
    public String irBuscador() {
        flash.put("Busqueda",this.busqueda);
        return goToBuscador;

    }

    /**
     *
     * @return
     */
    public String irPerfil() {
        return goToPerfilPrincipal;

    }
    
    /**
     *
     * @return
     */
    public String irConfiguracion(){
        return goToConfiguracion;
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
    public List<Mensajeria> getMensajesPendientes() {
        return mensajesPendientes;
    }

    /**
     *
     * @param mensajesPendientes
     */
    public void setMensajesPendientes(List<Mensajeria> mensajesPendientes) {
        this.mensajesPendientes = mensajesPendientes;
    }

    /**
     *
     * @return
     */
    public String getBusqueda() {
        return busqueda;
    }

    /**
     *
     * @param busqueda
     */
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    /**
     *
     * @return
     */
    public MenuModel getMenuNotificaciones() {
        return menuNotificaciones;
    }

    /**
     *
     * @param menuNotificaciones
     */
    public void setMenuNotificaciones(MenuModel menuNotificaciones) {
        this.menuNotificaciones = menuNotificaciones;
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


    
}
