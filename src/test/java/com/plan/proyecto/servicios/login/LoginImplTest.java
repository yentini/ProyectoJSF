/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.login;

import com.plan.proyecto.beans.Cuenta;
import com.plan.proyecto.repositorios.DaoCuenta;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentasImplTest;
import com.plan.proyecto.servicios.gestionCuentas.GestionCuentas;
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
public class LoginImplTest {

    @Autowired
    private GestionLogin gl;

    @Autowired
    private GestionCuentas gc;

    @Autowired
    DaoCuenta dao;

    Logger log = Logger.getLogger(GestionCuentasImplTest.class.getName());

    public LoginImplTest() {
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
     * Test of login method, of class LoginImpl.
     */
    @Test
    public void testLogin() {
        log.log(Level.INFO, "Login");
        log.log(Level.INFO, "Prueba de login");

        String password = "abcd";
        String email = "dddd@aaaaa.com";
        String nombre = "cesar";
        Date fecha = new Date();

        Cuenta cuenta = new Cuenta(email, password, nombre, fecha);
        cuenta.setEmail(email);
        cuenta.setPassword(password);

        cuenta = gc.AltaCuenta(cuenta);

        Cuenta result = gl.autenticarse(email, password);

        assertNotNull(result);

        log.log(Level.INFO, "Prueba de login correcto terminada");

        result = gl.autenticarse("aaa", "bbb");
        assertNull(result);
        log.log(Level.INFO, "Prueba de login incorrecto terminada");
    }

}
