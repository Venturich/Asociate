/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Pueblo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ventura
 */
public class PuebloDAO {
    /**
     * Obtiene una colección con todos los pueblos de una provincia
     * @param codigoProvincia
     * @return
     */
    public List<Pueblo> getListaPueblos(int codigoProvincia) {
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Pueblo> lista = null;
        try {
            Query qu = sesion.createQuery("Select p from Pueblo p where codigoProv = :codigo").setInteger("codigo", codigoProvincia);
            lista = qu.list();

        } catch (RuntimeException e) {

        } finally {
            sesion.flush();
            sesion.close();
        }
        return lista;
    }
}
