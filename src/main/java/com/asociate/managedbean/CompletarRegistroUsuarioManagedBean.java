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
        if (persDAO.registrar(this.regPersona)) {
            notifDAO = new NotificacionDAO();
            notifDAO.generarNotificacion(this.regPersona.getIdUsuario(), Notificaciones.REGUSU, null, null, true);
            flash.put("Email", this.regUser.getLogin());
            return goToExito;
        } else {
            //error
            return atras();
        }

    }

    public String atras() {
        if (regPersona != null) {
            flash.put("Persona", regPersona);
        }

        return goToInicio;
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public Flash getFlash() {
        return flash;
    }

    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    public UploadedFile getComprobante() {
        return comprobante;
    }

    public void setComprobante(UploadedFile comprobante) {
        this.comprobante = comprobante;
    }

    public NotificacionDAO getNotifDAO() {
        return notifDAO;
    }

    public void setNotifDAO(NotificacionDAO notifDAO) {
        this.notifDAO = notifDAO;
    }

    public ProvinciaDAO getProvDAO() {
        return provDAO;
    }

    public void setProvDAO(ProvinciaDAO provDAO) {
        this.provDAO = provDAO;
    }

    public PuebloDAO getPueDAO() {
        return pueDAO;
    }

    public void setPueDAO(PuebloDAO pueDAO) {
        this.pueDAO = pueDAO;
    }

    public UsuarioDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UsuarioDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AsociacionDAO getAsocDAO() {
        return asocDAO;
    }

    public void setAsocDAO(AsociacionDAO asocDAO) {
        this.asocDAO = asocDAO;
    }

    public PersonaDAO getPersDAO() {
        return persDAO;
    }

    public void setPersDAO(PersonaDAO persDAO) {
        this.persDAO = persDAO;
    }

    public Asociacion getRegAsoc() {
        return regAsoc;
    }

    public void setRegAsoc(Asociacion regAsoc) {
        this.regAsoc = regAsoc;
    }

    public Persona getRegPersona() {
        return regPersona;
    }

    public void setRegPersona(Persona regPersona) {
        this.regPersona = regPersona;
    }

    public Usuario getRegUser() {
        return regUser;
    }

    public void setRegUser(Usuario regUser) {
        this.regUser = regUser;
    }

    public Ambitos[] getAmbitos() {
        return ambitos;
    }

    public void setAmbitos(Ambitos[] ambitos) {
        this.ambitos = ambitos;
    }

    public Pais getPaisP() {
        return paisP;
    }

    public void setPaisP(Pais paisP) {
        this.paisP = paisP;
    }

    public String getLogEmail() {
        return logEmail;
    }

    public void setLogEmail(String logEmail) {
        this.logEmail = logEmail;
    }

    public String getLogPass() {
        return logPass;
    }

    public void setLogPass(String logPass) {
        this.logPass = logPass;
    }

    public int getProvincia() {
        return provincia;
    }

    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }

    public int getPueblo() {
        return pueblo;
    }

    public void setPueblo(int pueblo) {
        this.pueblo = pueblo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Pueblo> getPueblos() {
        return pueblos;
    }

    public void setPueblos(List<Pueblo> pueblos) {
        this.pueblos = pueblos;
    }

    public Boolean getVerDescripcion() {
        return verDescripcion;
    }

    public void setVerDescripcion(Boolean verDescripcion) {
        this.verDescripcion = verDescripcion;
    }

    public Long getIdPersonaAsoc() {
        return idPersonaAsoc;
    }

    public void setIdPersonaAsoc(Long idPersonaAsoc) {
        this.idPersonaAsoc = idPersonaAsoc;
    }

    public List<Asociacion> getListAsociacion() {
        return listAsociacion;
    }

    public void setListAsociacion(List<Asociacion> listAsociacion) {
        this.listAsociacion = listAsociacion;
    }

}
