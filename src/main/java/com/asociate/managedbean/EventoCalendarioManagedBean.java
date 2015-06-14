/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.EventoDAO;
import com.asociate.modelo.Evento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "eventoCalendarioMB")
@ViewScoped
public class EventoCalendarioManagedBean extends AsociateError implements Serializable{

    private String goToEvento = "evento";

    @ManagedProperty("#{sesionMB}")
    private DatosSesion sesion;

    private Flash flash;
    private Log logger = LogFactory.getLog(this.getClass().getName());
    
    private EventoDAO eveDAO;
    private List<Evento> listaEvento = new ArrayList();

    /**
     * Creates a new instance of EventoCalendarioManagedBean
     */
    public EventoCalendarioManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        eveDAO = new EventoDAO();
        if (flash.get("ListaEvento") == null) {
            if (sesion.getEsAsociacion()) {
                logger.info("no es asoc");
                this.listaEvento=(eveDAO.getListaEventoPropio(sesion.getUsuarioLogeado().getIdUsuario()));
            } else {
                logger.info(" es asoc");
                this.listaEvento=(eveDAO.getListaEventoPropio(sesion.getUsuarioLogeado().getIdUsuario()));
                this.listaEvento.addAll(eveDAO.getListaEvento(sesion.getUsuarioLogeado().getPersona().getIdPersona()));

            }
            //listaEvento.sort(null);
        } else {
            logger.info("coge del flash");
            this.listaEvento = (List<Evento>) flash.get("ListaEvento");
        }
        
        
    }

    /**
     *
     * @param idEvento
     * @return
     */
    public String irAEvento(Long idEvento) {
        flash.put("idEvento", idEvento);
        flash.put("ListaEvento", this.listaEvento);
        return goToEvento;
    }

    /**
     *
     * @return
     */
    public List<Evento> getListaEvento() {
        return listaEvento;
    }

    /**
     *
     * @param listaEvento
     */
    public void setListaEvento(List<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    /**
     *
     * @return
     */
    public DatosSesion getSesion() {
        return sesion;
    }

    /**
     *
     * @param sesion
     */
    public void setSesion(DatosSesion sesion) {
        this.sesion = sesion;
    }
    
    

}
