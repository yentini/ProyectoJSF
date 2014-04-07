/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.beans;

import com.plan.proyecto.servicios.utilidades.UrlParser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Administrador
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "TIPO_CONTENIDO")
@NamedQueries({
    @NamedQuery(name = "Contenido.findByCuenta", query = "SELECT c FROM Contenido c WHERE c.cuenta.id = :valor"),
    @NamedQuery(name = "Contenido.findMensajeByComentario", query = "SELECT c FROM Contenido c WHERE :valor MEMBER OF c.comentarios"),
    @NamedQuery(name = "Contenido.findComentariosByMensaje", query = "SELECT c.comentarios FROM Contenido c WHERE c.id = :idValor")

})
public abstract class Contenido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String texto;

    private String resumen;

    @ManyToOne
    private Cuenta cuenta;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Contenido> comentarios = new ArrayList<>();

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getTexto() {

        return texto;
    }

    public void setTexto(String text) {

        int longitudResumen = 20;
        String puntosSuspensivos = "...";

        texto = actualizarEnlaces(text);

        int longitudEnlace = texto.indexOf("<A");

        longitudEnlace = longitudEnlace == -1 ? longitudResumen : longitudEnlace;

        int longitud = texto.length();

        if (longitud < longitudResumen) {
            longitudResumen = longitud;
        }
        if (longitudEnlace < longitudResumen) {
            longitudResumen = longitudEnlace;
        }

        this.resumen = texto.substring(0, longitudResumen) + puntosSuspensivos;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Cuenta getCuenta() {

        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {

        this.cuenta = cuenta;
    }

    public List<Contenido> getComentarios() {

        return comentarios;
    }

    public void setComentarios(List<Contenido> comentarios) {

        this.comentarios = comentarios;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.texto);
        hash = 79 * hash + Objects.hashCode(this.cuenta);
        hash = 79 * hash + Objects.hashCode(this.comentarios);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contenido other = (Contenido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        return true;
    }

    private String actualizarEnlaces(String texto) {
        List<String> result = UrlParser.pullLinks(texto);
        for (String enlace : result) {
//            texto = texto.replace(enlace, "<A href='" + enlace + "'/>");
            texto = texto.replace(enlace, "<A href='http://" + enlace + "'/>" + enlace + "</A>");
        }

        return texto;
    }

    @Override
    public String toString() {

        return "Contenido{" + "id=" + id + ", texto=" + texto + ", cuenta=" + cuenta + '}';
    }

}
