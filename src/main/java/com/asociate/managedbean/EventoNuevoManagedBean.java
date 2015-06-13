/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AmistadDAO;
import com.asociate.dao.EventoDAO;
import com.asociate.dao.PersonaDAO;
import com.asociate.modelo.Evento;
import com.asociate.modelo.EventoAsistentes;
import com.asociate.modelo.Persona;
import com.asociate.utils.Estados;
import com.mchange.io.FileUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ventura
 */
@Named(value = "eventoNuevoMB")
@ViewScoped
public class EventoNuevoManagedBean extends AsociateError implements Serializable {

    private final String goToPerfil = "";

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
        listaAmigoId = new ArrayList();
        listaAsistente = new ArrayList();
        nuevoEvento = new Evento();
    }

    /**
     *
     */
    public void subirFotoTemporal() {
        try {
            String ficheroSalida;
            String urlFotos;
            //this.urlFotos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/productos");

            urlFotos = "D:/ASOCIATE/evento/temporal";
            ficheroSalida = "\\" + datosSesion.getUsuarioLogeado().getIdUsuario() + ".jpg";

            //String prFoto = fPerfil.getFileName().substring(0, fPerfil.getFileName().lastIndexOf("."));
            File targetFolder = new File(urlFotos);
            InputStream inputStream = fEvento.getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, ficheroSalida));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            //if (esAsociacion) {
            //  this.usuarioLogeado.getAsociacion().setLogo();
            //} 

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
            File origen = new File("D:/ASOCIATE/evento/temporal\\" + datosSesion.getUsuarioLogeado().getIdUsuario() + ".jpg");
            File destino = new File("D:/ASOCIATE/evento/" + this.nuevoEvento.getIdEvento() + ".jpg");

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
        for (Long id : listaAmigoId) {
            if (listaAmigo.containsKey(id)) {
                evAs = new EventoAsistentes();
                evAs.setIdPersona(id);
                evAs.setNotificar("S");
                evAs.setPersona(listaAmigo.get(id));
                evAs.setEstado(Estados.PENDIENTE.getValor());
                evAs.setEvento(nuevoEvento);
            }
        }

        eveDAO = new EventoDAO();
        nuevoEvento.setEventoAsistentesCollection(listaAsistente);
        if (eveDAO.guardarNuevoEvento(nuevoEvento)) {
            nuevoEvento.setImagen(this.moverImagen());
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

}
