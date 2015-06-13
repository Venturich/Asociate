/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Directorio;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class DirectorioDAO {

    /**
     *
     * @param idAsociacion
     * @return
     */
    public boolean hasDirectorioAsociacion(Long idAsociacion) {
          Session sesion = HibernateUtil.getSessionFactory().openSession();
          Directorio dir=null;
        try {
            Query qu = sesion.createQuery("Select D from Directorio D where D.idDirectorio = (Select A.idDirectorio from Asociacion A where A.idAsociacion=:idAsoc)");
                qu.setParameter("idAsoc", idAsociacion);
            qu.setMaxResults(1).getFirstResult();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return dir!=null;
        
    }

    /**
     *
     * @param idPersona
     * @return
     */
    public boolean hasDirectorioPersona(Long idPersona) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
          Directorio dir=null;
        try {
            Query qu = sesion.createQuery("Select D from Directorio D where D.idDirectorio = (Select A.idDirectorio from Asociacion A where A.idAsociacion in( Select SO from Socio SO where SO.idPersona = :idPer))");
                qu.setParameter("idPer", idPersona);
            qu.setMaxResults(1).getFirstResult();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return dir!=null;
    }

    /**
     *
     * @param dir
     */
    public void guardar(Directorio dir) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        
        try {
            sesion.save(dir);
            

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
    }
    
}
