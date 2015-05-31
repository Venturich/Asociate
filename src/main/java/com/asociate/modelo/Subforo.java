package com.asociate.modelo;


import java.io.Serializable;
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

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="subforo")
public class Subforo implements Serializable {

    @Column(name="ID_SUBFORO",table="subforo",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idSubforo;
    @OneToMany(targetEntity = Hilo.class,mappedBy = "idSubforo")
    private Collection<Hilo> hiloCollection;
    @Column(name="NOMBRE",table="subforo",length=150)
    @Basic
    private String nombre;

    public Subforo() {

    }
   
    public Integer getIdSubforo() {
        return this.idSubforo;
    }

    public void setIdSubforo(Integer idSubforo) {
        this.idSubforo = idSubforo;
    }
   
    public Collection<Hilo> getHiloCollection() {
        return this.hiloCollection;
    }

    public void setHiloCollection(Collection<Hilo> hiloCollection) {
        this.hiloCollection = hiloCollection;
    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
