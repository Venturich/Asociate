/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Amistad;
import com.asociate.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class UsuarioDAO {

    private Log logger = LogFactory.getLog(this.getClass().getName());

    /**
     * Comprueba que un email no esté en la base de datos
     *
     * @param email
     * @return true si existe el email
     */
    public boolean comprobarEmail(String email) {
        logger.info("comprobar");
        boolean existe = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Query qu = sesion.createQuery("Select U.login from Usuario U where U.login=:email").setString("email", email);
        Object salida = qu.uniqueResult();

        if (salida != null) {
            existe = true;
        }
        sesion.flush();
        sesion.close();
        logger.info("fin comprobar " + existe);
        return existe;
    }

    /**
     * Comprueba que el login es correcto
     *
     * @param usuario
     * @return usuario logeado
     */
    public Usuario comprobarLogin(Usuario usuario) {
        Usuario user = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {

            Query qu = sesion.createQuery("Select u from Usuario u where u.login=:email AND u.password=:clave")
                    .setString("email", usuario.getLogin())
                    .setString("clave", usuario.getPassword());//OJO
            Object salida = qu.uniqueResult();
            if (salida != null) {
                user = (Usuario) salida;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sesion.flush();
        sesion.close();
        return user;

    }

    /**
     * Bloquea/desbloquea usuario de la base de datos
     *
     * @param codigo
     * @param bloqueado
     */
    public void bloquear(Long codigo, String bloqueado) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            Query qu = sesion.createQuery("Update Usuario U set U.bloqueado=:bloq where U.idUsuario=:cod")
                    .setParameter("bloq", bloqueado).setParameter("cod", codigo);
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
     * @param idUsuario
     */
    public void setRegistroCompleto(Long idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            Query qu = sesion.createQuery("Update Usuario U set U.confirmado='S' where U.idUsuario=:id")
                    .setParameter("id", idUsuario);
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
     * @param user
     */
    public void actualizar(Usuario user) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.update(user);

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

    /**
     *
     * @param query
     * @param idUsuario
     * @return
     */
    public List<Usuario> buscarContacto(String query, Long idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> salida = new ArrayList<>();
        try {
            Query qu = sesion.createSQLQuery("Select U.* from usuario U "
                    + "join amistad A on A.id_amigo=U.id_usuario"
                    + " where A.id_origen = :idUsuario  and "
                    + "A.id_amigo in("
                    + "(Select P.id_usuario from persona P where lower(nombre) like lower('%" + query + "%') "
                    + " or lower(apellidop) like lower('%" + query + "%')) union "
                    + "(Select S.id_usuario from asociacion S where lower(razonsocial) like lower('%" + query + "%') )"
                    + ")");
            qu.setParameter("idUsuario", idUsuario);
            salida = qu.list();

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
     * @param busqueda
     * @return
     */
    public List<Usuario> buscarUsuarios(String busqueda) {
        List<Usuario> salida = new ArrayList();
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            Query qu = sesion.createQuery("Select U from Usuario U join fetch U.persona P"
                    + " where ("
                    //+ " lower(A.razonsocial) like lower('%"+busqueda+"%') or "
                    + " lower(P.nombre) like lower('%" + busqueda + "%') or "
                    + " lower(P.apellidop) like lower('%" + busqueda + "%') or "
                    + " lower(P.alias) like lower('%" + busqueda + "%') or "
                    + " lower(P.apellidom) like lower('%" + busqueda + "%')) and"
                    + " U.bloqueado = 'N' and U.confirmado = 'S' ");

            salida = qu.list();

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
     * @return
     */
    public List<Usuario> getTodos() {
        List<Usuario> salida = new ArrayList();
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            Query qu = sesion.createQuery("Select U from Usuario U");
            salida = qu.list();

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
     * @param idUsuario
     */
    public void guardar(Usuario idUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.save(idUsuario);

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public static Usuario getPorID(Long id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Usuario salida = null;
        try {
            salida = (Usuario) sesion.get(Usuario.class, id);

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
     * @param amigos
     * @return
     */
    public List<Usuario> getAmigosDeLista(List<Amistad> amigos) {
        List<Usuario> salida = new ArrayList();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        StringBuffer sb = new StringBuffer();
        for (Amistad am : amigos) {
            sb.append(" ");
            sb.append(am.getIdAmigo());
            sb.append(" ,");
        }
        sb.delete(sb.lastIndexOf(",")-1, sb.length());
        try {
            Query qu = sesion.createQuery("Select U from Usuario U where U.persona.idPersona in(:list)");
            qu.setParameter("list", sb.toString());
            salida = qu.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return salida;
    }
}
