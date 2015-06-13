package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="socio")
public class Socio implements Serializable {

    @Column(name="ESTADO",table="socio",nullable=false)
    @Basic
    private String estado;
    @Column(name="TIPOSOCIO",table="socio")
    @Basic
    private String tiposocio;
    @Column(name="NUSOCIO",table="socio")
    @Basic
    private Integer nusocio;
    @Column(name="ID_ASOCIACION",table="socio",nullable=false)
    @Id
    private long idAsociacion;
    @Column(name="FHINSCRIPCION",table="socio")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhinscripcion;
    @Column(name="ID_PERSONA",table="socio",nullable=false)
    @Id
    private long idPersona;
    @Column(name="FHVALIDED",table="socio")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhvalided;

    /**
     *
     */
    public Socio() {

    }
   
    /**
     *
     * @return
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    /**
     *
     * @return
     */
    public String getTiposocio() {
        return this.tiposocio;
    }

    /**
     *
     * @param tiposocio
     */
    public void setTiposocio(String tiposocio) {
        this.tiposocio = tiposocio;
    }
   
    /**
     *
     * @return
     */
    public Integer getNusocio() {
        return this.nusocio;
    }

    /**
     *
     * @param nusocio
     */
    public void setNusocio(Integer nusocio) {
        this.nusocio = nusocio;
    }
   
    /**
     *
     * @return
     */
    public long getIdAsociacion() {
        return this.idAsociacion;
    }

    /**
     *
     * @param idAsociacion
     */
    public void setIdAsociacion(long idAsociacion) {
        this.idAsociacion = idAsociacion;
    }
   
    /**
     *
     * @return
     */
    public Date getFhinscripcion() {
        return this.fhinscripcion;
    }

    /**
     *
     * @param fhinscripcion
     */
    public void setFhinscripcion(Date fhinscripcion) {
        this.fhinscripcion = fhinscripcion;
    }
   
    /**
     *
     * @return
     */
    public long getIdPersona() {
        return this.idPersona;
    }

    /**
     *
     * @param idPersona
     */
    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }
   
    /**
     *
     * @return
     */
    public Date getFhvalided() {
        return this.fhvalided;
    }

    /**
     *
     * @param fhvalided
     */
    public void setFhvalided(Date fhvalided) {
        this.fhvalided = fhvalided;
    }
}
