package com.asociate.modelo;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="usuario")
public class Usuario implements Serializable {

    @Column(name="TIPO",table="usuario",nullable=false)
    @Basic
    private String tipo;
    
    @OneToOne(targetEntity = Persona.class,mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private Persona persona;
    
    @Column(name="ID_USUARIO",table="usuario",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idUsuario;
    
    @Column(name="BLOQUEADO",table="usuario",nullable=false)
    @Basic
    private String bloqueado;
    
    @OneToMany(targetEntity = Mensajeria.class,mappedBy = "idDestino",fetch = FetchType.LAZY)
    private Collection<Mensajeria> mensajeriaCollection;
    
    @Column(name="LOGIN",table="usuario",nullable=false,length=150)
    @Basic
    private String login;
    
    @Column(name="FHALTA",table="usuario")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhalta;
    
    @OneToMany(targetEntity = Mensajeria.class,mappedBy = "idOrigen", fetch = FetchType.LAZY)
    private Collection<Mensajeria> mensajeriaCollection1;
    
    @Column(name="PASSWORD",table="usuario",nullable=false)
    @Lob
    @Basic
    private String password;
    
    @OneToMany(targetEntity = Post.class,mappedBy = "autor",fetch = FetchType.LAZY)
    private Collection<Post> postCollection;
    
    @OneToMany(targetEntity = Notificacion.class,mappedBy = "idUsuario", fetch = FetchType.EAGER)
    private Collection<Notificacion> notificacionCollection;
    
    @Column(name="CONFIRMADO",table="usuario",nullable=false)
    @Basic
    private String confirmado;
    
    @OneToOne(targetEntity = Asociacion.class,mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private Asociacion asociacion;
    
    @Column(name="FHULTIMACONEXION",table="usuario",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    private Date fhultimaconexion;
    
    @OneToMany(targetEntity = Notificacion.class,mappedBy = "idUsuarioOrigen", fetch = FetchType.LAZY)
    private Collection<Notificacion> notificacionesPropias;
    
    @OneToMany(targetEntity = Comentario.class,mappedBy = "idAutor", fetch = FetchType.LAZY)
    private Collection<Comentario> comentarioCollection;
    
    @OneToMany(targetEntity = Evento.class,mappedBy = "idCreador",fetch = FetchType.LAZY)
    private Collection<Evento> eventoCollection;

    public Usuario() {

    }
   
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
   
    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
   
    public String getBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }
   
    public Collection<Mensajeria> getMensajeriaCollection() {
        return this.mensajeriaCollection;
    }

    public void setMensajeriaCollection(Collection<Mensajeria> mensajeriaCollection) {
        this.mensajeriaCollection = mensajeriaCollection;
    }
   
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
   
    public Date getFhalta() {
        return this.fhalta;
    }

    public void setFhalta(Date fhalta) {
        this.fhalta = fhalta;
    }
   
    public Collection<Mensajeria> getMensajeriaCollection1() {
        return this.mensajeriaCollection1;
    }

    public void setMensajeriaCollection1(Collection<Mensajeria> mensajeriaCollection1) {
        this.mensajeriaCollection1 = mensajeriaCollection1;
    }
   
    public  String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public Collection<Post> getPostCollection() {
        return this.postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }
   
    public Collection<Notificacion> getNotificacionCollection() {
        return this.notificacionCollection;
    }

    public void setNotificacionCollection(Collection<Notificacion> notificacionCollection) {
        this.notificacionCollection = notificacionCollection;
    }
   
    public String getConfirmado() {
        return this.confirmado;
    }

    public void setConfirmado(String confirmado) {
        this.confirmado = confirmado;
    }
   
    public Asociacion getAsociacion() {
        return this.asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }
   
    public Date getFhultimaconexion() {
        return this.fhultimaconexion;
    }

    public void setFhultimaconexion(Date fhultimaconexion) {
        this.fhultimaconexion = fhultimaconexion;
    }
   
    public Collection<Notificacion> getNotificacionesPropias() {
        return this.notificacionesPropias;
    }

    public void setNotificacionesPropias(Collection<Notificacion> notificacionCollection1) {
        this.notificacionesPropias = notificacionCollection1;
    }
   
    public Collection<Comentario> getComentarioCollection() {
        return this.comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }
   
    public Collection<Evento> getEventoCollection() {
        return this.eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }
}
