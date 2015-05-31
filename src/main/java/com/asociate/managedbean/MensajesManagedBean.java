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

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "mensajesMB")
@ViewScoped
public class MensajesManagedBean extends AsociateError implements Serializable{

    
    private Flash flash;
    
    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;
    
    private List<Mensajeria> mensajesPendientes;
    private List<Mensajeria> mensajesHistorico;
    private List<Usuario> busquedaContacto;
    private Mensajeria mensajeLeer;
    private Mensajeria mensajeNuevo;
    private MensajeriaDAO menDAO;
    private UsuarioDAO usuDAO;
    
    private boolean verHistorico=false;
    private Long idDestinoNuevo;

    /**
     * Creates a new instance of MensajesManagedBean
     */
    public MensajesManagedBean() {
    }

    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        if(flash.get("Mensajes")!=null){
            mensajesPendientes=(List<Mensajeria>) flash.get("Mensajes");
        }
        menDAO= new MensajeriaDAO();
        mensajesHistorico= menDAO.getHistorico(datosSesion.getUsuarioLogeado().getIdUsuario());
        

    }

    public void crearMensaje(){
        this.mensajeNuevo=new Mensajeria();
        mensajeNuevo.setIdDestino(this.mensajeLeer.getIdOrigen());
        mensajeNuevo.setIdOrigen(this.mensajeLeer.getIdDestino());
        mensajeNuevo.setLeido("N");
        
        
    }
    
    public void nuevoMensaje(){
        this.mensajeNuevo=new Mensajeria();
        mensajeNuevo.setIdOrigen(this.mensajeLeer.getIdDestino());
        mensajeNuevo.setLeido("N");
    }
    
    public void cargarDatosContacto(){
        for (Usuario u:busquedaContacto) {
            if(this.idDestinoNuevo.equals(u.getIdUsuario())){
                this.mensajeNuevo.setIdDestino(u);
                break;
            }
        }
        
    }
    
    public void buscarContacto(String query){
        usuDAO = new UsuarioDAO();
        this.busquedaContacto=usuDAO.buscarContacto(query, this.datosSesion.getUsuarioLogeado().getIdUsuario());
    
    }
    
    public void enviarMensaje(){
        mensajeNuevo.setFhenvio(new Date());
        menDAO = new MensajeriaDAO();
        if(menDAO.guardar(mensajeNuevo)){
            addInfo("Mensaje enviado con exito");
            mensajeNuevo= new Mensajeria();
        }else{
            addError("Error al enviar el mensaje");
        }
        
        
    }
    
    public List<Mensajeria> getMensajesPendientes() {
        return mensajesPendientes;
    }

    public void setMensajesPendientes(List<Mensajeria> mensajesPendientes) {
        this.mensajesPendientes = mensajesPendientes;
    }

    public List<Mensajeria> getMensajesHistorico() {
        return mensajesHistorico;
    }

    public void setMensajesHistorico(List<Mensajeria> mensajesHistorico) {
        this.mensajesHistorico = mensajesHistorico;
    }

    public Mensajeria getMensajeLeer() {
        return mensajeLeer;
    }

    public void setMensajeLeer(Mensajeria mensajeLeer) {
        this.mensajeLeer = mensajeLeer;
    }

    public Mensajeria getMensajeNuevo() {
        return mensajeNuevo;
    }

    public void setMensajeNuevo(Mensajeria mensajeNuevo) {
        this.mensajeNuevo = mensajeNuevo;
    }

    public boolean isVerHistorico() {
        return verHistorico;
    }

    public void setVerHistorico(boolean verHistorico) {
        this.verHistorico = verHistorico;
    }

    public Long getIdDestinoNuevo() {
        return idDestinoNuevo;
    }

    public void setIdDestinoNuevo(Long idDestinoNuevo) {
        this.idDestinoNuevo = idDestinoNuevo;
    }

}
