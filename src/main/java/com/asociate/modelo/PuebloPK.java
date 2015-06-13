/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author Ventura
 */
@Embeddable
public class PuebloPK implements Serializable{
    @Column(name="CODIGO",table="pueblo",nullable=false)
    @Basic
    private int codigo;
    @Column(name="CODIGOPROV",table="pueblo",nullable=false)
    @Basic
    private short codigoprov;
    
    /**
     *
     * @return
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
   
    /**
     *
     * @return
     */
    public short getCodigoprov() {
        return this.codigoprov;
    }

    /**
     *
     * @param codigoprov
     */
    public void setCodigoprov(short codigoprov) {
        this.codigoprov = codigoprov;
    }
    
}
