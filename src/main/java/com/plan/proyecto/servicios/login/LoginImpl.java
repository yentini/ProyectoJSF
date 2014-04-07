/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.login;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.repositorios.DaoCuenta;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentas;
import com.plan.proyecto.servicios.gestionRelaciones.GestionRelaciones;
import com.plan.proyecto.servicios.utilidades.Encriptar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */
@Service
public class LoginImpl implements GestionLogin {

    @Autowired
    DaoCuenta dao;

    @Autowired
    GestionRelaciones gestionRelaciones;

    @Autowired
    GestionCuentas gestionCuentas;

    @Override
    public Cuenta autenticarse(String email, String pwd) {

        List<Cuenta> cuentasSistema = gestionCuentas.mostrarCuentasSistema();

        for (Cuenta cuenta : cuentasSistema) {
            if (cuenta.getEmail().equals(email)
                    && Encriptar.decrypt(cuenta.getPassword()).equals(pwd)) {
                return cuenta;
            }
        }
        return null;
//        return dao.findByEmailAndPassword(email, encriptar.encrypt(pwd)) != null;
    }

    @Override
    public Cuenta autenticarse(Cuenta cuenta) {
        return autenticarse(cuenta.getEmail(), cuenta.getPassword());
    }

}
