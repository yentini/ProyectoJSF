/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.repositorios;

import com.plan.proyecto.beans.Comentario;
import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.beans.Mensaje;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface DaoContenido extends DaoGenerico<Contenido, Long> {

    Contenido findMensajeByComentario(Contenido comentario);
    
    List<Comentario> findComentarioByCuenta (Cuenta cuenta);
    
    List<Mensaje> findMensajeByCuenta (Cuenta cuenta);
    
    List<Contenido> findComentariosoByMensaje (Contenido mensaje);
    
    List<Contenido> findContenidosByCuenta(Long id);
    
}
