/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Socio;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class SocioDAO {

    public List<Socio> getListaSocios(Long idAsociacion) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Socio> lista = new ArrayList();
        try {
            Query qu = sesion.createQuery("Select SO from Socio SO where SO.idAsociacion = :idAso");
            qu.setParameter("idAso", idAsociacion);
            lista = qu.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return lista;
    }

    public boolean esSocio(Long idAsociacion, Long idPersona) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        boolean salida=false;
        try {
            Query qu = sesion.createQuery("Select SO from Socio SO where SO.idAsociacion = :idAso and SO.idPersona=:idPer");
            qu.setParameter("idAso", idAsociacion).setParameter("idPer", idPersona);
            salida=!qu.list().isEmpty();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return salida;
    }

    public void guardar(Socio socio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    

}
