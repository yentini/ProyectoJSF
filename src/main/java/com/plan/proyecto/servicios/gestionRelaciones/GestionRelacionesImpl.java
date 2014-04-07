/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionRelaciones;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.repositorios.DaoCuenta;
import java.util.ArrayList;
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
public class GestionRelacionesImpl implements GestionRelaciones {

    @Autowired
    DaoCuenta dao;

    @Override
    public List<Cuenta> hacerAmigos(Cuenta origen, Cuenta... amigos) {

        List<Cuenta> listaDevolver = new ArrayList<>();
        for (Cuenta cuenta : amigos) {
            listaDevolver.add(hacerAmigo(origen, cuenta));
        }
//        dao.modificar(origen);
        return listaDevolver;
    }

    private Cuenta hacerAmigo(Cuenta origen, Cuenta amigo) {

        Cuenta retorno = null;

        origen = dao.findById(origen.getId());
        amigo = dao.findById(amigo.getId());

        if (!sonAmigos(origen, amigo)) {
            origen.getAmigos().add(amigo);
            retorno = amigo;
        }
        return retorno;
    }

    @Override
    public Boolean sonAmigos(Cuenta origen, Cuenta destino) {
        return dao.sonAmigos(origen, destino);
    }

    @Override
    public List<Cuenta> mostrarAmigos(Cuenta cuenta) {

        return dao.findAmigosByCuenta(cuenta);
    }

    @Override
    public List<Cuenta> quitarAmigos(Cuenta origen, Cuenta... amigos) {

        List<Cuenta> listaDevolver = new ArrayList<>();
        for (Cuenta cuenta : amigos) {
            listaDevolver.add(quitarAmigo(origen, cuenta));
        }
//        dao.modificar(origen);
        return listaDevolver;
    }

    private Cuenta quitarAmigo(Cuenta origen, Cuenta amigo) {

        Cuenta retorno = null;

        origen = dao.findById(origen.getId());
        amigo = dao.findById(amigo.getId());

        if (sonAmigos(origen, amigo)) {
            origen.getAmigos().remove(amigo);
            retorno = amigo;
        }
        return retorno;
    }

    @Override
    public List<Cuenta> amigosPotenciales(Cuenta origen) {

        if (origen == null) {
            return null;
        }
        return dao.findAmigosPotencialesByCuenta(origen);
    }

    @Override
    public List<Cuenta> deQuienSoyAmigo(Cuenta cuenta) {
        
         if (cuenta == null) {
            return null;
        }
        return dao.findDeQuienSoyAmigoByCuenta(cuenta);
    }

}
