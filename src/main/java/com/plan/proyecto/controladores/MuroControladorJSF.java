/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.controladores;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.beans.Mensaje;
import com.plan.proyecto.servicios.gestionContenidos.GestionContenidos;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentas;
import com.plan.proyecto.servicios.gestionRelaciones.GestionRelaciones;
import com.plan.proyecto.servicios.login.GestionLogin;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author ccabrerizo
 */
@Component
@Scope("session")
public class MuroControladorJSF implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cuenta cuenta;

    private List<Mensaje> mensajes;

    private List<Cuenta> amigos;

    private List<Cuenta> usuarios;

    private List<Cuenta> muros;

    @Autowired
    GestionCuentas gc;

    @Autowired
    GestionLogin gl;

    @Autowired
    GestionContenidos gestionContenidos;

    @Autowired
    GestionRelaciones gestionRelaciones;

    @PostConstruct
    public void init() {

        Cuenta cuenta = (Cuenta) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("cuenta");

        mensajes = gestionContenidos.mostrarMensajes(cuenta);
        amigos = gestionRelaciones.mostrarAmigos(cuenta);
        usuarios = gestionRelaciones.amigosPotenciales(cuenta);
        muros = gestionRelaciones.deQuienSoyAmigo(cuenta);

        if (mensajes != null) {
            if (mensajes.isEmpty()) {
                FacesMessage msgMensajes = new FacesMessage("No tienes ningún mensaje", "ERROR MSG");
                msgMensajes.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, msgMensajes);
            }
        }
        if (muros != null) {
            if (muros.isEmpty()) {
                FacesMessage msgMuros = new FacesMessage("No puedes visitar ningún muro", "ERROR MSG");
                msgMuros.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, msgMuros);
            }
        }

    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Cuenta> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Cuenta> amigos) {
        this.amigos = amigos;
    }

    public List<Cuenta> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Cuenta> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Cuenta> getMuros() {
        return muros;
    }

    public void setMuros(List<Cuenta> muros) {
        this.muros = muros;
    }
}
