package com.asociate.modelo;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="post")
public class Post implements Serializable {

    @Column(name="TEXTO",table="post",length=65535)
    @Lob
    @Basic
    private String texto;
    @Column(name="ID_SUBFORO",table="post")
    @Basic
    private Integer idSubforo;
    @Column(name="FHCREACION",table="post")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhcreacion;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name="AUTOR",referencedColumnName="ID_USUARIO")
    private Usuario autor;
    @Column(name="ID_POST",table="post",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idPost;
    @ManyToOne(targetEntity = Hilo.class)
    @JoinColumn(name="ID_HILO",referencedColumnName="ID_HILO")
    private Hilo idHilo;

    public Post() {

    }
   
    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
   
    public Integer getIdSubforo() {
        return this.idSubforo;
    }

    public void setIdSubforo(Integer idSubforo) {
        this.idSubforo = idSubforo;
    }
   
    public Date getFhcreacion() {
        return this.fhcreacion;
    }

    public void setFhcreacion(Date fhcreacion) {
        this.fhcreacion = fhcreacion;
    }
   
    public Usuario getAutor() {
        return this.autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
   
    public Long getIdPost() {
        return this.idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }
   
    public Hilo getIdHilo() {
        return this.idHilo;
    }

    public void setIdHilo(Hilo idHilo) {
        this.idHilo = idHilo;
    }
}
