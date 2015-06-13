package com.asociate.modelo;


import java.io.Serializable;
import java.lang.String;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Ventura
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="evento_asistentes")
public class EventoAsistentes implements Serializable {

    @Column(name="ESTADO",table="evento_asistentes")
    @Basic
    private String estado;
    
    @ManyToOne(optional=false,targetEntity = Evento.class)
    @JoinColumn(name="ID_EVENTO",referencedColumnName="ID_EVENTO",insertable=false,updatable=false)
    private Evento evento;
    
    @Column(name="NOTIFICAR",table="evento_asistentes")
    @Basic
    private String notificar;
    
    @ManyToOne(optional=false,targetEntity = Persona.class)
    @JoinColumn(name="ID_PERSONA",referencedColumnName="ID_PERSONA",insertable=false,updatable=false)
    private Persona persona;
    
    @Column(name="ID_EVENTO",table="evento_asistentes",nullable=false)
    @Id
    private long idEvento;
    
    @Column(name="ID_PERSONA",table="evento_asistentes",nullable=false)
    @Id
    private long idPersona;

    /**
     *
     */
    public EventoAsistentes() {

    }
   
    /**
     *
     * @return
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    /**
     *
     * @return
     */
    public Evento getEvento() {
        return this.evento;
    }

    /**
     *
     * @param evento
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
   
    /**
     *
     * @return
     */
    public String getNotificar() {
        return this.notificar;
    }

    /**
     *
     * @param notificar
     */
    public void setNotificar(String notificar) {
        this.notificar = notificar;
    }
   
    /**
     *
     * @return
     */
    public Persona getPersona() {
        return this.persona;
    }

    /**
     *
     * @param persona
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
   
    /**
     *
     * @return
     */
    public long getIdEvento() {
        return this.idEvento;
    }

    /**
     *
     * @param idEvento
     */
    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }
   
    /**
     *
     * @return
     */
    public long getIdPersona() {
        return this.idPersona;
    }

    /**
     *
     * @param idPersona
     */
    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }
}
