/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.MensajeriaDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Mensajeria;
import com.asociate.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "mensajesMB")
@ViewScoped
public class MensajesManagedBean extends AsociateError implements Serializable {

    private Flash flash;
    private Log logger = LogFactory.getLog(this.getClass().getName());

    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;

    private List<Mensajeria> mensajesPendientes;
    private List<Mensajeria> mensajesHistorico;
    private List<Usuario> busquedaContacto;
    private Mensajeria mensajeLeer;
    private Mensajeria mensajeNuevo;
    private MensajeriaDAO menDAO;
    private UsuarioDAO usuDAO;

    private boolean verHistorico = false;
    private Long idDestinoNuevo;

    /**
     * Creates a new instance of MensajesManagedBean
     */
    public MensajesManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        logger.info("Init de mensaje;");
        if (flash.get("Mensajes") != null) {
            mensajesPendientes = (List<Mensajeria>) flash.get("Mensajes");
        } else {
            mensajesPendientes = new ArrayList();
        }
        menDAO = new MensajeriaDAO();
        mensajesHistorico = menDAO.getHistorico(datosSesion.getUsuarioLogeado().getIdUsuario());
        if (mensajesHistorico == null || mensajesHistorico.size() == 0) {
            mensajesHistorico = new ArrayList();
        }
        mensajeLeer = new Mensajeria();
        mensajeNuevo = new Mensajeria();

    }

    /**
     *
     */
    public void crearMensaje() {
        this.mensajeNuevo = new Mensajeria();
        mensajeNuevo.setIdDestino(this.mensajeLeer.getIdOrigen());
        mensajeNuevo.setIdOrigen(this.mensajeLeer.getIdDestino());
        mensajeNuevo.setLeido("N");

    }

    /**
     *
     */
    public void nuevoMensaje() {
        this.mensajeNuevo = new Mensajeria();
        mensajeNuevo.setIdDestino(this.mensajeLeer.getIdOrigen());
        mensajeNuevo.setIdOrigen(this.mensajeLeer.getIdDestino());
        mensajeNuevo.setLeido("N");
    }

    /**
     *
     * @param idMensaje
     */
    public void leerMensaje(Long idMensaje) {
        menDAO = new MensajeriaDAO();
        for (Mensajeria mp : mensajesPendientes) {
            logger.info("se lee -->" + mp.getIdMensaje());
            if (mp.getIdMensaje().compareTo(idMensaje) == 0) {
                menDAO.leido(mp.getIdMensaje());
                mensajesHistorico.add(mp);
                this.mensajeLeer = mp;
                mensajesPendientes.remove(mp);
                break;
            }
        }
    }
    
    /**
     *
     * @param idMensaje
     */
    public void leerMensajeHistorico(Long idMensaje) {
        
        for (Mensajeria mp : mensajesHistorico) {
            logger.info("se lee -->" + mp.getIdMensaje());
            if (mp.getIdMensaje().compareTo(idMensaje) == 0) {
                this.mensajeLeer = mp;
                break;
            }
        }
    }

    /**
     *
     */
    public void cargarDatosContacto() {
        for (Usuario u : busquedaContacto) {
            if (this.idDestinoNuevo.equals(u.getIdUsuario())) {
                this.mensajeNuevo.setIdDestino(u);
                break;
            }
        }

    }

    /**
     *
     * @param query
     */
    public void buscarContacto(String query) {
        usuDAO = new UsuarioDAO();
        this.busquedaContacto = usuDAO.buscarContacto(query, this.datosSesion.getUsuarioLogeado().getIdUsuario());

    }

    /**
     *
     */
    public void enviarMensaje() {
        mensajeNuevo.setFhenvio(new Date());
        mensajeNuevo.setIdDestino(this.mensajeLeer.getIdOrigen());
        mensajeNuevo.setIdOrigen(this.datosSesion.getUsuarioLogeado());
        menDAO = new MensajeriaDAO();
        if (menDAO.guardar(mensajeNuevo)) {
            addInfo("Mensaje enviado con exito");
            mensajeNuevo = new Mensajeria();
        } else {
            addError("Error al enviar el mensaje");
        }

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
    public List<Mensajeria> getMensajesHistorico() {
        return mensajesHistorico;
    }

    /**
     *
     * @param mensajesHistorico
     */
    public void setMensajesHistorico(List<Mensajeria> mensajesHistorico) {
        this.mensajesHistorico = mensajesHistorico;
    }

    /**
     *
     * @return
     */
    public Mensajeria getMensajeLeer() {
        return mensajeLeer;
    }

    /**
     *
     * @param mensajeLeer
     */
    public void setMensajeLeer(Mensajeria mensajeLeer) {
        this.mensajeLeer = mensajeLeer;
    }

    /**
     *
     * @return
     */
    public Mensajeria getMensajeNuevo() {
        return mensajeNuevo;
    }

    /**
     *
     * @param mensajeNuevo
     */
    public void setMensajeNuevo(Mensajeria mensajeNuevo) {
        this.mensajeNuevo = mensajeNuevo;
    }

    /**
     *
     * @return
     */
    public boolean isVerHistorico() {
        return verHistorico;
    }

    /**
     *
     * @param verHistorico
     */
    public void setVerHistorico(boolean verHistorico) {
        this.verHistorico = verHistorico;
    }

    /**
     *
     * @return
     */
    public Long getIdDestinoNuevo() {
        return idDestinoNuevo;
    }

    /**
     *
     * @param idDestinoNuevo
     */
    public void setIdDestinoNuevo(Long idDestinoNuevo) {
        this.idDestinoNuevo = idDestinoNuevo;
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
    public List<Usuario> getBusquedaContacto() {
        return busquedaContacto;
    }

    /**
     *
     * @param busquedaContacto
     */
    public void setBusquedaContacto(List<Usuario> busquedaContacto) {
        this.busquedaContacto = busquedaContacto;
    }

}
