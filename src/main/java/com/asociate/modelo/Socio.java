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

    public Socio() {

    }
   
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    public String getTiposocio() {
        return this.tiposocio;
    }

    public void setTiposocio(String tiposocio) {
        this.tiposocio = tiposocio;
    }
   
    public Integer getNusocio() {
        return this.nusocio;
    }

    public void setNusocio(Integer nusocio) {
        this.nusocio = nusocio;
    }
   
    public long getIdAsociacion() {
        return this.idAsociacion;
    }

    public void setIdAsociacion(long idAsociacion) {
        this.idAsociacion = idAsociacion;
    }
   
    public Date getFhinscripcion() {
        return this.fhinscripcion;
    }

    public void setFhinscripcion(Date fhinscripcion) {
        this.fhinscripcion = fhinscripcion;
    }
   
    public long getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }
   
    public Date getFhvalided() {
        return this.fhvalided;
    }

    public void setFhvalided(Date fhvalided) {
        this.fhvalided = fhvalided;
    }
}
