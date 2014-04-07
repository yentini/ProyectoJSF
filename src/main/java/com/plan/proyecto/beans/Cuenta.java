/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrador
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Cuenta.findByNombre", query = "SELECT c FROM Cuenta c WHERE c.nombre = :valor"),
    @NamedQuery(name = "Cuenta.findByEmailAndPassword", query = "SELECT c FROM Cuenta c WHERE c.email = :valorEmail and c.password = :valorPassword"),
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByEmail", query = "SELECT c FROM Cuenta c WHERE c.email = :valor"),
    @NamedQuery(name = "Cuenta.findAmigos", query = "SELECT c FROM Cuenta c WHERE c.id = :idorigen and :idamigo MEMBER OF c.amigos"),
    @NamedQuery(name = "Cuenta.findDeQuienSoyAmigoByCuenta", query = "SELECT c FROM Cuenta c WHERE :idorigen MEMBER OF c.amigos"),
    @NamedQuery(name = "Cuenta.findAmigosByCuenta", query = "SELECT c.amigos FROM Cuenta c WHERE c.id = :idorigen") //@NamedQuery(name = "Cuenta.findAmigosPotencialesByCuenta", query = "SELECT c FROM Cuenta AS c WHERE c MEMBER OF (SELECT p.amigos FROM Cuenta AS p WHERE p.id = :idorigen)")
   
})
//@ManagedBean(eager = true)
//@SessionScoped
@Component
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 6, max = 25)
    private String email;

    @NotNull
    @Size(min = 4, max = 25)
    private String password;

    @NotNull
    @Size(min = 2, max = 25)
    private String nombre;

    private String apellidos;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contenido> contenidos = new ArrayList<>();

    @ManyToMany()
    private List<Cuenta> amigos = new ArrayList<>();

    public Cuenta() {
    }

    public Cuenta(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Cuenta(String email, String password, String nombre, Date fechaNacimiento) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Cuenta(String email, String password, String nombre, String apellidos, Date fechaNacimiento) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.apellidos);
        hash = 79 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 79 * hash + Objects.hashCode(this.contenidos);
        hash = 79 * hash + Objects.hashCode(this.amigos);
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
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    public List<Cuenta> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Cuenta> amigos) {
        this.amigos = amigos;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id + ", email=" + email + ", password=" + password + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

}
