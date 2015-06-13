/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.AsociacionDAO;
import com.asociate.dao.NotificacionDAO;
import com.asociate.dao.PersonaDAO;
import com.asociate.dao.ProvinciaDAO;
import com.asociate.dao.PuebloDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Asociacion;
import com.asociate.modelo.Pais;
import com.asociate.modelo.Persona;
import com.asociate.modelo.Provincia;
import com.asociate.modelo.Pueblo;
import com.asociate.modelo.Usuario;
import com.asociate.utils.Ambitos;
import com.asociate.utils.Notificaciones;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "completarRegistroUserMB")
@ViewScoped
public class CompletarRegistroUsuarioManagedBean {

    private final String goToExito = "exitoRegistro";
    private final String goToInicio = "index";

    private Log logger = LogFactory.getLog(this.getClass().getName());
    private Flash flash;
    private UploadedFile comprobante;
    private NotificacionDAO notifDAO;
    private ProvinciaDAO provDAO;
    private PuebloDAO pueDAO;
    private UsuarioDAO userDAO;
    private AsociacionDAO asocDAO;
    private PersonaDAO persDAO;
    private Asociacion regAsoc;
    private Persona regPersona;
    private Usuario regUser;
    private Ambitos[] ambitos = Ambitos.values();
    private Pais paisP;
    private String logEmail;
    private String logPass;
    private int provincia;
    private int pueblo;
    private String pais;
    private String password;
    private List<Pais> paises = new ArrayList();
    private List<Provincia> provincias;
    private List<Pueblo> pueblos;
    private Boolean verDescripcion;
    private Long idPersonaAsoc;
    private List<Asociacion> listAsociacion;
    
    

    /**
     * Creates a new instance of completarRegistroManagedBean
     */
    public CompletarRegistroUsuarioManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        
        logger.error("Compltar Usuario");
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

        if (flash.get("Persona") != null) {
            regPersona = (Persona) flash.get("Persona");
        } else {
            regPersona = new Persona();
        }
        asocDAO = new AsociacionDAO();
        this.listAsociacion = asocDAO.getListaAsociaciones();
    }

    /**
     *
     * @return
     */
    public String completarRegistroPersona() {
        persDAO = new PersonaDAO();
        for (Asociacion a : this.listAsociacion) {
            if (a.getIdAsociacion().compareTo(this.idPersonaAsoc) == 0) {
                List<Asociacion> aux = new ArrayList();
                aux.add(a);
                this.regPersona.setAsociacionCollection(aux);
                break;
            }
        }
        UsuarioDAO usuDAO = new UsuarioDAO();
        usuDAO.guardar(regPersona.getIdUsuario());
        if (persDAO.registrar(this.regPersona)) {
            notifDAO = new NotificacionDAO();
            notifDAO.generarNotificacion(this.regPersona.getIdUsuario(), Notificaciones.REGUSU, null, regPersona.getIdUsuario().getIdUsuario(), true);
            flash.put("Email", this.regUser.getLogin());
            return goToExito;
        } else {
            //error
            return atras();
        }

    }

    /**
     *
     * @return
     */
    public String atras() {
        if (regPersona != null) {
            flash.put("Persona", regPersona);
        }

        return goToInicio;
    }

    /**
     *
     * @return
     */
    public Log getLogger() {
        return logger;
    }

    /**
     *
     * @param logger
     */
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    /**
     *
     * @return
     */
    public Flash getFlash() {
        return flash;
    }

    /**
     *
     * @param flash
     */
    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    /**
     *
     * @return
     */
    public UploadedFile getComprobante() {
        return comprobante;
    }

    /**
     *
     * @param comprobante
     */
    public void setComprobante(UploadedFile comprobante) {
        this.comprobante = comprobante;
    }

    /**
     *
     * @return
     */
    public NotificacionDAO getNotifDAO() {
        return notifDAO;
    }

    /**
     *
     * @param notifDAO
     */
    public void setNotifDAO(NotificacionDAO notifDAO) {
        this.notifDAO = notifDAO;
    }

    /**
     *
     * @return
     */
    public ProvinciaDAO getProvDAO() {
        return provDAO;
    }

    /**
     *
     * @param provDAO
     */
    public void setProvDAO(ProvinciaDAO provDAO) {
        this.provDAO = provDAO;
    }

    /**
     *
     * @return
     */
    public PuebloDAO getPueDAO() {
        return pueDAO;
    }

    /**
     *
     * @param pueDAO
     */
    public void setPueDAO(PuebloDAO pueDAO) {
        this.pueDAO = pueDAO;
    }

    /**
     *
     * @return
     */
    public UsuarioDAO getUserDAO() {
        return userDAO;
    }

    /**
     *
     * @param userDAO
     */
    public void setUserDAO(UsuarioDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     *
     * @return
     */
    public AsociacionDAO getAsocDAO() {
        return asocDAO;
    }

    /**
     *
     * @param asocDAO
     */
    public void setAsocDAO(AsociacionDAO asocDAO) {
        this.asocDAO = asocDAO;
    }

    /**
     *
     * @return
     */
    public PersonaDAO getPersDAO() {
        return persDAO;
    }

    /**
     *
     * @param persDAO
     */
    public void setPersDAO(PersonaDAO persDAO) {
        this.persDAO = persDAO;
    }

    /**
     *
     * @return
     */
    public Asociacion getRegAsoc() {
        return regAsoc;
    }

    /**
     *
     * @param regAsoc
     */
    public void setRegAsoc(Asociacion regAsoc) {
        this.regAsoc = regAsoc;
    }

    /**
     *
     * @return
     */
    public Persona getRegPersona() {
        return regPersona;
    }

    /**
     *
     * @param regPersona
     */
    public void setRegPersona(Persona regPersona) {
        this.regPersona = regPersona;
    }

    /**
     *
     * @return
     */
    public Usuario getRegUser() {
        return regUser;
    }

    /**
     *
     * @param regUser
     */
    public void setRegUser(Usuario regUser) {
        this.regUser = regUser;
    }

    /**
     *
     * @return
     */
    public Ambitos[] getAmbitos() {
        return ambitos;
    }

    /**
     *
     * @param ambitos
     */
    public void setAmbitos(Ambitos[] ambitos) {
        this.ambitos = ambitos;
    }

    /**
     *
     * @return
     */
    public Pais getPaisP() {
        return paisP;
    }

    /**
     *
     * @param paisP
     */
    public void setPaisP(Pais paisP) {
        this.paisP = paisP;
    }

    /**
     *
     * @return
     */
    public String getLogEmail() {
        return logEmail;
    }

    /**
     *
     * @param logEmail
     */
    public void setLogEmail(String logEmail) {
        this.logEmail = logEmail;
    }

    /**
     *
     * @return
     */
    public String getLogPass() {
        return logPass;
    }

    /**
     *
     * @param logPass
     */
    public void setLogPass(String logPass) {
        this.logPass = logPass;
    }

    /**
     *
     * @return
     */
    public int getProvincia() {
        return provincia;
    }

    /**
     *
     * @param provincia
     */
    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }

    /**
     *
     * @return
     */
    public int getPueblo() {
        return pueblo;
    }

    /**
     *
     * @param pueblo
     */
    public void setPueblo(int pueblo) {
        this.pueblo = pueblo;
    }

    /**
     *
     * @return
     */
    public String getPais() {
        return pais;
    }

    /**
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public List<Pais> getPaises() {
        return paises;
    }

    /**
     *
     * @param paises
     */
    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    /**
     *
     * @return
     */
    public List<Provincia> getProvincias() {
        return provincias;
    }

    /**
     *
     * @param provincias
     */
    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    /**
     *
     * @return
     */
    public List<Pueblo> getPueblos() {
        return pueblos;
    }

    /**
     *
     * @param pueblos
     */
    public void setPueblos(List<Pueblo> pueblos) {
        this.pueblos = pueblos;
    }

    /**
     *
     * @return
     */
    public Boolean getVerDescripcion() {
        return verDescripcion;
    }

    /**
     *
     * @param verDescripcion
     */
    public void setVerDescripcion(Boolean verDescripcion) {
        this.verDescripcion = verDescripcion;
    }

    /**
     *
     * @return
     */
    public Long getIdPersonaAsoc() {
        return idPersonaAsoc;
    }

    /**
     *
     * @param idPersonaAsoc
     */
    public void setIdPersonaAsoc(Long idPersonaAsoc) {
        this.idPersonaAsoc = idPersonaAsoc;
    }

    /**
     *
     * @return
     */
    public List<Asociacion> getListAsociacion() {
        return listAsociacion;
    }

    /**
     *
     * @param listAsociacion
     */
    public void setListAsociacion(List<Asociacion> listAsociacion) {
        this.listAsociacion = listAsociacion;
    }

}
