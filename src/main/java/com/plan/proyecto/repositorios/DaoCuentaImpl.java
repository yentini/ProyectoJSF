/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.repositorios;

import com.plan.proyecto.beans.Cuenta;
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
public class DaoCuentaImpl extends DaoGenericoImpl<Cuenta, Long> implements DaoCuenta {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Cuenta> findByNombre(String nombre) {
        TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findByNombre", Cuenta.class);
        query.setParameter("valor", nombre);
        List<Cuenta> results = query.getResultList();
        return results;
    }

    @Override
    public Cuenta findByEmailAndPassword(String email, String pwd) {
        TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findByEmailAndPassword", Cuenta.class);
        query.setParameter("valorEmail", email);
        query.setParameter("valorPassword", pwd);
        List<Cuenta> lista = query.getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }

    @Override
    public void limpiezaCuentas() {
        Query query = em.createNamedQuery("Cuenta.findAll");
        List<Cuenta> cuentas = query.getResultList();

        for (Cuenta cuenta : cuentas) {
//            eliminar(cuenta);
            em.remove(cuenta);
        }
    }

    @Override
    public Cuenta findByEmail(String email) {
        TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findByEmail", Cuenta.class);
        query.setParameter("valor", email);
        List<Cuenta> lista = query.getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }

    @Override
    public List<Cuenta> findAll() {
        TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findAll", Cuenta.class);
        return query.getResultList();
    }

    @Override
    public Boolean sonAmigos(Cuenta origen, Cuenta amigo) {
        TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findAmigos", Cuenta.class);
        query.setParameter("idorigen", origen.getId());
        query.setParameter("idamigo", amigo);

        List<Cuenta> temp = query.getResultList();
        return !query.getResultList().isEmpty();
    }

    @Override
    public List<Cuenta> findAmigosByCuenta(Cuenta cuenta) {
        Query query = em.createNamedQuery("Cuenta.findAmigosByCuenta");
        query.setParameter("idorigen", cuenta.getId());
        return query.getResultList();
    }

    @Override
    public List<Cuenta> findAmigosPotencialesByCuenta(Cuenta cuenta) {
//        Query query = em.createNamedQuery("Cuenta.findAmigosPotencialesByCuenta",Cuenta.class);       
//        query.setParameter("idorigen", cuenta.getId());
               
        List<Cuenta> amigos = findAmigosByCuenta(cuenta);
        
        List<Cuenta> usuarios = findAll();
        
        usuarios.remove(cuenta);
                
        usuarios.removeAll(amigos);
                
        return usuarios;
    }

    @Override
    public List<Cuenta> findDeQuienSoyAmigoByCuenta(Cuenta cuenta) {
        Query query = em.createNamedQuery("Cuenta.findDeQuienSoyAmigoByCuenta");
        query.setParameter("idorigen", cuenta.getId());
        return query.getResultList();
    }
}
