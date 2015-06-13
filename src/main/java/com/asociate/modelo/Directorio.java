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
@Table(name="directorio")
public class Directorio implements Serializable {

    @Column(name="ID_DIRECTORIO",table="directorio",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idDirectorio;
    @Column(name="RAIZ",table="directorio",length=100)
    @Basic
    private String raiz;
//    @OneToMany(targetEntity = Asociacion.class,mappedBy = "idDirectorio")
//    private Collection<Asociacion> asociacionCollection;
    @Column(name="PERMISOS",table="directorio")
    @Basic
    private String permisos;
    @Column(name="NOMBRE",table="directorio",length=100)
    @Basic
    private String nombre;

    /**
     *
     */
    public Directorio() {

    }
   
    /**
     *
     * @return
     */
    public Integer getIdDirectorio() {
        return this.idDirectorio;
    }

    /**
     *
     * @param idDirectorio
     */
    public void setIdDirectorio(Integer idDirectorio) {
        this.idDirectorio = idDirectorio;
    }
   
    /**
     *
     * @return
     */
    public String getRaiz() {
        return this.raiz;
    }

    /**
     *
     * @param raiz
     */
    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }
   
//    public Collection<Asociacion> getAsociacionCollection() {
//        return this.asociacionCollection;
//    }
//
//    public void setAsociacionCollection(Collection<Asociacion> asociacionCollection) {
//        this.asociacionCollection = asociacionCollection;
//    }
   
    /**
     *
     * @return
     */
       
    public String getPermisos() {
        return this.permisos;
    }

    /**
     *
     * @param permisos
     */
    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
   
    /**
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
