/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionRelaciones;

import com.plan.proyecto.beans.Cuenta;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface GestionRelaciones {
   
    List<Cuenta> amigosPotenciales(Cuenta origen);
    
    List<Cuenta> hacerAmigos(Cuenta origen, Cuenta... amigos);
    
    List<Cuenta> quitarAmigos(Cuenta origen, Cuenta... amigos);

    List<Cuenta> mostrarAmigos(Cuenta cuenta);
    
    List<Cuenta> deQuienSoyAmigo(Cuenta cuenta);

    Boolean sonAmigos(Cuenta origen, Cuenta destino);
}
