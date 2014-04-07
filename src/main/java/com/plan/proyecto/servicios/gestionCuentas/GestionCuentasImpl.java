/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionCuentas;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.repositorios.DaoCuenta;
import com.plan.proyecto.servicios.utilidades.Encriptar;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrador
 */
@Service
@Transactional
//@ManagedBean(eager = true)
//@SessionScoped
public class GestionCuentasImpl implements GestionCuentas, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    DaoCuenta dao;

    @Override
    public List<Cuenta> mostrarCuentasSistema() {
        return dao.findAll();
    }

    @Override
    public Cuenta AltaCuenta(Cuenta cuenta) {

        if (cuenta == null) {
            return null;
        }
        if (!existeCuenta(cuenta)) {
            cuenta.setPassword(Encriptar.encrypt(cuenta.getPassword()));
            return dao.insertar(cuenta);
        }
        return null;
    }

    @Override
    public Cuenta ModificarCuenta(Cuenta cuenta) {

        if (cuenta == null) {
            return null;
        }
        if (existeCuenta(cuenta)) {
            return dao.modificar(cuenta);
        }
        return null;
    }

    @Override
    public Boolean BajaCuenta(Cuenta cuenta) {

        if (cuenta == null) {
            return false;
        }

        if (existeCuenta(cuenta)) {
            Cuenta cuentaOriginal = dao.findById(cuenta.getId());
            dao.eliminar(cuentaOriginal);
            return true;
        }
        return false;
    }

    public Boolean existeCuenta(Cuenta cuenta) {

        return dao.findByEmail(cuenta.getEmail()) != null;
    }

    @Override
    public Cuenta devolverCuenta(Long id) {
        return dao.findById(id);
    }

}
