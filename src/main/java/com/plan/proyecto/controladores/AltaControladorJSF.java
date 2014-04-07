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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author ccabrerizo
 */
//@ManagedBean(name = "altaControladorJSF")
//@SessionScoped
@Component
@Scope("session")
public class AltaControladorJSF implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ManagedProperty(value = "#{cuenta}")
    private Cuenta cuenta = new Cuenta();

    private Long id;

//    @ManagedProperty(value = "#{gestionCuentas}")
    @Autowired
    private GestionCuentas gestionCuentas;

    @Autowired
    private GestionContenidos gestionContenidos;

    @Autowired
    private GestionRelaciones gestionRelaciones;

    @Autowired
    GestionLogin gl;

    public String alta() {

        if (gestionCuentas.existeCuenta(cuenta)) {
            FacesMessage msg = new FacesMessage("El usuario ya existe", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "alta";
        } else {
            Cuenta retornoCuenta = gestionCuentas.AltaCuenta(cuenta);

            if (retornoCuenta != null) {
                FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("cuenta", retornoCuenta);
                return "muro";
            } else {
                FacesMessage msg = new FacesMessage("El usuario no se ha dado de alta correctamente", "ERROR MSG");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "alta";
            }
        }
    }

    public String login() {

        Cuenta retornoCuenta = gl.autenticarse(cuenta);

        if (retornoCuenta != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("cuenta", retornoCuenta);
            return "muro";
        } else {
            FacesMessage msg = new FacesMessage("El usuario no existe", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "alta";
        }
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public GestionCuentas getGestionCuentas() {
        return gestionCuentas;
    }

    public void setGestionCuentas(GestionCuentas gestionCuentas) {
        this.gestionCuentas = gestionCuentas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GestionContenidos getGestionContenidos() {
        return gestionContenidos;
    }

    public void setGestionContenidos(GestionContenidos gestionContenidos) {
        this.gestionContenidos = gestionContenidos;
    }

    public GestionRelaciones getGestionRelaciones() {
        return gestionRelaciones;
    }

    public void setGestionRelaciones(GestionRelaciones gestionRelaciones) {
        this.gestionRelaciones = gestionRelaciones;
    }

}
