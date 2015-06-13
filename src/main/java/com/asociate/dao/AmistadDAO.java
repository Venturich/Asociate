/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Amistad;
import com.asociate.modelo.Persona;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class AmistadDAO {

    /**
     *
     * @param idPersona
     * @return
     */
    public List<Amistad> getListaAmigos(Long idPersona) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Amistad> salida = new ArrayList();
        try {
            Query qu = sesion.createQuery("Select A from Amistad A where A.idOrigen=:id and A.bloqueado = 'N'")
                    .setParameter("id", idPersona);
            salida.addAll(qu.list());

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
     * @param idPersona
     * @return
     */
    public Map<Long, Persona> getMapaAmigos(Long idPersona) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Map<Long, Persona> mapa = new HashMap();
        List<Persona> salidaP = new ArrayList();
        try {

            Query qu = sesion.createQuery("Select P from Persona P where P.idPersona in (Select A.idAmigo from Amistad A where A.idOrigen=:id and A.bloqueado = 'N')")
                    .setParameter("id", idPersona);
            salidaP = qu.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        for (Persona pe : salidaP) {
            mapa.put(pe.getIdPersona(), pe);
        }

        return mapa;
    }

    /**
     *
     * @param ami
     */
    public void guardar(Amistad ami) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            sesion.save(ami);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

    /**
     *
     * @param idAmigo
     * @param idOrigen
     */
    public void eliminarPorId(Long idAmigo, Long idOrigen) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Query qu = sesion.createQuery("Update Amistad A set A.bloqueado='S'  where ((A.idAmigo:idA and A.idOrigen=:idO)or(A.idOrigen:idA1 and A.idAmigo=:idO1))");
                    qu.setParameter("idO", idOrigen).setParameter("idA", idAmigo)
                            .setParameter("idA1", idOrigen).setParameter("idO1", idAmigo);
                    qu.executeUpdate();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

    /**
     *
     * @param idAmigo
     * @param idOrigen
     * @return
     */
    public boolean esAmigo(Long idAmigo, Long idOrigen) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        boolean salida=false;
        try {
            Query qu = sesion.createQuery("Select A from Amistad A where A.idOrigen=:id and A.idAmigo=:idA and A.bloqueado = 'N'")
                    .setParameter("id", idOrigen).setParameter("idA", idAmigo);
            salida=!qu.list().isEmpty();

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
     * @param idAmigo
     * @param idOrigen
     * @return
     */
    public boolean eraAmigo(Long idAmigo, Long idOrigen) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        boolean salida=false;
        try {
            Query qu = sesion.createQuery("Select A from Amistad A where ((A.idAmigo:idA and A.idOrigen=:idO)or(A.idOrigen:idA1 and A.idAmigo=:idO1)) and A.bloqueado = 'S'")
                      .setParameter("idO", idOrigen).setParameter("idA", idAmigo)
                            .setParameter("idA1", idOrigen).setParameter("idO1", idAmigo);
            salida=!qu.list().isEmpty();

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
     * @param idAmigo
     * @param idOrigen
     * @param estado
     */
    public void actualizarAmistad(Long idAmigo, Long idOrigen, String estado) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Query qu = sesion.createQuery("Update Amistad A set A.bloqueado=:est  where ((A.idAmigo:idA and A.idOrigen=:idO)or(A.idOrigen:idA1 and A.idAmigo=:idO1))");
                    qu.setParameter("idO", idOrigen).setParameter("idA", idAmigo)
                            .setParameter("idA1", idOrigen).setParameter("idO1", idAmigo).setParameter("est", estado);
                    qu.executeUpdate();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

}
