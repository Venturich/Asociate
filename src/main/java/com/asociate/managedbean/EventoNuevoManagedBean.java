/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AmistadDAO;
import com.asociate.dao.EventoAsistentesDAO;
import com.asociate.dao.EventoDAO;
import com.asociate.dao.NotificacionDAO;
import com.asociate.dao.PersonaDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Evento;
import com.asociate.modelo.EventoAsistentes;
import com.asociate.modelo.Notificacion;
import com.asociate.modelo.Persona;
import com.asociate.utils.Estados;
import com.asociate.utils.Notificaciones;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "eventoNuevoMB")
@ViewScoped
public class EventoNuevoManagedBean extends AsociateError implements Serializable {

    private final String goToPerfil = "perfilPrincipal";
    private Flash flash;
    private Log logger = LogFactory.getLog(this.getClass().getName());

    @ManagedProperty(value = "#{geocodeViewMB}")
    private GeocodeViewManagedBean geocode;

    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;

    private EventoDAO eveDAO;
    private PersonaDAO perDAO;

    private Date hoy = new Date();
    private Evento nuevoEvento;
    private Map<Long, Persona> listaAmigo;
    private List<Long> listaAmigoId;
    private List<EventoAsistentes> listaAsistente;
    private UploadedFile fEvento;

    private Boolean panelAsist;

    /**
     * Creates a new instance of EventoNuevoManagedBean
     */
    public EventoNuevoManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        panelAsist = false;
        listaAmigo = new HashMap<Long, Persona>();
        listaAmigoId = new ArrayList();
        listaAsistente = new ArrayList();
        if (flash.get("evento") != null) {
            nuevoEvento = (Evento) flash.get("evento");
        } else {
            nuevoEvento = new Evento();
        }

        hoy = new Date();
    }

    /**
     *
     */
    public void subirFotoTemporal() {

        try {
            String ficheroSalida;
            String urlFotos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/eventos/temporal");
            //urlFotos = "D:/ASOCIATE/evento/temporal";
            ficheroSalida = datosSesion.getUsuarioLogeado().getIdUsuario() + ".jpg";
            //String prFoto = fPerfil.getFileName().substring(0, fPerfil.getFileName().lastIndexOf("."));
            File targetFolder = new File(urlFotos);
            InputStream inputStream = fEvento.getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, "\\" + ficheroSalida));
            IOUtils.copy(inputStream, out);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(out);
            this.nuevoEvento.setImagen(ficheroSalida);
            flash.putNow("evento", nuevoEvento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String moverImagen() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
            String nombre = sdf.format(this.nuevoEvento.getFhinicio())+"_"+this.nuevoEvento.getPrivacidad()+"_"+((int)(Math.random()*100));
            File origen = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/eventos/temporal") + "\\" + datosSesion.getUsuarioLogeado().getIdUsuario() + ".jpg");
            File destino = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/eventos") + "\\" + nombre + ".jpg");

            Files.copy(origen.toPath(), destino.toPath());
            return this.nuevoEvento.getIdEvento() + ".jpg";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     */
    public void cargarListaAmigos() {
        panelAsist = true;
        AmistadDAO amiDAO = new AmistadDAO();
        listaAmigo = amiDAO.getMapaAmigos(this.datosSesion.getUsuarioLogeado().getPersona().getIdPersona());

    }

    /**
     *
     * @return
     */
    public String guardarEvento() {
        nuevoEvento.setFinalizado(Estados.NO_FINALIZADO.getValor());
        nuevoEvento.setIdCreador(this.datosSesion.getUsuarioLogeado());
        nuevoEvento.setPosicion(this.geocode.getCenterGeoMap());
        EventoAsistentes evAs;
        List<Notificacion> notis = new ArrayList();
        NotificacionDAO notiDAO;
        for (Long id : listaAmigoId) {
            if (listaAmigo.containsKey(id)) {
                evAs = new EventoAsistentes();
                evAs.setIdPersona(id);
                evAs.setNotificar("S");
                evAs.setPersona(listaAmigo.get(id));
                evAs.setEstado(Estados.PENDIENTE.getValor());
                evAs.setEvento(nuevoEvento);
                listaAsistente.add(evAs);
            }
        }
        if (!datosSesion.getEsAsociacion()) {
            evAs = new EventoAsistentes();
            evAs.setIdPersona(this.datosSesion.getUsuarioLogeado().getPersona().getIdPersona());
            evAs.setNotificar("S");
            evAs.setPersona(this.datosSesion.getUsuarioLogeado().getPersona());
            evAs.setEstado(Estados.PENDIENTE.getValor());
            evAs.setEvento(nuevoEvento);
            listaAsistente.add(evAs);
        }

        eveDAO = new EventoDAO();
        nuevoEvento.setEventoAsistentesCollection(listaAsistente);
        if(nuevoEvento.getImagen()!=null && !nuevoEvento.getImagen().equals("")){
            nuevoEvento.setImagen(this.moverImagen());
        }else{
            nuevoEvento.setImagen("default.svg");
        }
        
        nuevoEvento.setIdEvento(eveDAO.guardarNuevoEvento(nuevoEvento));
        if (nuevoEvento.getIdEvento() > 0L) {
//            EventoAsistentesDAO evasDAO = new EventoAsistentesDAO();
//            evasDAO.guardarAsistente(asistentes);
            for (Long id : listaAmigoId) {
                notiDAO = new NotificacionDAO();
                notis.add(notiDAO.generarNotificacion(this.datosSesion.getUsuarioLogeado(), Notificaciones.NUEVOEVENTO, UsuarioDAO.getPorID(id), nuevoEvento.getIdEvento(), false));
            }
            notiDAO = new NotificacionDAO();
            notiDAO.guardarListaNotificaciones(notis);
            addInfo("Evento creado con éxito");
            return goToPerfil;
        } else {
            addError("Error al crear el evento, vuelva a intentarlo");
            return null;
        }

    }

    /**
     *
     * @return
     */
    public String irAPerfil() {
        return goToPerfil;
    }

    /**
     *
     * @return
     */
    public Evento getNuevoEvento() {
        return nuevoEvento;
    }

    /**
     *
     * @param nuevoEvento
     */
    public void setNuevoEvento(Evento nuevoEvento) {
        this.nuevoEvento = nuevoEvento;
    }

    /**
     *
     * @return
     */
    public UploadedFile getfEvento() {
        return fEvento;
    }

    /**
     *
     * @param fEvento
     */
    public void setfEvento(UploadedFile fEvento) {
        this.fEvento = fEvento;
    }

    /**
     *
     * @return
     */
    public Date getHoy() {
        return hoy;
    }

    /**
     *
     * @param hoy
     */
    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    /**
     *
     * @return
     */
    public Boolean getPanelAsist() {
        return panelAsist;
    }

    /**
     *
     * @param panelAsist
     */
    public void setPanelAsist(Boolean panelAsist) {
        this.panelAsist = panelAsist;
    }

    /**
     *
     * @return
     */
    public GeocodeViewManagedBean getGeocode() {
        return geocode;
    }

    /**
     *
     * @param geocode
     */
    public void setGeocode(GeocodeViewManagedBean geocode) {
        this.geocode = geocode;
    }

    /**
     *
     * @return
     */
    public Map<Long, Persona> getListaAmigo() {
        return listaAmigo;
    }

    /**
     *
     * @param listaAmigo
     */
    public void setListaAmigo(Map<Long, Persona> listaAmigo) {
        this.listaAmigo = listaAmigo;
    }

    /**
     *
     * @return
     */
    public List<Long> getListaAmigoId() {
        return listaAmigoId;
    }

    /**
     *
     * @param listaAmigoId
     */
    public void setListaAmigoId(List<Long> listaAmigoId) {
        this.listaAmigoId = listaAmigoId;
    }

    /**
     *
     * @return
     */
    public List<EventoAsistentes> getListaAsistente() {
        return listaAsistente;
    }

    /**
     *
     * @param listaAsistente
     */
    public void setListaAsistente(List<EventoAsistentes> listaAsistente) {
        this.listaAsistente = listaAsistente;
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
