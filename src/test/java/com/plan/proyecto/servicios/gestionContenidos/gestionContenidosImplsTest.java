/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionContenidos;

import com.plan.proyecto.beans.Contenido;
import com.plan.proyecto.beans.Mensaje;
import com.plan.proyecto.beans.Comentario;
import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.repositorios.DaoContenido;
import com.plan.proyecto.repositorios.DaoCuenta;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentas;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentasImplTest;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Administrador
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "../applicationContext.xml")
public class gestionContenidosImplsTest {

    @Autowired
    private GestionCuentas gestionCuentas;

    @Autowired
    private DaoCuenta dao;

    @Autowired
    private DaoContenido daoContenido;

    @Autowired
    private GestionContenidos gestionContenidos;

    Logger log = Logger.getLogger(GestionCuentasImplTest.class.getName());

    public gestionContenidosImplsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        daoCuenta.limpiezaCuentas();
    }

    @After
    public void tearDown() {
        dao.limpiezaCuentas();
    }

    /**
     * Test of publicarContenido method, of class gestionContenidosImpls.
     */
    @Test
    public void testPublicarContenido() {
        log.log(Level.INFO, "Publicar Contenidos");
        log.log(Level.INFO, "Prueba de publicación de contenidos");

        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);
        gestionCuentas.AltaCuenta(cuenta);

        Contenido mensaje = new Mensaje("Texto prueba");

        mensaje = gestionContenidos.publicarContenido(cuenta, mensaje, null);

        assertNotNull(mensaje.getId());
        log.log(Level.INFO, "Prueba de inserción de un mensaje, terminada");

        password = "abcd";
        email = "badddd@aaaaa.com";
        nombre = "cesar";
        fecha = new Date();

        Cuenta cuenta2 = new Cuenta(email, password, nombre, fecha);
        gestionCuentas.AltaCuenta(cuenta2);

        Contenido comentario = new Comentario("comentario prueba");

        comentario = gestionContenidos.publicarContenido(cuenta2, mensaje, comentario);

        assertNotNull(comentario.getId());
        log.log(Level.INFO, "Prueba de inserción de un comentario, terminada");
    }

    /**
     * Test of eliminarContenido method, of class gestionContenidosImpls.
     */
    @Test
    public void testEliminarContenido() {
        log.log(Level.INFO, "Eliminar contenidos");
        log.log(Level.INFO, "Prueba de eliminación de contenidos");

        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);
        gestionCuentas.AltaCuenta(cuenta);

        password = "abcd";
        email = "agdddd@aaaaa.com";
        nombre = "cesar";
        fecha = new Date();

        Cuenta cuenta2 = new Cuenta(email, password, nombre, fecha);
        gestionCuentas.AltaCuenta(cuenta2);

        Contenido mensaje = new Mensaje("Texto prueba");
        Contenido comentario = new Comentario("comentario prueba");

        mensaje = gestionContenidos.publicarContenido(cuenta, mensaje, null);
        comentario = gestionContenidos.publicarContenido(cuenta2, mensaje, comentario);

        gestionContenidos.eliminarContenido(comentario);

        assertNull(daoContenido.findById(comentario.getId()));

        log.log(Level.INFO, "Prueba de eliminación de un comentario, terminada");
        
        mensaje = gestionContenidos.eliminarContenido(mensaje);

        assertNull(daoContenido.findById(mensaje.getId()));

        log.log(Level.INFO, "Prueba de eliminación de un mensaje, terminada");

        mensaje = new Mensaje("Texto prueba");
        comentario = new Comentario("comentario prueba");

        mensaje = gestionContenidos.publicarContenido(cuenta, mensaje, null);
        comentario = gestionContenidos.publicarContenido(cuenta2, mensaje, comentario);

        gestionContenidos.eliminarContenido(mensaje);
        assertNull(daoContenido.findById(mensaje.getId()));

        log.log(Level.INFO, "Prueba de eliminación de un mensaje con comentarios, terminada");
    }

    /**
     * Test of publicarComentario method, of class gestionContenidosImpls.
     */
    @Test
    public void testPublicarComentario() {
        log.log(Level.INFO, "Publicar comentario");
        log.log(Level.INFO, "Prueba de publicación de un comentario");

        String password = "abcd";
        String email = "badddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);
        gestionCuentas.AltaCuenta(cuenta);

        password = "abcd";
        email = "addddd@aaaaa.com";
        nombre = "cesar";
        fecha = new Date();

        Cuenta cuenta2 = new Cuenta(email, password, nombre, fecha);
        gestionCuentas.AltaCuenta(cuenta2);

        password = "abcd";
        email = "addsdd@aaaaa.com";
        nombre = "cesar";
        fecha = new Date();

        Cuenta cuenta3 = new Cuenta(email, password, nombre, fecha);
        gestionCuentas.AltaCuenta(cuenta3);

        Contenido mensaje = new Mensaje("visita: www.as.com");
        gestionContenidos.publicarContenido(cuenta, mensaje, null);

        Contenido comentario = new Comentario("Comentario prueba1");
        gestionContenidos.publicarContenido(cuenta2, mensaje, comentario);

        Contenido comentario2 = new Comentario("Comentario prueba2");
        gestionContenidos.publicarContenido(cuenta2, mensaje, comentario2);

        Boolean result = false;
        int resultInt = 2;

        List<Mensaje> listaMensajes = gestionContenidos.mostrarMensajes(cuenta);
        assertEquals(listaMensajes.isEmpty(), result);

        List<Comentario> listaComentarios = gestionContenidos.mostrarComentarios(cuenta2);
        assertEquals(listaComentarios.size(), resultInt);

        List<Contenido> listaComentariosMensaje = gestionContenidos.mostrarComentarios(mensaje);
        assertEquals(listaComentariosMensaje.size(), resultInt);

        log.log(Level.INFO, "Prueba de mostrar contenidos, terminada");
    }
}
