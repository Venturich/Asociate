/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import com.asociate.dao.UsuarioDAO;
import com.asociate.modelo.Amistad;
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

    private String goToPerfil = "perfilPrincipal";
    private String goToVerPerfil = "perfil";
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

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        if (flash.get("amigos") == null) {
            busqueda = perfilMenu.getBusqueda();
            if (!"".equals(busqueda)) {
                resultado = buscar(busqueda);
            }
        } else {
            List<Amistad> amigos=(List<Amistad>) flash.get("amigos");
            usuDAO=new UsuarioDAO();
            resultado= usuDAO.getAmigosDeLista(amigos);
        }

    }

    /**
     *
     */
    public void buscar() {
        if (!busqueda.equals("")) {
            this.resultado = buscar(busqueda);
        }
    }

    private List<Usuario> buscar(String busqueda) {
        usuDAO = new UsuarioDAO();
        return usuDAO.buscarUsuarios(busqueda);
    }

    /**
     *
     * @param id
     * @param asociacion
     * @return
     */
    public String irAPerfil(Long id, boolean asociacion) {
        flash.put("asociacion", asociacion);
        if (asociacion) {
            flash.put("idAsociacion", id);
        } else {
            flash.put("idPersona", id);
        }
        return goToVerPerfil;
    }

    /**
     *
     * @return
     */
    public List<Usuario> getResultado() {
        return resultado;
    }

    /**
     *
     * @param resultado
     */
    public void setResultado(List<Usuario> resultado) {
        this.resultado = resultado;
    }

    /**
     *
     * @return
     */
    public String getBusqueda() {
        return busqueda;
    }

    /**
     *
     * @param busqueda
     */
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    /**
     *
     * @return
     */
    public Flash getFlash() {
        return flash;
    }

    /**
     *
     * @param flash
     */
    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    /**
     *
     * @return
     */
    public PerfilMenuManagedBean getPerfilMenu() {
        return perfilMenu;
    }

    /**
     *
     * @param perfilMenu
     */
    public void setPerfilMenu(PerfilMenuManagedBean perfilMenu) {
        this.perfilMenu = perfilMenu;
    }

}
