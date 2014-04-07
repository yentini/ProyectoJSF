/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.gestionCuentas;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.repositorios.DaoCuenta;
import java.util.Date;
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
public class GestionCuentasImplTest {

    @Autowired
    private GestionCuentas gestionCuentas;

    @Autowired
    DaoCuenta daoCuenta;

    Logger log = Logger.getLogger(GestionCuentasImplTest.class.getName());

    public GestionCuentasImplTest() {

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
        daoCuenta.limpiezaCuentas();
    }

    /**
     * Test of AltaCuenta method, of class GestionCuentasImpl.
     */
    @Test
    public void testAltaCuenta() {
        log.log(Level.INFO, "AltaCuenta");
        log.log(Level.INFO, "Prueba de inserción de usuario");

        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);

        Boolean expResult = true;
        assertNotNull(gestionCuentas.AltaCuenta(cuenta).getId());

        log.log(Level.INFO, "Prueba de inserción de usuario terminada");
        log.log(Level.INFO, "Prueba de inserción de un segundo usuario repetido");

        password = "abcd";
        email = "adddd@aaaaa.com";
        nombre = "cesar";
        fecha = new Date();

        Cuenta cuenta2 = new Cuenta(email, password, nombre, fecha);

        expResult = false;
        assertNull(gestionCuentas.AltaCuenta(cuenta2));

        log.log(Level.INFO, "Prueba de inserción de un segundo usuario repetido, terminada");
    }

    /**
     * Test of ModificarCuenta method, of class GestionCuentasImpl.
     */
    @Test
    public void testModificarCuenta() {
        log.log(Level.INFO, "ModificarCuenta");
        log.log(Level.INFO, "Prueba de modificación de una cuenta");
        log.log(Level.INFO, "Creo una cuenta");

        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);

        gestionCuentas.AltaCuenta(cuenta);

        log.log(Level.INFO, "cuenta: " + cuenta.getApellidos());
        log.log(Level.INFO, "Modifico la cuenta");

        String apellido = "marin";
        cuenta.setApellidos(apellido);
        cuenta = gestionCuentas.ModificarCuenta(cuenta);
        assertNotNull(cuenta);
        assertTrue(cuenta.getApellidos().equals(apellido));

        log.log(Level.INFO, "Prueba de inserción de un segundo usuario repetido, terminada");
    }

    /**
     * Test of BajaCuenta method, of class GestionCuentasImpl.
     */
    @Test
    public void testBajaCuenta() {
        log.log(Level.INFO, "BajaCuenta");
        log.log(Level.INFO, "Prueba de eliminación de una cuenta");
        log.log(Level.INFO, "Creo una cuenta");

        String password = "abcd";
        String email = "adddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);

        cuenta = gestionCuentas.AltaCuenta(cuenta);

        gestionCuentas.BajaCuenta(cuenta);

        assertNull(daoCuenta.findById(cuenta.getId()));

        log.log(Level.INFO, "Prueba de inserción de un segundo usuario repetido, terminada");
    }
}
