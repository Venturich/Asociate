package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Ventura
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="comentario")
public class Comentario implements Serializable {

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name="ID_AUTOR",referencedColumnName="ID_USUARIO")
    private Usuario idAutor;
    @Column(name="FHPUBLICACION",table="comentario")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhpublicacion;
    @Column(name="BLOQUEADO",table="comentario")
    @Basic
    private String bloqueado;
    @Column(name="COMENTARIO",table="comentario",length=140)
    @Basic
    private String comentario;
    @Column(name="ID_COMENTARIO",table="comentario",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idComentario;

    /**
     *
     */
    public Comentario() {

    }
   
    /**
     *
     * @return
     */
    public Usuario getIdAutor() {
        return this.idAutor;
    }

    /**
     *
     * @param idAutor
     */
    public void setIdAutor(Usuario idAutor) {
        this.idAutor = idAutor;
    }
   
    /**
     *
     * @return
     */
    public Date getFhpublicacion() {
        return this.fhpublicacion;
    }

    /**
     *
     * @param fhpublicacion
     */
    public void setFhpublicacion(Date fhpublicacion) {
        this.fhpublicacion = fhpublicacion;
    }
   
    /**
     *
     * @return
     */
    public String getBloqueado() {
        return this.bloqueado;
    }

    /**
     *
     * @param bloqueado
     */
    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }
   
    /**
     *
     * @return
     */
    public String getComentario() {
        return this.comentario;
    }

    /**
     *
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
   
    /**
     *
     * @return
     */
    public Long getIdComentario() {
        return this.idComentario;
    }

    /**
     *
     * @param idComentario
     */
    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }
}
