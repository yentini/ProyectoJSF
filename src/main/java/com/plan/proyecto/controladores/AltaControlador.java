/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.controladores;

import com.plan.proyecto.beans.Comentario;
import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.beans.Mensaje;
import com.plan.proyecto.servicios.gestionContenidos.GestionContenidos;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentas;
import com.plan.proyecto.servicios.gestionRelaciones.GestionRelaciones;
import com.plan.proyecto.servicios.login.GestionLogin;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrador
 */
//@RequestMapping(value = {"alta.html","formularioLogin.html"})
//@RequestMapping(value = {"alta.html","formularioLogin.html","formularioAlta.html"})
@Controller
public class AltaControlador {

    @Autowired
    GestionCuentas gc;

    @Autowired
    GestionLogin gl;

    @Autowired
    GestionContenidos gestionContenidos;

    @Autowired
    GestionRelaciones gestionRelaciones;

    @ModelAttribute("cuentaAlta")
    public Cuenta getCuentaAlta() {
        return new Cuenta();
    }

    @ModelAttribute("cuentaLogin")
    public Cuenta getCuentaLogin() {

        return new Cuenta();
    }

    @ModelAttribute("mensaje")
    public Contenido getMensaje() {
        return new Mensaje("Escribe el mensaje...");
    }

    @ModelAttribute("comentario")
    public Contenido getComentario() {
        return new Comentario("Escribe el comentario...");
    }

    @RequestMapping(value = "/alta.html", method = RequestMethod.GET)
    public void tratarGet() {
    }

    @RequestMapping(value = "formularioAlta.html", method = RequestMethod.POST)
    public String tratarAlta(@ModelAttribute("cuentaAlta") @Valid Cuenta cuenta, Model model) {

        if (gc.existeCuenta(cuenta)) {
            model.addAttribute("mensajeAlta", "El usuario ya existe");
            return "alta";
        } else {
            Cuenta retornoCuenta = gc.AltaCuenta(cuenta);

            if (retornoCuenta != null) {
                List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(retornoCuenta);
                List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(retornoCuenta);
                List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(retornoCuenta);
                List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(retornoCuenta);

                model.addAttribute("muroId", retornoCuenta);
                model.addAttribute("muros", muros);
                model.addAttribute("amigos", amigos);
                model.addAttribute("usuarios", usuarios);
                model.addAttribute("mensajes", mensajes);
                model.addAttribute("cuenta", retornoCuenta);
                if (mensajes != null) {
                    model.addAttribute("vacio", mensajes.isEmpty());
                }
                if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
                return "muro";
            } else {
                model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
                return "alta";
            }
        }
    }

    @RequestMapping(value = "/formularioLogin.html", method = RequestMethod.POST)
    public String tratarLogin(@ModelAttribute("cuentaLogin") Cuenta cuenta, Model model) {

        Cuenta retornoCuenta = gl.autenticarse(cuenta);

        if (retornoCuenta != null) {
            List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(retornoCuenta);
            List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(retornoCuenta);
            List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(retornoCuenta);
            List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(retornoCuenta);

            model.addAttribute("muroId", retornoCuenta);
            model.addAttribute("muros", muros);
            model.addAttribute("amigos", amigos);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("cuenta", retornoCuenta);

            if (mensajes != null) {
                model.addAttribute("vacio", mensajes.isEmpty());
            }
            if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
            return "muro";
        } else {
            model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
            return "alta";
        }
    }

    @RequestMapping(value = "/formularioPublicarContenido.html", method = RequestMethod.POST)
    public String tratarMensaje(@RequestParam("ident") Long id, @ModelAttribute("mensaje") Contenido mensaje, Model model) {

        Cuenta retornoCuenta = gc.devolverCuenta(id);

        gestionContenidos.publicarContenido(retornoCuenta, mensaje, null);

        if (retornoCuenta != null) {
            List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(retornoCuenta);
            List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(retornoCuenta);
            List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(retornoCuenta);
            List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(retornoCuenta);

            model.addAttribute("muroId", retornoCuenta);
            model.addAttribute("muros", muros);
            model.addAttribute("amigos", amigos);
            model.addAttribute("usuarios", usuarios);
            mensaje = new Mensaje("Escribe el mensaje...");
            model.addAttribute("mensaje", mensaje);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("cuenta", retornoCuenta);
            if (mensajes != null) {
                model.addAttribute("vacio", mensajes.isEmpty());
            }
            if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
            return "muro";
        } else {
            model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
            return "alta";
        }
    }

    @RequestMapping(value = "/formularioPublicarComentario.html", method = RequestMethod.POST)
    public String tratarComentario(@RequestParam("idAmigo") Long idAmigo, @RequestParam("ident") Long id, @RequestParam("mensajeId") Long idMensaje, @ModelAttribute("comentario") Contenido comentario, Model model) {

        Cuenta retornoCuenta = gc.devolverCuenta(id);
        Cuenta amigo = gc.devolverCuenta(idAmigo);

        Contenido mensaje = gestionContenidos.devolverContenido(idMensaje);

        gestionContenidos.publicarContenido(retornoCuenta, mensaje, comentario);

        if (retornoCuenta != null) {

            List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(amigo);
            List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(amigo);
            List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(amigo);
            List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(amigo);

            model.addAttribute("muroId", amigo);
            model.addAttribute("muros", muros);
            model.addAttribute("amigos", amigos);
            model.addAttribute("usuarios", usuarios);
            mensaje = new Mensaje("Escribe el comentario...");
            model.addAttribute("mensaje", mensaje);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("cuenta", retornoCuenta);
            if (mensajes != null) {
                model.addAttribute("vacio", mensajes.isEmpty());
            }
            if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
            return "muro";
        } else {
            model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
            return "alta";
        }
    }

    @RequestMapping(value = "/eliminarMensaje.html", method = RequestMethod.POST)
    public String eliminarMensaje(@RequestParam("identMensaje") Long idMensaje, @RequestParam("ident") Long id, Model model) {

        Cuenta retornoCuenta = gc.devolverCuenta(id);

        Contenido mensaje = gestionContenidos.devolverContenido(idMensaje);

        gestionContenidos.eliminarContenido(mensaje);

        if (retornoCuenta != null) {
            List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(retornoCuenta);
            List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(retornoCuenta);
            List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(retornoCuenta);
            List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(retornoCuenta);

            model.addAttribute("muroId", retornoCuenta);
            model.addAttribute("muros", muros);
            model.addAttribute("amigos", amigos);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("cuenta", retornoCuenta);
            if (mensajes != null) {
                model.addAttribute("vacio", mensajes.isEmpty());
            }
            if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
            return "muro";
        } else {
            model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
            return "alta";
        }
    }

    @RequestMapping(value = "/hacerAmigo.html", method = RequestMethod.GET)
    public String hacerAmigo(@RequestParam("idAmigo") Long idAmigo, @RequestParam("ident") Long id, Model model) {

        Cuenta retornoCuenta = gc.devolverCuenta(id);
        Cuenta amigo = gc.devolverCuenta(idAmigo);

        gestionRelaciones.hacerAmigos(retornoCuenta, amigo);

        if (retornoCuenta != null) {
            List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(retornoCuenta);
            List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(retornoCuenta);
            List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(retornoCuenta);
            List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(retornoCuenta);

            model.addAttribute("muroId", retornoCuenta);
            model.addAttribute("muros", muros);
            model.addAttribute("amigos", amigos);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("cuenta", retornoCuenta);

            if (mensajes != null) {
                model.addAttribute("vacio", mensajes.isEmpty());
            }
            if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
            return "muro";
        } else {
            model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
            return "alta";
        }
    }

    @RequestMapping(value = "/quitarAmigo.html", method = RequestMethod.GET)
    public String quitarAmigo(@RequestParam("idAmigo") Long idAmigo, @RequestParam("ident") Long id, Model model) {

        Cuenta retornoCuenta = gc.devolverCuenta(id);
        Cuenta amigo = gc.devolverCuenta(idAmigo);

        gestionRelaciones.quitarAmigos(retornoCuenta, amigo);

        if (retornoCuenta != null) {
            List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(retornoCuenta);
            List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(retornoCuenta);
            List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(retornoCuenta);
            List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(retornoCuenta);

            model.addAttribute("muroId", retornoCuenta);
            model.addAttribute("muros", muros);
            model.addAttribute("amigos", amigos);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("cuenta", retornoCuenta);

            if (mensajes != null) {
                model.addAttribute("vacio", mensajes.isEmpty());
            }
            if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
            return "muro";
        } else {
            model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
            return "alta";
        }
    }

    @RequestMapping(value = "/cambiarMuro.html", method = RequestMethod.GET)
    public String cambiarMuro(@RequestParam("idAmigo") Long idAmigo, @RequestParam("ident") Long id, Model model) {

        Cuenta retornoCuenta = gc.devolverCuenta(id);
        Cuenta amigo = gc.devolverCuenta(idAmigo);

        gestionRelaciones.quitarAmigos(retornoCuenta, amigo);

        if (retornoCuenta != null) {
            List<Mensaje> mensajes = gestionContenidos.mostrarMensajes(amigo);
            List<Cuenta> amigos = gestionRelaciones.mostrarAmigos(amigo);
            List<Cuenta> usuarios = gestionRelaciones.amigosPotenciales(amigo);
            List<Cuenta> muros = gestionRelaciones.deQuienSoyAmigo(amigo);

            model.addAttribute("muroId", amigo);
            model.addAttribute("muros", muros);
            model.addAttribute("amigos", amigos);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("cuenta", retornoCuenta);

            if (mensajes != null) {
                model.addAttribute("vacio", mensajes.isEmpty());
            }
            if (muros != null) {
                model.addAttribute("vacioMuros", muros.isEmpty());
            }
            return "muro";
        } else {
            model.addAttribute("mensajeLogin", "El usuario no existe o la contraseña es incorrecta");
            return "alta";
        }
    }
}
