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
import com.plan.proyecto.repositorios.DaoContenido;
import com.plan.proyecto.repositorios.DaoCuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrador
 */
@Service
@Transactional
public class gestionContenidosImpls implements GestionContenidos {

    @Autowired
    DaoCuenta daoCuenta;

    @Autowired
    DaoContenido daoContenido;

    @Override
    public Contenido publicarContenido(Cuenta cuenta, Contenido mensaje, Contenido comentario) {

        if (cuenta.getId() == null) {
            return null;
        }
        if (mensaje == null) {
            return null;
        }

        Cuenta cuentaRecuperada = daoCuenta.findById(cuenta.getId());

        if (comentario == null) {
            cuentaRecuperada.getContenidos().add(mensaje);
            mensaje.setCuenta(cuentaRecuperada);
            return mensaje;
        } else {
            if (mensaje.getId() == null) {
                return null;
            }
            Contenido mensajeRecuperado = daoContenido.findById(mensaje.getId());

            mensajeRecuperado.getComentarios().add(comentario);
            cuentaRecuperada.getContenidos().add(comentario);
            comentario.setCuenta(cuentaRecuperada);
            return comentario;
        }
    }

    @Override
    public Contenido eliminarContenido(Contenido contenido) {

        if (contenido == null) {
            return null;
        }

        if (contenido.getId() == null) {
            return null;
        }

        Contenido mensajeOriginal = daoContenido.findById(contenido.getId());        
        Contenido mensaje = daoContenido.findMensajeByComentario(contenido);
        
        if (mensaje != null) {
            mensaje.getComentarios().remove(contenido);
        } else {
            daoContenido.eliminar(mensajeOriginal);
        }
        return contenido;
    }

    @Override
    public List<Mensaje> mostrarMensajes(Cuenta cuenta) {

        if (cuenta == null) {
            return null;
        }

        if (cuenta.getId() == null) {
            return null;
        }

        return daoContenido.findMensajeByCuenta(cuenta);
    }

    @Override
    public List<Comentario> mostrarComentarios(Cuenta cuenta) {

        if (cuenta == null) {
            return null;
        }

        if (cuenta.getId() == null) {
            return null;
        }

        return daoContenido.findComentarioByCuenta(cuenta);

    }

    @Override
    public List<Contenido> mostrarComentarios(Contenido mensaje) {

        if (mensaje == null) {
            return null;
        }

        if (mensaje.getId() == null) {
            return null;
        }

        return daoContenido.findComentariosoByMensaje(mensaje);
    }

    @Override
    public Contenido devolverContenido(Long id) {
        return daoContenido.findById(id);
    }

}
