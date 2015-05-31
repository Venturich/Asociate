/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.dao;

import com.asociate.modelo.Provincia;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Objeto DAO para la tabla Provincia
 * @author Ventura
 */
public class ProvinciaDAO {

    /**
     * Obtiene una colección con todas las provincias
     * @return
     */
    public List<Provincia> getTodas() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Provincia> lista=new ArrayList();
        try {
            Query qu = sesion.createQuery("Select p from Provincia p");
            lista = qu.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }
        return lista;

    }
}