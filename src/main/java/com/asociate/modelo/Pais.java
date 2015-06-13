package com.asociate.modelo;


import java.io.Serializable;
import java.lang.Short;
import java.lang.String;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="pais")
public class Pais implements Serializable {

    @Column(name="CODIGOPAIS",table="pais",length=2)
    @Basic
    private String codigo;
    @Column(name="ID_PAIS",table="pais",nullable=false)
    @Id
    private Short idPais;
    @OneToMany(targetEntity = Direccion.class,mappedBy = "idPais")
    private Collection<Direccion> direccionCollection;
    @Column(name="NOMBRE",table="pais",length=100)
    @Basic
    private String nombre;

    /**
     *
     */
    public Pais() {

    }
   
    /**
     *
     * @return
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
   
    /**
     *
     * @return
     */
    public Short getIdPais() {
        return this.idPais;
    }

    /**
     *
     * @param idPais
     */
    public void setIdPais(Short idPais) {
        this.idPais = idPais;
    }
   
    /**
     *
     * @return
     */
    public Collection<Direccion> getDireccionCollection() {
        return this.direccionCollection;
    }

    /**
     *
     * @param direccionCollection
     */
    public void setDireccionCollection(Collection<Direccion> direccionCollection) {
        this.direccionCollection = direccionCollection;
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
