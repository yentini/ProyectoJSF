/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionRelaciones;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.repositorios.DaoCuenta;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentas;
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
public class GestionRelacionesImplTest {

    Logger log = Logger.getLogger(GestionRelacionesImplTest.class.getName());

    @Autowired
    private GestionCuentas gc;

    @Autowired
    private DaoCuenta dao;

    @Autowired
    private GestionRelaciones gr;

    public GestionRelacionesImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        dao.limpiezaCuentas();
    }

    /**
     * Test of mostrarCuentasSistema method, of class GestionRelacionesImpl.
     */
    @Test
    public void testMostrarCuentasSistema() {
        log.log(Level.INFO, "Muestra Cuentas Sistema");

        log.log(Level.INFO, "Prueba de mostrar cuentas");

       
        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);
        
        gc.AltaCuenta(cuenta);

        List<Cuenta> cuentas = gc.mostrarCuentasSistema();

        assertEquals(cuentas.size(), 1);

        gc.BajaCuenta(cuenta);

        cuentas = gc.mostrarCuentasSistema();

        assertEquals(cuentas.size(), 0);
        log.log(Level.INFO, "Prueba de mostrar cuentas, terminada");
    }

    /**
     * Test of hacerAmigos method, of class GestionRelacionesImpl.
     */
    @Test
    public void testHacerAmigos() {
        System.out.println("sonAmigos");
        
        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta origen = new Cuenta(email, password, nombre, fecha);
        
         password = "abcd";
         email = "bdddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta destino = new Cuenta(email, password, nombre, fecha);

        gc.AltaCuenta(origen);
        gc.AltaCuenta(destino);

        List<Cuenta> amigosNuevos = gr.hacerAmigos(origen, destino);
        List<Cuenta> amigos = gr.mostrarAmigos(origen);
        assertEquals(1, amigos.size());

    }

    /**
     * Test of sonAmigos method, of class GestionRelacionesImpl.
     */
    @Test
    public void testSonAmigos() {
        System.out.println("sonAmigos");       
        
        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta origen = new Cuenta(email, password, nombre, fecha);
        
         password = "aabcd";
         email = "addddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta destino = new Cuenta(email, password, nombre, fecha);
        
         password = "babcd";
         email = "fadddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta destino2 = new Cuenta(email, password, nombre, fecha);

        gc.AltaCuenta(origen);
        gc.AltaCuenta(destino);
        gc.AltaCuenta(destino2);

        Boolean expResult = false;
        Boolean result = gr.sonAmigos(origen, destino);
        assertEquals(expResult, result);

        gr.hacerAmigos(origen, destino);
        List<Cuenta> amigos = gr.mostrarAmigos(origen);
        assertEquals(1, amigos.size());
        
        gr.hacerAmigos(origen, destino2);
        amigos = gr.mostrarAmigos(origen);
        assertEquals(2, amigos.size());
        
        expResult = true;
        result = gr.sonAmigos(origen, destino);
        assertEquals(expResult, result);
        
        expResult = true;
        result = gr.sonAmigos(origen, destino2);
        assertEquals(expResult, result);
        
        expResult = false;
        result = gr.sonAmigos(destino, destino2);
        assertEquals(expResult, result);
    }
   

    /**
     * Test of quitarAmigos method, of class GestionRelacionesImpl.
     */
    @Test
    public void testQuitarAmigos() {
        System.out.println("QuitarAmigos");
       
        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta origen = new Cuenta(email, password, nombre, fecha);
        
         password = "abcd";
         email = "bdddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta destino = new Cuenta(email, password, nombre, fecha);
        
         password = "abcd";
         email = "cdddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta destino2 = new Cuenta(email, password, nombre, fecha);

        gc.AltaCuenta(origen);
        gc.AltaCuenta(destino);
        gc.AltaCuenta(destino2);  

        gr.hacerAmigos(origen, destino);
        
        gr.hacerAmigos(origen, destino2);
        
        Boolean expResult = true;
        Boolean result = gr.sonAmigos(origen, destino);
        assertEquals(expResult, result);
        
        gr.quitarAmigos(origen, destino);
        
        expResult = false;
        result = gr.sonAmigos(origen, destino);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of mostrarAmigos method, of class GestionRelacionesImpl.
     */
    @Test
    public void testMostrarAmigos() {
        System.out.println("Mostrar amigos");
        
        String password = "abcd";
        String email = "bbbb@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta origen = new Cuenta(email, password, nombre, fecha);
        
         password = "abcd";
         email = "aaaa@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta destino = new Cuenta(email, password, nombre, fecha);

        gc.AltaCuenta(origen);
        gc.AltaCuenta(destino);

        List<Cuenta> amigosNuevos = gr.hacerAmigos(origen, destino);
        List<Cuenta> amigos = gr.mostrarAmigos(origen);
        assertEquals(1, amigos.size());
    }

    /**
     * Test of amigosPotenciales method, of class GestionRelacionesImpl.
     */
    @Test
    public void testAmigosPotenciales() {
        System.out.println("Mostrar amigos potenciales");
        
        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta u1 = new Cuenta(email, password, nombre, fecha);
        
         password = "abcd";
         email = "bdddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta u2 = new Cuenta(email, password, nombre, fecha);
        
         password = "abcd";
         email = "cdddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta u3 = new Cuenta(email, password, nombre, fecha);
        
         password = "abcd";
         email = "ddddd@aaaaa.com";
         nombre = "cesar";
         fecha = new Date();

        Cuenta u4 = new Cuenta(email, password, nombre, fecha);
                
        gc.AltaCuenta(u1);
        gc.AltaCuenta(u2);
        gc.AltaCuenta(u3);
        gc.AltaCuenta(u4);

        gr.hacerAmigos(u1, u2);
        
        List<Cuenta> amigosPotenciales = gr.amigosPotenciales(u1);
                        
        assertEquals(2, amigosPotenciales.size());
    }
}
