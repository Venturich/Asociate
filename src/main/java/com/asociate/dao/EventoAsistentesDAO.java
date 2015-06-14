/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.EventoAsistentes;
import com.asociate.modelo.Persona;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ventura
 */
public class EventoAsistentesDAO {

    /**
     *
     * @param idEvento
     * @return
     */
    public List<Persona> getListaEvento(Long idEvento) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Persona> salida = new ArrayList();
        try {
            Query qu = sesion.createSQLQuery("Select P.* from PERSONA P join EVENTO_ASISTENTES E on E.ID_PERSONA = P.ID_PERSONA WHERE E.ID_EVENTO = :idE AND E.ESTADO = 'A' ");
            qu.setParameter("idE", idEvento);
            salida = qu.list();

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
     * @param lista
     */
    public void guardarAsistente(List<EventoAsistentes> lista) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            
            for (int i = 0; i < lista.size(); i++) {
                sesion.save(lista.get(i));
            }
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

}
