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

/**
 *
 * @author Ventura
 */
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

    /**
     *
     */
    public Direccion() {

    }
   
    /**
     *
     * @return
     */
    public Integer getIdDireccion() {
        return this.idDireccion;
    }

    /**
     *
     * @param idDireccion
     */
    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }
   
    /**
     *
     * @return
     */
    public Provincia getIdEstado() {
        return this.idEstado;
    }

    /**
     *
     * @param idEstado
     */
    public void setIdEstado(Provincia idEstado) {
        this.idEstado = idEstado;
    }
   
    /**
     *
     * @return
     */
    public Collection<Persona> getPersonaCollection() {
        return this.personaCollection;
    }

    /**
     *
     * @param personaCollection
     */
    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }
   
    /**
     *
     * @return
     */
    public Pais getIdPais() {
        return this.idPais;
    }

    /**
     *
     * @param idPais
     */
    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }
   
    /**
     *
     * @return
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
    /**
     *
     * @return
     */
    public String getIdTipodireccion() {
        return this.idTipodireccion;
    }

    /**
     *
     * @param idTipodireccion
     */
    public void setIdTipodireccion(String idTipodireccion) {
        this.idTipodireccion = idTipodireccion;
    }
   
    /**
     *
     * @return
     */
    public String getIdPrivacidad() {
        return this.idPrivacidad;
    }

    /**
     *
     * @param idPrivacidad
     */
    public void setIdPrivacidad(String idPrivacidad) {
        this.idPrivacidad = idPrivacidad;
    }
   
    /**
     *
     * @return
     */
    public Integer getIdPueblo() {
        return this.idPueblo;
    }

    /**
     *
     * @param idPueblo
     */
    public void setIdPueblo(Integer idPueblo) {
        this.idPueblo = idPueblo;
    }
}
