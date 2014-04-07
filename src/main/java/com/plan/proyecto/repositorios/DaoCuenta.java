/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.repositorios;

import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.Cuenta;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface DaoCuenta extends DaoGenerico<Cuenta, Long> {

    List<Cuenta> findByNombre(String nombre);

    List<Cuenta> findAll();

    Cuenta findByEmailAndPassword(String email, String pwd);

    Cuenta findByEmail(String email);

    void limpiezaCuentas();
    
    Boolean sonAmigos (Cuenta origen, Cuenta amigo);
    
    List<Cuenta> findAmigosByCuenta(Cuenta cuenta);
    
    List<Cuenta> findAmigosPotencialesByCuenta(Cuenta cuenta);
    
    List<Cuenta> findDeQuienSoyAmigoByCuenta(Cuenta cuenta);
        
}
