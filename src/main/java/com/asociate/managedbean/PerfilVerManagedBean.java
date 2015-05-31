/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AmistadDAO;
import com.asociate.dao.AsociacionDAO;
import com.asociate.dao.PersonaDAO;
import com.asociate.dao.SocioDAO;
import com.asociate.modelo.Amistad;
import com.asociate.modelo.Asociacion;
import com.asociate.modelo.Evento;
import com.asociate.modelo.Persona;
import com.asociate.modelo.Socio;
import com.asociate.modelo.Usuario;
import com.asociate.utils.Estados;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
@ManagedBean(name = "perfilVerMB")
@ViewScoped
public class PerfilVerManagedBean extends AsociateError implements Serializable {

    private String goToEvento = "";
    private String goToBuscador = "";

    private Log logger = LogFactory.getLog(this.getClass().getName());
    private Flash flash;
    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;

    private Persona user;
    private Asociacion asoc;
    private PersonaDAO perDAO;
    private AsociacionDAO asoDAO;
    private AmistadDAO amiDAO;
    private SocioDAO socDAO;
    private Evento evento;
    private String imagen = "";
    private String btnAsociate = "Asociate";
    private String btnAmigo = "Amigo + ";

    private boolean deEvento = false;
    private boolean deBusqueda = false;
    private boolean asociacion = false;
    private boolean amigos = false;

    /**
     * Creates a new instance of PerfilVerManagedBean
     */
    public PerfilVerManagedBean() {
    }

    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        asociacion = (boolean) flash.get("asociacion");
        if (asociacion) {
            asoDAO = new AsociacionDAO();
            Long id = (Long) flash.get("idAsociacion");
            asoc = asoDAO.getAsociacionById(id);
            imagen = "D:\\ASOCIATE\\usuarios\\" + asoc.getIdUsuario().getIdUsuario() + "\\perfil.jpg";
            socDAO = new SocioDAO();
            amigos = socDAO.esSocio(id, datosSesion.getUsuarioLogeado().getPersona().getIdPersona());
            if (amigos) {
                btnAsociate = "Eres socio";
            }
        } else {
            perDAO = new PersonaDAO();
            Long id = (Long) flash.get("idPersona");
            user = perDAO.getPersonaById(id);
            imagen = "D:\\ASOCIATE\\usuarios\\" + user.getIdUsuario().getIdUsuario() + "\\perfil.jpg";
            amiDAO = new AmistadDAO();
            amigos = amiDAO.esAmigo(id, datosSesion.getUsuarioLogeado().getPersona().getIdPersona());
            if (amigos) {
                btnAmigo = "Sois amigos";
            }
        }

        if (flash.get("Evento") != null) {
            evento = (Evento) flash.get("Evento");
        }

    }

    public String volverEvento() {
        flash.put("Evento", evento);
        return goToEvento;

    }

    public String volverBuscador() {
        return goToBuscador;
    }

    public void agregarAmigo() {
        
        amiDAO = new AmistadDAO();
        if (amiDAO.eraAmigo(user.getIdPersona(), datosSesion.getUsuarioLogeado().getPersona().getIdPersona())) {
            amiDAO = new AmistadDAO();
            amiDAO.actualizarAmistad(user.getIdPersona(), datosSesion.getUsuarioLogeado().getPersona().getIdPersona(),Estados.PENDIENTE.getValor());
        } else {
            amiDAO = new AmistadDAO();
            Amistad ami = new Amistad();
            ami.setBloqueado(Estados.PENDIENTE.getValor());
            ami.setIdAmigo(user.getIdPersona());
            ami.setIdOrigen(datosSesion.getUsuarioLogeado().getPersona().getIdPersona());
            amiDAO.guardar(ami);
            
            ami = new Amistad();
            ami.setBloqueado(Estados.PENDIENTE.getValor());
            ami.setIdAmigo(datosSesion.getUsuarioLogeado().getPersona().getIdPersona());
            ami.setIdOrigen(user.getIdPersona());
            amiDAO = new AmistadDAO();
            amiDAO.guardar(ami);
        }
        amigos = true;
        addInfo("Peticion de amistad enviada");
        this.btnAmigo = "Petición enviada";
        //notificar
    }

    public void eliminarAmigo() {
        amiDAO = new AmistadDAO();
        amiDAO.eliminarPorId(user.getIdPersona(), datosSesion.getUsuarioLogeado().getPersona().getIdPersona());
        amigos = false;
        addInfo("Amigo eliminado");
        this.btnAmigo = "Amigos + ";
    }

    public void peticionSocio() {
        socDAO = new SocioDAO();
        Socio socio = new Socio();
        socio.setEstado(Estados.PENDIENTE.getValor());
        socio.setIdAsociacion(asoc.getIdAsociacion());
        socio.setIdPersona(datosSesion.getUsuarioLogeado().getPersona().getIdPersona());
        socDAO.guardar(socio);
        addInfo("Tu petición ha sido enviada, será atendida por el administrador de la asociacion lo mas pronto posible");
        //notificar
        btnAsociate = "Pendiente";
    }

    public void peticionBaja() {
        //notificar
        addInfo("Tu petición ha sido enviada, será atendida por el administrador de la asociacion lo mas pronto posible");
        btnAsociate = "Pendiente";
    }

    public Persona getUser() {
        return user;
    }

    public void setUser(Persona user) {
        this.user = user;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public boolean isDeEvento() {
        return deEvento;
    }

    public void setDeEvento(boolean deEvento) {
        this.deEvento = deEvento;
    }

    public boolean isDeBusqueda() {
        return deBusqueda;
    }

    public void setDeBusqueda(boolean deBusqueda) {
        this.deBusqueda = deBusqueda;
    }

    public Asociacion getAsoc() {
        return asoc;
    }

    public void setAsoc(Asociacion asoc) {
        this.asoc = asoc;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isAsociacion() {
        return asociacion;
    }

    public void setAsociacion(boolean asociacion) {
        this.asociacion = asociacion;
    }

    public String getBtnAsociate() {
        return btnAsociate;
    }

    public void setBtnAsociate(String btnAsociate) {
        this.btnAsociate = btnAsociate;
    }

    public String getBtnAmigo() {
        return btnAmigo;
    }

    public void setBtnAmigo(String btnAmigo) {
        this.btnAmigo = btnAmigo;
    }

    public boolean isAmigos() {
        return amigos;
    }

    public void setAmigos(boolean amigos) {
        this.amigos = amigos;
    }

}
