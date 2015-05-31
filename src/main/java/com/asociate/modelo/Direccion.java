package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="direccion")
public class Direccion implements Serializable {

    @Column(name="ID_DIRECCION",table="direccion",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idDireccion;
    @ManyToOne(targetEntity = Provincia.class)
    @JoinColumn(name="ID_ESTADO",referencedColumnName="IDPROV")
    private Provincia idEstado;
    @OneToMany(targetEntity = Persona.class,mappedBy = "idDireccion")
    private Collection<Persona> personaCollection;
    @ManyToOne(optional=false,targetEntity = Pais.class)
    @JoinColumn(name="ID_PAIS",referencedColumnName="ID_PAIS")
    private Pais idPais;
    @Column(name="DIRECCION",table="direccion",nullable=false,length=150)
    @Basic
    private String direccion;
    @Column(name="ID_TIPODIRECCION",table="direccion")
    @Basic
    private String idTipodireccion;
    @Column(name="ID_PRIVACIDAD",table="direccion",nullable=false)
    @Basic
    private String idPrivacidad;
    @Column(name="ID_PUEBLO", table="direccion")
    @Basic
    private Integer idPueblo;

    public Direccion() {

    }
   
    public Integer getIdDireccion() {
        return this.idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }
   
    public Provincia getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(Provincia idEstado) {
        this.idEstado = idEstado;
    }
   
    public Collection<Persona> getPersonaCollection() {
        return this.personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }
   
    public Pais getIdPais() {
        return this.idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }
   
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
    public String getIdTipodireccion() {
        return this.idTipodireccion;
    }

    public void setIdTipodireccion(String idTipodireccion) {
        this.idTipodireccion = idTipodireccion;
    }
   
    public String getIdPrivacidad() {
        return this.idPrivacidad;
    }

    public void setIdPrivacidad(String idPrivacidad) {
        this.idPrivacidad = idPrivacidad;
    }
   
    public Integer getIdPueblo() {
        return this.idPueblo;
    }

    public void setIdPueblo(Integer idPueblo) {
        this.idPueblo = idPueblo;
    }
}
