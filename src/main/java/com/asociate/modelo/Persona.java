package com.asociate.modelo;


import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Ventura
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="persona",uniqueConstraints=@UniqueConstraint(columnNames={"ID_USUARIO","EMAIL"}))
public class Persona implements Serializable {

    @ManyToOne(targetEntity = Direccion.class)
    @JoinColumn(name="ID_DIRECCION",referencedColumnName="ID_DIRECCION")
    private Direccion idDireccion;
    @Column(name="APELLIDOP",table="persona",nullable=false,length=100)
    @Basic
    private String apellidop;
    @OneToOne(optional=false,targetEntity = Usuario.class)
    @JoinColumn(name="ID_USUARIO",referencedColumnName="ID_USUARIO")
    private Usuario idUsuario;
    @Column(name="ALIAS",table="persona",length=150)
    @Basic
    private String alias;
    @Column(name="APELLIDOM",table="persona",length=100)
    @Basic
    private String apellidom;
    @OneToMany(targetEntity = EventoAsistentes.class,mappedBy = "persona")
    private Collection<EventoAsistentes> eventoAsistentesCollection;
    @OneToMany(targetEntity = Asociacion.class,mappedBy = "responsable")
    private Collection<Asociacion> asociacionCollection;
    @Column(name="NOMBRE",table="persona",nullable=false,length=100)
    @Basic
    private String nombre;
    @Column(name="ID_PERSONA",table="persona",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idPersona;
    @Column(name="EMAIL",table="persona",nullable=false,length=150)
    @Basic
    private String email;

    /**
     *
     */
    public Persona() {

    }
   
    /**
     *
     * @return
     */
    public Direccion getIdDireccion() {
        return this.idDireccion;
    }

    /**
     *
     * @param idDireccion
     */
    public void setIdDireccion(Direccion idDireccion) {
        this.idDireccion = idDireccion;
    }
   
    /**
     *
     * @return
     */
    public String getApellidop() {
        return this.apellidop;
    }

    /**
     *
     * @param apellidop
     */
    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }
   
    /**
     *
     * @return
     */
    public Usuario getIdUsuario() {
        return this.idUsuario;
    }

    /**
     *
     * @param idUsuario
     */
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
   
    /**
     *
     * @return
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     *
     * @param alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
   
    /**
     *
     * @return
     */
    public String getApellidom() {
        return this.apellidom;
    }

    /**
     *
     * @param apellidom
     */
    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }
   
    /**
     *
     * @return
     */
    public Collection<EventoAsistentes> getEventoAsistentesCollection() {
        return this.eventoAsistentesCollection;
    }

    /**
     *
     * @param eventoAsistentesCollection
     */
    public void setEventoAsistentesCollection(Collection<EventoAsistentes> eventoAsistentesCollection) {
        this.eventoAsistentesCollection = eventoAsistentesCollection;
    }
   
    /**
     *
     * @return
     */
    public Collection<Asociacion> getAsociacionCollection() {
        return this.asociacionCollection;
    }

    /**
     *
     * @param asociacionCollection
     */
    public void setAsociacionCollection(Collection<Asociacion> asociacionCollection) {
        this.asociacionCollection = asociacionCollection;
    }
   
    /**
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    /**
     *
     * @return
     */
    public Long getIdPersona() {
        return this.idPersona;
    }

    /**
     *
     * @param idPersona
     */
    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }
   
    /**
     *
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
