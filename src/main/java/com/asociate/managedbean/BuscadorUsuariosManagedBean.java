/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "buscadorUsuariosMB")
@ViewScoped
public class BuscadorUsuariosManagedBean implements Serializable {

    private String goToPerfil = "";
    private Flash flash;

    @ManagedProperty(value = "#{perfilMenuMB}")
    private PerfilMenuManagedBean perfilMenu;

    private List<Usuario> resultado;
    private UsuarioDAO usuDAO;
    private String busqueda;

    /**
     * Creates a new instance of BuscadorUsuariosManagedBean
     */
    public BuscadorUsuariosManagedBean() {
    }

    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        busqueda = perfilMenu.getBusqueda();
        if (!"".equals(busqueda)) {
            resultado = buscar(busqueda);
        }

    }
    
    public void buscar(){
        if(!busqueda.equals("")){
            this.resultado=buscar(busqueda);
        }
    }

    private List<Usuario> buscar(String busqueda) {
        usuDAO = new UsuarioDAO();
        return usuDAO.buscarUsuarios(busqueda);
    }

    public String irAPerfil(Long id, boolean asociacion) {
        flash.put("asociacion", asociacion);
        if (asociacion) {
            flash.put("idAsociacion", id);
        } else {
            flash.put("idPersona", id);
        }
        return goToPerfil;
    }

    public List<Usuario> getResultado() {
        return resultado;
    }

    public void setResultado(List<Usuario> resultado) {
        this.resultado = resultado;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

}
