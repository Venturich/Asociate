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
@Table(name="provincia")
public class Provincia implements Serializable {

    @Column(name="IDPROV",table="provincia",nullable=false)
    @Id
    private Short codigo;
    @OneToMany(targetEntity = Direccion.class,mappedBy = "idEstado")
    private Collection<Direccion> direccionCollection;
    @Column(name="NOMBRE",table="provincia",length=45)
    @Basic
    private String nombre;

    /**
     *
     */
    public Provincia() {

    }
   
    /**
     *
     * @return
     */
    public Short getCodigo() {
        return this.codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(Short codigo) {
        this.codigo = codigo;
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
