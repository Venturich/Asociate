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
public class AsociacionDAO {

    public boolean registrar(Asociacion regAsoc) {
        Boolean error = false;
        Transaction transaccion = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.save(regAsoc);
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

    public Asociacion getAsociacionById(Long id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Asociacion salida=null;
        try {
            
            salida = (Asociacion) sesion.get(Asociacion.class, id);
            
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
