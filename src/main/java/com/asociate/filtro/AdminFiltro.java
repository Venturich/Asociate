/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.filtro;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filtro que controla que el acceso al area de administración sea realizado solo por administradores resgitrados
 * @author Ventura
 */
public class AdminFiltro implements Filter {
    
    private FilterConfig filterConfig = null;
    
    public AdminFiltro() {
    }

    /**
     * Filtrado de la petición y la solicitud según la URI
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            String reqURI = req.getRequestURI();
            if (reqURI.contains("/Administracion") && (ses == null || (ses.getAttribute("adminIsLogged")== null || !ses.getAttribute("adminIsLogged").equals("SI")))) {
                
                res.sendRedirect(req.getContextPath() + "/loginAdmin.xhtml");
            } else {
                chain.doFilter(request, response);
                
            }
        } catch (IOException | ServletException t) {
            t.printStackTrace();
        }
}

    /**
     * Metodo destroy 
     */
    @Override
    public void destroy() {
    }

    /**
     * Metodo Init, configuración inicial del filtro
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
}
