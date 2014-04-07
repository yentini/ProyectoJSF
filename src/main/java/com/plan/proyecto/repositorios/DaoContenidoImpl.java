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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrador
 */
@Repository
@Transactional
public class DaoContenidoImpl extends DaoGenericoImpl<Contenido, Long> implements DaoContenido {

    @PersistenceContext
    EntityManager em;

    @Override
    public Contenido findMensajeByComentario(Contenido comentario) {

        if (comentario == null) {
            return null;
        }

        TypedQuery<Contenido> query = em.createNamedQuery("Contenido.findMensajeByComentario", Contenido.class);
        query.setParameter("valor", comentario);
        List<Contenido> listaTemp = query.getResultList();

        Contenido retorno;

        if (listaTemp.isEmpty()) {
            retorno = null;
        } else {
            retorno = listaTemp.get(0);
        }
        return retorno;
    }

    @Override
    public List<Mensaje> findMensajeByCuenta(Cuenta cuenta) {

        if (cuenta == null) {
            return null;
        }

        TypedQuery<Mensaje> query = em.createNamedQuery("Mensaje.findMensajeByCuenta", Mensaje.class);
        query.setParameter("idValor", cuenta.getId());

        return query.getResultList();
    }
    
    @Override
    public List<Comentario> findComentarioByCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }

        TypedQuery<Comentario> query = em.createNamedQuery("Comentario.findComentarioByCuenta", Comentario.class);
        query.setParameter("idValor", cuenta.getId());

        return query.getResultList();
    }

    @Override
    public List<Contenido> findComentariosoByMensaje(Contenido mensaje) {

        if (mensaje == null) {
            return null;
        }

        Query query = em.createNamedQuery("Contenido.findComentariosByMensaje");
        query.setParameter("idValor", mensaje.getId());

        return query.getResultList();
    }

    @Override
    public List<Contenido> findContenidosByCuenta(Long id) {
        TypedQuery<Contenido> query = em.createNamedQuery("Contenido.findByCuenta", Contenido.class);
        query.setParameter("valor", id);
        return query.getResultList();
    }
    
}
