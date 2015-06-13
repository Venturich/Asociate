package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Ventura
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="asociacion",uniqueConstraints=@UniqueConstraint(columnNames={"CIF"}))
public class Asociacion implements Serializable {

    @Column(name="DESCRIPCION",table="asociacion",length=65535)
    @Lob
    @Basic
    private String descripcion;
    
    @Column(name="CIF",table="asociacion",nullable=false,length=12)
    @Basic
    private String cif;
    
    
    @Column(name="RESPONSABLE",table="asociacion")
    @Basic
    private Long responsable;
    
    //@ManyToOne(targetEntity = Directorio.class, fetch = FetchType.LAZY)
    //@JoinColumn(name="ID_DIRECTORIO",referencedColumnName="ID_DIRECTORIO")
    @Transient
    private Directorio idDirectorio;
    
    @ManyToOne(optional=false,targetEntity = Usuario.class, fetch = FetchType.LAZY)
    @JoinColumn(name="ID_USUARIO",referencedColumnName="ID_USUARIO")
    private Usuario idUsuario;
    
    @Column(name="ID_ASOCIACION",table="asociacion",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idAsociacion;
    
    @Column(name="WEB",table="asociacion",length=200)
    @Basic
    private String web;
    
    @Column(name="RAZONSOCIAL",table="asociacion",nullable=false,length=300)
    @Basic
    private String razonsocial;
    
    @Column(name="LOGO",table="asociacion",length=100)
    @Basic
    private String logo;
    
    @Column(name="AMBITO",table="asociacion")
    @Basic
    private String ambito;
    
    @Column(name="FHCREACION",table="asociacion")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhcreacion;
    
    @Column(name="TELEFONO",table="asociacion",length=9)
    @Basic
    private String telefono;
    
    @Column(name="EMAIL",table="asociacion",nullable=false,length=150)
    @Basic
    private String email;
    
    @Column(name="COMPLETA",table="asociacion",length=1)
    @Basic
    private String completa;
    
    /**
     *
     */
    public Asociacion() {

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
    public String getCif() {
        return this.cif;
    }

    /**
     *
     * @param cif
     */
    public void setCif(String cif) {
        this.cif = cif;
    }
   
    /**
     *
     * @return
     */
    public Long getResponsable() {
        return this.responsable;
    }

    /**
     *
     * @param responsable
     */
    public void setResponsable(Long responsable) {
        this.responsable = responsable;
    }
   
    /**
     *
     * @return
     */
    public Directorio getIdDirectorio() {
        return this.idDirectorio;
    }

    /**
     *
     * @param idDirectorio
     */
    public void setIdDirectorio(Directorio idDirectorio) {
        this.idDirectorio = idDirectorio;
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
    public Long getIdAsociacion() {
        return this.idAsociacion;
    }

    /**
     *
     * @param idAsociacion
     */
    public void setIdAsociacion(Long idAsociacion) {
        this.idAsociacion = idAsociacion;
    }
   
    /**
     *
     * @return
     */
    public String getWeb() {
        return this.web;
    }

    /**
     *
     * @param web
     */
    public void setWeb(String web) {
        this.web = web;
    }
   
    /**
     *
     * @return
     */
    public String getRazonsocial() {
        return this.razonsocial;
    }

    /**
     *
     * @param razonsocial
     */
    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }
   
    /**
     *
     * @return
     */
    public String getLogo() {
        return this.logo;
    }

    /**
     *
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }
   
    /**
     *
     * @return
     */
    public String getAmbito() {
        return this.ambito;
    }

    /**
     *
     * @param ambito
     */
    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
   
    /**
     *
     * @return
     */
    public Date getFhcreacion() {
        return this.fhcreacion;
    }

    /**
     *
     * @param fhcreacion
     */
    public void setFhcreacion(Date fhcreacion) {
        this.fhcreacion = fhcreacion;
    }
   
    /**
     *
     * @return
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    /**
     *
     * @return
     */
    public String getCompleta() {
        return completa;
    }

    /**
     *
     * @param completa
     */
    public void setCompleta(String completa) {
        this.completa = completa;
    }
    
}
