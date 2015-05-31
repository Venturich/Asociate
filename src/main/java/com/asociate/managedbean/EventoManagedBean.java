/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.EventoAsistentesDAO;
import com.asociate.dao.EventoDAO;
import com.asociate.modelo.Evento;
import com.asociate.modelo.Persona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "eventoMB")
@ViewScoped
public class EventoManagedBean extends AsociateError implements Serializable {

    private String goToCalendarioEventos = "";
    private String goToPerfil = "";

    @ManagedProperty(value = "#{geocodeViewMB}")
    private GeocodeViewManagedBean geocode;

    private Flash flash;

    private Evento evento;
    private List<Persona> listaAsistente;
    private EventoAsistentesDAO evAsDao;
    private EventoDAO eveDAO;

    /**
     * Creates a new instance of EventoManagedBean
     */
    public EventoManagedBean() {
    }

    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        evAsDao = new EventoAsistentesDAO();
        eveDAO = new EventoDAO();
        if (flash.get("Evento") == null) {
            Long idEvento = (Long) flash.get("idEvento");
            this.evento = eveDAO.getEvento(idEvento);
            if (evento != null) {
                this.listaAsistente = evAsDao.getListaEvento(idEvento);
            }
        } else {
            this.evento = (Evento) flash.get("Evento");
            if (evento != null) {
                this.listaAsistente = evAsDao.getListaEvento(evento.getIdEvento());
            }   
        }

    }

    public String volver() {
        flash.put("ListaEvento", this.flash.get("ListaEvento"));
        return goToCalendarioEventos;
    }

    public String irAPerfil(Long id) {
        flash.put("asociacion",false);
        flash.put("idPersona", id);
        flash.put("Evento", evento);
        return goToPerfil;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Persona> getListaAsistente() {
        return listaAsistente;
    }

    public void setListaAsistente(List<Persona> listaAsistente) {
        this.listaAsistente = listaAsistente;
    }

}
