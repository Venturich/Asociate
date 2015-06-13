/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Asociacion;
import com.asociate.modelo.Persona;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ventura
 */
public class PersonaDAO {

    /**
     *
     * @param regPersona
     * @return
     */
    public boolean registrar(Persona regPersona) {

        Boolean error = false;
        Transaction transaccion = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.save(regPersona);
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
     * @param id
     * @return
     */
    public Persona getPersonaById(Long id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Persona salida=null;
        try {
            
            salida = (Persona) sesion.get(Persona.class, id);
            
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
