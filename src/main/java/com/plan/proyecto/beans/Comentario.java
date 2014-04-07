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
@DiscriminatorValue("C")
@NamedQueries({
    @NamedQuery(name = "Comentario.findComentarioByCuenta", query = "SELECT m FROM Comentario m WHERE m.cuenta.id = :idValor")
    
})
public class Comentario extends Contenido {

    public Comentario() {
    }

    public Comentario(String texto) {
        setTexto(texto);
    }

    @Override
    public String toString() {
        return "Comentario{" + "texto=" + getTexto() + '}';
    }
    
}
