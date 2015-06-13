/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Ventura
 */
@ManagedBean
public class AsociateError implements Serializable {

    private Log logger = LogFactory.getLog(this.getClass().getName());
    
    /**
     *
     * @return
     */
    protected FacesContext getCurrentContext() {

        return FacesContext.getCurrentInstance();

    }

    /**
     *
     * @param msg
     */
    public void addInfo(String msg) {

        addMessage(msg, FacesMessage.SEVERITY_INFO);

    }

    /**
     *
     * @param sumario
     * @param detalle
     */
    public void addInfo(String sumario, String detalle) {

        FacesMessage message = new FacesMessage(sumario);

        message.setSeverity(FacesMessage.SEVERITY_INFO);

        message.setDetail(detalle);

        FacesContext ctx = getCurrentContext();

        ctx.addMessage(null, message);

    }

    /**
     *
     * @param msg
     */
    public void addError(String msg) {

        addMessage(msg, FacesMessage.SEVERITY_ERROR);

    }

    /**
     *
     * @param sumario
     * @param detalle
     */
    public void addError(String sumario, String detalle) {

        FacesMessage message = new FacesMessage(sumario);

        message.setSeverity(FacesMessage.SEVERITY_ERROR);

        message.setDetail(detalle);

        FacesContext ctx = getCurrentContext();

        ctx.addMessage(null, message);

    }

    private void addMessage(String msg, Severity severity) {

        FacesMessage message = new FacesMessage(msg);

        message.setSeverity(severity);

        FacesContext ctx = getCurrentContext();

        ctx.addMessage(null, message);

    }

    /**
     *
     * @return
     */
    public String putStackTrace() {

        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, Object> map = context.getExternalContext().getRequestMap();

        Throwable throwable = (Throwable) map.get("javax.servlet.error.exception");

        StringBuilder builder = new StringBuilder();

        if (throwable != null && throwable.getMessage() != null && !throwable.getMessage().isEmpty()) {

            builder.append(throwable.getMessage()).append("\n");

            for (StackTraceElement element : throwable.getStackTrace()) {

                builder.append(element).append("\n");

            }

            logger.debug("Ocurrio un error: " + builder.toString());
            return "Ha ocurrido un error...";

        }

        return "";

    }
}
