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
public class PerfilMenuManagedBean {

    private final String goToBuscador = "";
    private final String goToPerfilPrincipal = "";
    private final String goToMensajes = "";
    private final String goToConfiguracion = "";

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

    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        if (this.flash.get("User") != null) {
            user = (Usuario) this.flash.get("User");
            menDAO = new MensajeriaDAO();
            mensajesPendientes = menDAO.getMensajesPendientes(user.getIdUsuario());
            notificaciones = new ArrayList(user.getNotificacionCollection());
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
    
    public String irMensajes() {
        flash.put("Mensajes", this.mensajesPendientes);
        return goToMensajes;
    }

    public String irBuscador() {
        flash.put("Busqueda",this.busqueda);
        return goToBuscador;

    }

    public String irPerfil() {
        return goToPerfilPrincipal;

    }
    
    public String irConfiguracion(){
        return goToConfiguracion;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public List<Mensajeria> getMensajesPendientes() {
        return mensajesPendientes;
    }

    public void setMensajesPendientes(List<Mensajeria> mensajesPendientes) {
        this.mensajesPendientes = mensajesPendientes;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public MenuModel getMenuNotificaciones() {
        return menuNotificaciones;
    }

    public void setMenuNotificaciones(MenuModel menuNotificaciones) {
        this.menuNotificaciones = menuNotificaciones;
    }


    
}
