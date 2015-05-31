/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.EventoDAO;
import com.asociate.modelo.Evento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Ventura
 */
@Named(value = "eventoCalendarioMB")
@ViewScoped
public class EventoCalendarioManagedBean {

    private String goToEvento = "";

    @ManagedProperty("#{sessionMB}")
    private DatosSesion sesion;

    private Flash flash;

    private EventoDAO eveDAO;
    private List<Evento> listaEvento = new ArrayList();

    /**
     * Creates a new instance of EventoCalendarioManagedBean
     */
    public EventoCalendarioManagedBean() {
    }

    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        eveDAO = new EventoDAO();
        if (flash.get("ListaEvento") == null) {
            if (sesion.getEsAsociacion()) {
                this.listaEvento.addAll(eveDAO.getListaEventoPropio(sesion.getUsuarioLogeado().getIdUsuario()));
            } else {
                this.listaEvento.addAll(eveDAO.getListaEvento(sesion.getUsuarioLogeado().getPersona().getIdPersona()));

            }
            //listaEvento.sort(null);
        } else {
            this.listaEvento = (List<Evento>) flash.get("ListaEvento");
        }
        
        
    }

    public String irAEvento(Long idEvento) {
        flash.put("idEvento", idEvento);
        flash.put("ListaEvento", this.listaEvento);
        return goToEvento;
    }

    public List<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setListaEvento(List<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }
    
    

}
