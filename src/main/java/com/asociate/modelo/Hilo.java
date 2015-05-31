package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="hilo")
public class Hilo implements Serializable {

    @Column(name="TEXTO",table="hilo",length=65535)
    @Lob
    @Basic
    private String texto;
    @Column(name="ICONO",table="hilo",length=3)
    @Basic
    private String icono;
    @ManyToOne(targetEntity = Subforo.class)
    @JoinColumn(name="ID_SUBFORO",referencedColumnName="ID_SUBFORO")
    private Subforo idSubforo;
    @OneToMany(targetEntity = Post.class,mappedBy = "idHilo")
    private Collection<Post> postCollection;
    @Column(name="FHCREACION",table="hilo",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    private Date fhcreacion;
    @Column(name="NOMBRE",table="hilo",length=150)
    @Basic
    private String nombre;
    @Column(name="ID_HILO",table="hilo",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idHilo;
    @Column(name="VISIBILIDAD",table="hilo")
    @Basic
    private String visibilidad;

    public Hilo() {

    }
   
    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
   
    public String getIcono() {
        return this.icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
   
    public Subforo getIdSubforo() {
        return this.idSubforo;
    }

    public void setIdSubforo(Subforo idSubforo) {
        this.idSubforo = idSubforo;
    }
   
    public Collection<Post> getPostCollection() {
        return this.postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }
   
    public Date getFhcreacion() {
        return this.fhcreacion;
    }

    public void setFhcreacion(Date fhcreacion) {
        this.fhcreacion = fhcreacion;
    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public Integer getIdHilo() {
        return this.idHilo;
    }

    public void setIdHilo(Integer idHilo) {
        this.idHilo = idHilo;
    }
   
    public String getVisibilidad() {
        return this.visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }
}
