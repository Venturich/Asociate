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

/**
 *
 * @author Ventura
 */
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
    
    /**
     *
     */
    public Evento() {

    }
   
    /**
     *
     * @return
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
    /**
     *
     * @return
     */
    public Long getIdEvento() {
        return this.idEvento;
    }

    /**
     *
     * @param idEvento
     */
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }
   
    /**
     *
     * @return
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
   
    /**
     *
     * @return
     */
    public String getImagen() {
        return this.imagen;
    }

    /**
     *
     * @param imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
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
    public Date getFhinicio() {
        return this.fhinicio;
    }

    /**
     *
     * @param fhinicio
     */
    public void setFhinicio(Date fhinicio) {
        this.fhinicio = fhinicio;
    }
   
    /**
     *
     * @return
     */
    public Date getHorainicio() {
        return this.horainicio;
    }

    /**
     *
     * @param horainicio
     */
    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }
   
    /**
     *
     * @return
     */
    public String getFinalizado() {
        return this.finalizado;
    }

    /**
     *
     * @param finalizado
     */
    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }
   
    /**
     *
     * @return
     */
    public Usuario getIdCreador() {
        return this.idCreador;
    }

    /**
     *
     * @param idCreador
     */
    public void setIdCreador(Usuario idCreador) {
        this.idCreador = idCreador;
    }
   
    /**
     *
     * @return
     */
    public String getPrivacidad() {
        return this.privacidad;
    }

    /**
     *
     * @param privacidad
     */
    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    /**
     *
     * @return
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     *
     * @param posicion
     */
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    
}
