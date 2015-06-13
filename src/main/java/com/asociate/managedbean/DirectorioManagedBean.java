/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import java.io.File;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Ventura
 */
@ManagedBean(name = "directorioMB")
@ViewScoped
public class DirectorioManagedBean  implements Serializable  {
    private Flash flash;
    private TreeNode root;
    private DefaultTreeNode nx;
    private String directorio;

    /**
     * Creates a new instance of DirectorioManagedBean
     */
    public DirectorioManagedBean() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        directorio = "";
        root = new DefaultTreeNode("Home", null);

        File file = new File(directorio);

        if (!file.exists()) {
            
            return;

        }

        tree(directorio, root);

    }

    /**
     *
     * @return
     */
    public TreeNode getRoot() {

        return root;

    }

    /**
     *
     * @param filename
     * @param op
     */
    public void tree(String filename, TreeNode op) {

        File file = new File(filename);

        if (!file.isDirectory()) {

            new DefaultTreeNode(file.getName(), op);

            return;

        } else {

            op = new DefaultTreeNode(file.getName(), op);

            String files[] = file.list();

            for (int i = 0; i < files.length; i++) {

                tree(filename + File.separator + files[i], op);

            }

        }

    }

}
