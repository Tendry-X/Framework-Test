package com.test.controller;

import mg.itu.framework.annotation.MyAnnotation;
import mg.itu.framework.annotation.HttpMethod;
import mg.itu.framework.model.ModelView;
import com.test.model.Etudiant;

/**
 * SPRINT 3 : Utilisation de ModelView
 */
@MyAnnotation(value = "", method = HttpMethod.GET)
public class TestController {
    
    @MyAnnotation(value = "/hello", method = HttpMethod.GET)
    public String hello() {
        return "<h1>Hello from Sprint 3!</h1>";
    }
    
    @MyAnnotation(value = "/etudiant", method = HttpMethod.GET)
    public ModelView showEtudiant() {
        ModelView mv = new ModelView("etudiant");
        
        Etudiant etudiant = new Etudiant("Rakoto", "Jean");
        mv.addItem("etudiant", etudiant);
        
        return mv;
    }
}