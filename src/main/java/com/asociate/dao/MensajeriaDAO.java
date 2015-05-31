/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Mensajeria;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class MensajeriaDAO {
    
    

    public List<Mensajeria> getMensajesPendientes(Long idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Mensajeria> lista = new ArrayList();
        try {
            Query qu = sesion.createQuery("Select M from Mensajeria M where M.idDestino=:id and M.leido='N' order by M.fhenvio desc")
                    .setParameter("id", idUsuario);
            qu.executeUpdate();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return lista;
    }

    public List<Mensajeria> getHistorico(Long idUsuario) {
       Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Mensajeria> lista = new ArrayList();
        try {
            Query qu = sesion.createQuery("Select M from Mensajeria M where (M.idDestino=:id or M.idOrigen=:id) and M.leido='S' order by M.fhenvio desc")
                    .setParameter("id", idUsuario);
            qu.executeUpdate();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return lista;
    }

    public boolean guardar(Mensajeria mensajeNuevo) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
        long id=0L;
        try {
            id = (long) sesion.save(mensajeNuevo);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }    
        return id>0L;
    }

}
