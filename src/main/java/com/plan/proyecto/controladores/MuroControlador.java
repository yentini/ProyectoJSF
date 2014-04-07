/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.controladores;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.beans.Mensaje;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrador
 */
//@Controller
public class MuroControlador {

//    @Autowired
//    GestionCuentas gc;

//    @ModelAttribute("mensaje")
//    public Mensaje getMensaje() {
//        return new Mensaje("dddddddddddddd");
//    }
//
////    @RequestMapping(value = "/muro.html", method = RequestMethod.GET)
////    public void tratarGet(@RequestParam("id") Long id, Model model) {
////        
////        Cuenta cuentaRecuperada = gc.devolverCuenta(id);
////        model.addAttribute("cuenta", cuentaRecuperada);
////    }
//    @RequestMapping(value = "/muro.html", method = RequestMethod.GET)
//    public void tratarGet() {
//    }
}
