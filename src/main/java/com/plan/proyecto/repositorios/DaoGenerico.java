/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plan.proyecto.repositorios;

/**
 *
 * @author Administrador
 */
public interface DaoGenerico<T, Long> {
    T insertar (T t);
    T eliminar (T t);
    void eliminarById (Long id);
    T modificar (T t);
    T findById (Long id);
}
