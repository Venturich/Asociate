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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="mensajeria")
public class Mensajeria implements Serializable {

    @Column(name="ID_MENSAJE",table="mensajeria",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idMensaje;
    @Column(name="TEXTO",table="mensajeria",length=65535)
    @Lob
    @Basic
    private String texto;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name="ID_ORIGEN",referencedColumnName="ID_USUARIO")
    private Usuario idOrigen;
    @Column(name="FHENVIO",table="mensajeria")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fhenvio;
    @Column(name="LEIDO",table="mensajeria")
    @Basic
    private String leido;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name="ID_DESTINO",referencedColumnName="ID_USUARIO")
    private Usuario idDestino;

    public Mensajeria() {

    }
   
    public Long getIdMensaje() {
        return this.idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }
   
    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
   
    public Usuario getIdOrigen() {
        return this.idOrigen;
    }

    public void setIdOrigen(Usuario idOrigen) {
        this.idOrigen = idOrigen;
    }
   
    public Date getFhenvio() {
        return this.fhenvio;
    }

    public void setFhenvio(Date fhenvio) {
        this.fhenvio = fhenvio;
    }
   
    public String getLeido() {
        return this.leido;
    }

    public void setLeido(String leido) {
        this.leido = leido;
    }
   
    public Usuario getIdDestino() {
        return this.idDestino;
    }

    public void setIdDestino(Usuario idDestino) {
        this.idDestino = idDestino;
    }
}
