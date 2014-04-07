/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Administrador
 */
@Entity
@DiscriminatorValue("M")
@NamedQueries({
    @NamedQuery(name = "Mensaje.findMensajeByCuenta", query = "SELECT m FROM Mensaje m WHERE m.cuenta.id = :idValor")
})
public class Mensaje extends Contenido {

    public Mensaje() {
    }

    public Mensaje(String texto) {
        setTexto(texto);
    }

    @Override
    public String toString() {
        return "Mensaje{" + "texto=" + getTexto() + '}';
    }
  
}
