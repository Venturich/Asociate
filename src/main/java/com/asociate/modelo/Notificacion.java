package com.asociate.modelo;


import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Long;
import java.lang.String;
import java.math.BigInteger;
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
@Table(name="notificacion")
public class Notificacion implements Serializable {

    @Column(name="TEXTO",table="notificacion",length=65535)
    @Lob
    @Basic
    private String texto;
    @Column(name="TIPO_GENERADOR",table="notificacion",length=3)
    @Basic
    private String tipoGenerador;
    @Column(name="FECHA",table="notificacion")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;
    @Column(name="TIPO",table="notificacion")
    @Basic
    private String tipo;
    @Column(name="ID_NOTIFICACION",table="notificacion",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idNotificacion;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name="ID_USUARIO_ORIGEN",referencedColumnName="ID_USUARIO")
    private Usuario idUsuarioOrigen;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name="ID_USUARIO",referencedColumnName="ID_USUARIO")
    private Usuario idUsuario;
    @Column(name="ID_ELEMENTO_GENERADOR",table="notificacion")
    @Basic
    private Long idElementoGenerador;
    @Column(name="VISTO",table="notificacion")
    @Basic
    private Boolean visto;

    /**
     *
     */
    public Notificacion() {

    }
   
    /**
     *
     * @return
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     *
     * @param texto
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
   
    /**
     *
     * @return
     */
    public String getTipoGenerador() {
        return this.tipoGenerador;
    }

    /**
     *
     * @param tipoGenerador
     */
    public void setTipoGenerador(String tipoGenerador) {
        this.tipoGenerador = tipoGenerador;
    }
   
    /**
     *
     * @return
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   
    /**
     *
     * @return
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
    /**
     *
     * @return
     */
    public Long getIdNotificacion() {
        return this.idNotificacion;
    }

    /**
     *
     * @param idNotificacion
     */
    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
   
    /**
     *
     * @return
     */
    public Usuario getIdUsuarioOrigen() {
        return this.idUsuarioOrigen;
    }

    /**
     *
     * @param idUsuarioOrigen
     */
    public void setIdUsuarioOrigen(Usuario idUsuarioOrigen) {
        this.idUsuarioOrigen = idUsuarioOrigen;
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
    public Long getIdElementoGenerador() {
        return this.idElementoGenerador;
    }

    /**
     *
     * @param idElementoGenerador
     */
    public void setIdElementoGenerador(Long idElementoGenerador) {
        this.idElementoGenerador = idElementoGenerador;
    }
    
    /**
     *
     * @return
     */
    public Boolean isVisto() {
        return this.visto;
    }

    /**
     *
     * @param visto
     */
    public void setVisto(Boolean visto) {
        this.visto = visto;
    }
}
