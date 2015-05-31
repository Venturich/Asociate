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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
    
    @ManyToOne(optional=false,targetEntity = Persona.class, fetch = FetchType.LAZY)
    @JoinColumn(name="RESPONSABLE",referencedColumnName="ID_PERSONA")
    private Persona responsable;
    
    @ManyToOne(targetEntity = Directorio.class, fetch = FetchType.LAZY)
    @JoinColumn(name="ID_DIRECTORIO",referencedColumnName="ID_DIRECTORIO")
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
    

    public Asociacion() {

    }
   
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
    public String getCif() {
        return this.cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
   
    public Persona getResponsable() {
        return this.responsable;
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }
   
    public Directorio getIdDirectorio() {
        return this.idDirectorio;
    }

    public void setIdDirectorio(Directorio idDirectorio) {
        this.idDirectorio = idDirectorio;
    }
   
    public Usuario getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
   
    public Long getIdAsociacion() {
        return this.idAsociacion;
    }

    public void setIdAsociacion(Long idAsociacion) {
        this.idAsociacion = idAsociacion;
    }
   
    public String getWeb() {
        return this.web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
   
    public String getRazonsocial() {
        return this.razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }
   
    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
   
    public String getAmbito() {
        return this.ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
   
    public Date getFhcreacion() {
        return this.fhcreacion;
    }

    public void setFhcreacion(Date fhcreacion) {
        this.fhcreacion = fhcreacion;
    }
   
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
   
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompleta() {
        return completa;
    }

    public void setCompleta(String completa) {
        this.completa = completa;
    }
    
}
