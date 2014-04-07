/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.proyecto.servicios.utilidades;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author Administrador
 */
@Configuration
public abstract class Encriptar {

//    @Value("${claveEncrypt}")
//    @Value("#{systemProperties['constantes.claveEncrypt']}")
//    @Value("#{constantes['claveEncrypt']}")  
    @Value("#{constantes.claveEncrypt}")  
    private static final String claveEncrypt = "cursoJava";

//    private Logger log = Logger.getLogger(Encriptar.class.getName());

    public static String encrypt(String cadena, String clave) {
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
        s.setPassword(clave);
        return s.encrypt(cadena);
    }

    public static String encrypt(String cadena) {
        return encrypt(cadena, claveEncrypt);
    }

    public static String decrypt(String cadena, String clave) {
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
        s.setPassword(clave);
        String devuelve = "";
        try {
            devuelve = s.decrypt(cadena);
        } catch (Exception e) {
        }
        return devuelve;
    }

    public static String decrypt(String cadena) {
        return decrypt(cadena, claveEncrypt);
    }

//    public String encryptURL(String cadena) { 
//        String encrypt = encrypt(cadena,Constantes.CLAVE_ENCRYPT);
//        String encode="";
//        try {
//            encode = URLEncoder.encode(encrypt, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return encode; 
//    }
}
