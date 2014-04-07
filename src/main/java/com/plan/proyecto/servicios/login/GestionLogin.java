/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.login;

import com.plan.proyecto.beans.Cuenta;

/**
 *
 * @author Administrador
 */
public interface GestionLogin {

    Cuenta autenticarse(String email, String pwd);
    
    Cuenta autenticarse(Cuenta cuenta);
}
