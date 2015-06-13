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

    /**
     *
     * @param idUsuario
     * @return
     */
    public List<Mensajeria> getMensajesPendientes(Long idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Mensajeria> lista = new ArrayList();
        try {
            Query qu = sesion.createQuery("Select M from Mensajeria M join fetch M.idOrigen  where M.idDestino.idUsuario=:id and M.leido='N' order by M.fhenvio desc")
                    .setParameter("id", idUsuario);
            lista = qu.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return lista;
    }

    /**
     *
     * @param idUsuario
     * @return
     */
    public List<Mensajeria> getHistorico(Long idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Mensajeria> lista = new ArrayList();
        try {
            Query qu = sesion.createQuery("Select M from Mensajeria M join fetch M.idDestino join fetch M.idOrigen where (M.idDestino.idUsuario=:id or M.idOrigen.idUsuario=:id) and M.leido=1 order by M.fhenvio desc")
                    .setParameter("id", idUsuario);
            lista = qu.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return lista;
    }

    /**
     *
     * @param mensajeNuevo
     * @return
     */
    public boolean guardar(Mensajeria mensajeNuevo) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        long id = 0L;
        try {
            id = (long) sesion.save(mensajeNuevo);

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return id > 0L;
    }

    public void leido(Long idMensaje) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            sesion.createQuery("Update Mensajeria M set M.leido=1 where M.idMensaje=:id").setParameter("id", idMensaje).executeUpdate();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

}
