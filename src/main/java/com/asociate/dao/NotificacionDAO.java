/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Notificacion;
import com.asociate.modelo.Usuario;
import com.asociate.utils.Notificaciones;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class NotificacionDAO {

    /**
     *
     * @param idOrigen
     * @param tipo
     * @param idDestino
     * @param elementoGenerador
     * @param guardar
     * @return
     */
    public Notificacion generarNotificacion(Usuario idOrigen, Notificaciones tipo, Usuario idDestino, Long elementoGenerador, boolean guardar) {
        Notificacion notificacion = new Notificacion();

        if (idDestino == null) {

        }
        if (elementoGenerador == null) {
        }

        notificacion.setIdUsuario(idDestino);
        notificacion.setIdUsuarioOrigen(idOrigen);
        notificacion.setFecha(new Date());
        notificacion.setIdElementoGenerador(elementoGenerador);
        notificacion.setTipoGenerador(tipo.getValor());
        notificacion.setTipo(tipo.getValor());
        switch (tipo.getValor()) {
            case "A":
                notificacion.setTexto("Se ha registrado una nueva Asociacion ");
                break;
            case "U":
                notificacion.setTexto("Se ha registrao un nuevo usuario perteneciente a tu asociacion ");
                break;
            case "E":
                notificacion.setTexto("Se ha creado un nuevo evento ");
                break;
            case "X":
                notificacion.setTexto("Un nuevo comentario en el evento ");
                break;
            case "C":
                notificacion.setTexto("Has recibido un nuevo comentario de ");
                break;
            case "M":
                notificacion.setTexto("Has recibido un nuevo mensaje de ");
                break;
            case "F":
                notificacion.setTexto("Un nuevo fichero ha sido creado en la carpeta de tu asociacion");
                break;
            case "L":
                notificacion.setTexto("Tienes una peticion de amistad");
                break;

        }
        notificacion.setVisto(Boolean.FALSE);
        if (guardar) {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            try {

                sesion.save(notificacion);

            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                sesion.flush();
                sesion.close();
            }

            return null;
        } else {
            return notificacion;

        }

    }

    /**
     *
     * @param idUsuario
     * @return
     */
    public List<Notificacion> getListaNotificacionPendientes(Long idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Notificacion> lista = new ArrayList<Notificacion>();
        try {
            Query qu = sesion.createQuery("Select N from Notificacion N where N.idUsuario.idUsuario=:id and N.visto=0");
            qu.setParameter("id", idUsuario);
            
            lista=qu.list();
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
     * @param idNotificacion
     */
    public void marcarComoVista(Long idNotificacion) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            Query qu = sesion.createQuery("Update Notificacion N set N.visto=1 where N.idNotificacion=:id")
                    .setParameter("id", idNotificacion);
            qu.executeUpdate();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

}
