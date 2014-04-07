/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionCuentas;

import com.plan.proyecto.beans.Cuenta;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface GestionCuentas {

    Cuenta AltaCuenta(Cuenta cuenta);

    Cuenta ModificarCuenta(Cuenta cuenta);

    Boolean BajaCuenta(Cuenta cuenta);

    List<Cuenta> mostrarCuentasSistema();

    Boolean existeCuenta(Cuenta cuenta);

    Cuenta devolverCuenta(Long id);

}
