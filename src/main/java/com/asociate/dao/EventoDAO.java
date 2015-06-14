/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Evento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ventura
 */
public class EventoDAO {

    /**
     *
     * @param nuevo
     * @return
     */
    public Long guardarNuevoEvento(Evento nuevo) {
        Long error = 0L;
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
        
            error =(Long) sesion.save(nuevo);
        
        } catch (JDBCException c) {
            c.printStackTrace();
            error = -1L;
        

        } catch (Exception e) {
            e.printStackTrace();
            error = -1L;
        
        } finally {
            sesion.flush();
            sesion.close();

        }
        return error;
    }

    /**
     *
     * @param idEvento
     * @return
     */
    public Evento getEvento(Long idEvento) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Evento salida=null;
        try {
            
            salida = (Evento) sesion.get(Evento.class, idEvento);
            
        } catch (JDBCException c) {
            c.printStackTrace();
            

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            sesion.flush();
            sesion.close();

        }
        
        return salida;
    }

    /**
     *
     * @param idPersona
     * @return
     */
    public List<Evento> getListaEvento(Long idPersona) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Evento> salida=new ArrayList();
        try {
            Query qu = sesion.createQuery("Select E from Evento E join fetch E.eventoAsistentesCollection EA where EA.idPersona = :id order by E.fhinicio desc");
            qu.setParameter("id", idPersona);
            salida= qu.list();
        } catch (JDBCException c) {
            c.printStackTrace();
            

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            sesion.flush();
            sesion.close();

        }
        
        return salida;
    }

    /**
     *
     * @param idUsuario
     * @return
     */
    public List<Evento> getListaEventoPropio(Long idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Evento> salida=new ArrayList();
        try {
            Query qu = sesion.createQuery("Select E from Evento E where E.idCreador.idUsuario = :id order by E.fhinicio asc");
            qu.setParameter("id", idUsuario);
            salida= qu.list();
        } catch (JDBCException c) {
            c.printStackTrace();
            

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            sesion.flush();
            sesion.close();

        }
        
        return salida;
    }
    
}
