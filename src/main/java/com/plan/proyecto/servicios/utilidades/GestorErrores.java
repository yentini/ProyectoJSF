/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plan.proyecto.servicios.utilidades;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author usuario
 */
@ControllerAdvice
public class GestorErrores {
    @ExceptionHandler(Exception.class)
    public ModelAndView errorGeneral(Exception e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("excepcion", e);
        return mav;
    }
}
