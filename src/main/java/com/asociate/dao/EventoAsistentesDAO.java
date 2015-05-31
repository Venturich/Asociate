/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Persona;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class EventoAsistentesDAO {

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
    
}
