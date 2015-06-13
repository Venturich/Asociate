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
    public Boolean guardarNuevoEvento(Evento nuevo) {
         Boolean error = false;
        Transaction transaccion = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.save(nuevo);
            transaccion.commit();
        } catch (JDBCException c) {
            c.printStackTrace();
            error = true;
            if (transaccion
                    != null) {
                transaccion.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
            error = true;
            if (transaccion
                    != null) {
                transaccion.rollback();
            }
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
            Query qu = sesion.createQuery("Select E from Evento E where E.eventoAsistentesCollection.persona.idPersona = :id order by E.fhinicio desc");
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
            Query qu = sesion.createQuery("Select E from Evento E where E.eventoAsistentesCollection.idCreador.idUsuario = :id order by E.fhinicio asc");
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
