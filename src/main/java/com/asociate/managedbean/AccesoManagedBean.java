package com.asociate.managedbean;

import com.asociate.dao.AsociacionDAO;
import com.asociate.dao.NotificacionDAO;
import com.asociate.dao.PersonaDAO;
import com.asociate.dao.ProvinciaDAO;
import com.asociate.dao.PuebloDAO;
import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Asociacion;
import com.asociate.modelo.Direccion;
import com.asociate.modelo.Directorio;
import com.asociate.modelo.Pais;
import com.asociate.modelo.Persona;
import com.asociate.modelo.Provincia;
import com.asociate.modelo.Pueblo;
import com.asociate.modelo.Usuario;
import com.asociate.utils.Ambitos;
import com.asociate.utils.Notificaciones;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "accesoMB")
@ViewScoped
public class AccesoManagedBean extends AsociateError implements Serializable {

    private String goToPerfil = "perfilPrincipal";
    private String goToSubirDoc = "completarRegistroAsociacion";
    private String goToElegirAsoc = "completarRegistroUsuario";

    private String goToAdmin = "menuPrincipal";

    @ManagedProperty(value = "#{sesionMB}")
    private DatosSesion datosSesion;

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
    private int provincia=0;
    private int pueblo=0;
    private String pais;
    private String password;
    private List<Pais> paises = new ArrayList();
    private List<Provincia> provincias;
    private List<Pueblo> pueblos;
    private Boolean verDescripcion;
    private Long idPersonaAsoc;

    /**
     * Creates a new instance of AccesoManagedBean
     */
    public AccesoManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
         flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
         FacesContext.getCurrentInstance().getExternalContext().getFlash().setRedirect(true);
        this.regAsoc = new Asociacion();
        this.regUser = new Usuario();
        this.regPersona = new Persona();

        
        if (flash.get("Asociacion") != null) {
            regAsoc = (Asociacion) flash.get("Asociacion");
        } else {
            regAsoc = new Asociacion();
        }
        
        if (flash.get("Persona") != null) {
            regPersona = (Persona) flash.get("Persona");
        } else {
            regPersona = new Persona();
        }
        
        paisP = new Pais();
        paisP.setCodigo("ES");
        paisP.setNombre("ESPAÑA");
        paisP.setIdPais((short) 01);
        paises.add(paisP);
        regUser = new Usuario();

        asignarProvincias();

    }

    /**
     *
     */
    public void resetear() {
        logger.info("Resetear");
        logEmail = "";
        logPass = "";
        provincia = 0;
        pueblo = 0;
        pais = "";
        password = "";
        regAsoc = new Asociacion();
        regPersona = new Persona();
        regUser = new Usuario();
    }

    /**
     *
     */
    public void asignarProvincias() {
        provDAO = new ProvinciaDAO();
        provincias = provDAO.getTodas();
    }

    /**
     * Asigna una colecciÃƒÂ³n de valores a pueblos
     */
    public void asignarPueblos() {
        pueDAO = new PuebloDAO();
        this.setPueblos(pueDAO.getListaPueblos(this.provincia));

    }

    /**
     *
     * @return
     */
    public String registrarPersona() {
        logger.info("registrar persona");
        this.regUser.setBloqueado("N");
        this.regUser.setConfirmado("S");
        this.regUser.setTipo("U");
        this.regUser.setFhultimaconexion(new Date());
        
        regPersona.setIdUsuario(regUser);
        regPersona.setEmail(regUser.getLogin());
        Direccion direccion = new Direccion();
        direccion.setIdPais(paisP);
        direccion.setIdPueblo(this.pueblo);
        for (Provincia p : provincias) {
            if (this.provincia == p.getCodigo()) {
                direccion.setIdEstado(p);
                break;
            }
        }
        flash.put("Persona", regPersona);
        flash.put("Fase", 1);
        flash.putNow("p", "PPPPPPPPPPPPPPPPP");
        logger.info(flash.get("p")+" !!!!");
        return goToElegirAsoc;

    }

    /**
     *
     * @return
     */
    public String registrarAsociacion() {
        logger.info("Registrando asociacion");
        this.regUser.setBloqueado("N");
        this.regUser.setConfirmado("N");
        this.regUser.setTipo("S");
        this.regUser.setFhultimaconexion(new Date());
        this.regUser.setFhalta(new Date());
        regAsoc.setIdUsuario(regUser);
        regAsoc.setCompleta("N");
        regAsoc.setIdDirectorio(new Directorio());
        flash.put("Asociacion", this.regAsoc);
        flash.put("Fase", "1");
        flash.putNow("p", "PPPPPPPPPPPPPPPPP");
        logger.info(regAsoc.getCif()+" !!!!");
        logger.info(goToSubirDoc);
        return goToSubirDoc;
    }

    /**
     *
     * @return
     */
    public String login() {
        userDAO = new UsuarioDAO();
        Usuario aux= new Usuario();
        aux.setLogin(logEmail);
        aux.setPassword(logPass);
        aux = userDAO.comprobarLogin(aux);

        if (aux != null && "A".equals(aux.getTipo())) {
            logger.info("Login de admin");
            aux.setTipo("A");
            this.datosSesion.setUsuarioLogeado(aux);
            this.flash.put("User", aux);
            return goToAdmin;
        } else if (aux != null && "U".equals(aux.getTipo())) {
            logger.info("Login de usuario");
            this.flash.put("User", aux);
            this.datosSesion.setEsAsociacion(false);
            return goToPerfil;
        } else if (aux != null && "S".equals(aux.getTipo())) {
            logger.info("Login de asociacion");
            this.flash.put("User", aux);
            this.datosSesion.setEsAsociacion(true);
            return goToPerfil;
        } else {
            this.addError("Usuario o contraseña incorrectos");
            return null;
        }

    }

    //getter y setter

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

}
