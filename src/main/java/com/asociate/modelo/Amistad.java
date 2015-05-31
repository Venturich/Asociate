package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
import java.lang.Long;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="amistad")
public class Amistad implements Serializable {

    @Column(name="ID_AMIGO",table="amistad",nullable=false)
    @Basic
    private long idAmigo;
    
    @Column(name="ID_ORIGEN",table="amistad",nullable=false)
    @Basic
    private long idOrigen;
    
    @Column(name="BLOQUEADO",table="amistad")
    @Basic
    private String bloqueado;
    
    @Column(name="ID_AMISTAD",table="amistad",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idAmistad;

    public Amistad() {

    }
   
    public long getIdAmigo() {
        return this.idAmigo;
    }

    public void setIdAmigo(long idAmigo) {
        this.idAmigo = idAmigo;
    }
   
    public long getIdOrigen() {
        return this.idOrigen;
    }

    public void setIdOrigen(long idOrigen) {
        this.idOrigen = idOrigen;
    }
   
    public String getBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }
   
    public Long getIdAmistad() {
        return this.idAmistad;
    }

    public void setIdAmistad(Long idAmistad) {
        this.idAmistad = idAmistad;
    }
}
