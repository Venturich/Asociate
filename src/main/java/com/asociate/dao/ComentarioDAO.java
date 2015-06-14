/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Comentario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class ComentarioDAO {

    /**
     *
     * @param idUsuario
     * @return
     */
    public List<Comentario> getListaComentarioesPorId(Long idUsuario) {
        List<Comentario> salida = new ArrayList();
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            salida = sesion.createQuery("Select C from Comentario C where C.idAutor.idUsuario=:id").setParameter("id", idUsuario).list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return salida;

    }

    /**
     *
     * @param comentario
     */
    public void guardar(Comentario comentario) {

        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.save(comentario);

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }

    }

}
