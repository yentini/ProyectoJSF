/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionContenidos;

import com.plan.proyecto.beans.Comentario;
import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.beans.Mensaje;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface GestionContenidos {

    Contenido publicarContenido(Cuenta cuenta, Contenido mensaje, Contenido comentario);
    
    Contenido eliminarContenido(Contenido contenido);

    List<Mensaje> mostrarMensajes(Cuenta cuenta);
    
    List<Comentario> mostrarComentarios(Cuenta cuenta);
    
    List<Contenido> mostrarComentarios(Contenido mensaje);
    
    Contenido devolverContenido(Long id);
    
}
