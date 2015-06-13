package com.asociate.modelo;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "pueblo")
public class Pueblo implements Serializable {

    @EmbeddedId
    private PuebloPK idPueblo;
    @Column(name = "NOMBRE", table = "pueblo", nullable = false, length = 45)
    @Basic
    private String nombre;
    @Column(name = "DC", table = "pueblo")
    @Basic
    private Boolean dc;

    /**
     *
     */
    public Pueblo() {

    }

    /**
     *
     * @return
     */
    public PuebloPK getIdPueblo() {
        return idPueblo;
    }

    /**
     *
     * @param idPueblo
     */
    public void setIdPueblo(PuebloPK idPueblo) {
        this.idPueblo = idPueblo;
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

    /**
     *
     * @return
     */
    public Boolean isDc() {
        return this.dc;
    }

    /**
     *
     * @param dc
     */
    public void setDc(Boolean dc) {
        this.dc = dc;
    }
}
