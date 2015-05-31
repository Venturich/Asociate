package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="evento")
public class Evento implements Serializable {

    @Column(name="DESCRIPCION",table="evento",length=65535)
    @Lob
    @Basic
    private String descripcion;
    
    @Column(name="ID_EVENTO",table="evento",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idEvento;
    
    @Column(name="TITULO",table="evento",length=150)
    @Basic
    private String titulo;
    
    @Column(name="IMAGEN",table="evento",length=200)
    @Basic
    private String imagen;
    
    @OneToMany(targetEntity = EventoAsistentes.class,mappedBy = "evento")
    private Collection<EventoAsistentes> eventoAsistentesCollection;
    
    @Column(name="FHINICIO",table="evento")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhinicio;
    
    @Column(name="HORAINICIO",table="evento")
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horainicio;
    
    @Column(name="FINALIZADO",table="evento")
    @Basic
    private String finalizado;
    
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name="ID_CREADOR",referencedColumnName="ID_USUARIO")
    private Usuario idCreador;
    
    @Column(name="PRIVACIDAD",table="evento",nullable=false)
    @Basic
    private String privacidad;
    
    @Column(name="POSICION", table="evento",nullable=true)
    @Basic
    private String posicion;
    

    public Evento() {

    }
   
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
    public Long getIdEvento() {
        return this.idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }
   
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
   
    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
   
    public Collection<EventoAsistentes> getEventoAsistentesCollection() {
        return this.eventoAsistentesCollection;
    }

    public void setEventoAsistentesCollection(Collection<EventoAsistentes> eventoAsistentesCollection) {
        this.eventoAsistentesCollection = eventoAsistentesCollection;
    }
   
    public Date getFhinicio() {
        return this.fhinicio;
    }

    public void setFhinicio(Date fhinicio) {
        this.fhinicio = fhinicio;
    }
   
    public Date getHorainicio() {
        return this.horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }
   
    public String getFinalizado() {
        return this.finalizado;
    }

    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }
   
    public Usuario getIdCreador() {
        return this.idCreador;
    }

    public void setIdCreador(Usuario idCreador) {
        this.idCreador = idCreador;
    }
   
    public String getPrivacidad() {
        return this.privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    
}
