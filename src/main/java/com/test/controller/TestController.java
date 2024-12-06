package com.test.controller;

import mg.itu.framework.annotation.MyAnnotation;
import mg.itu.framework.annotation.MyParam;
import mg.itu.framework.annotation.HttpMethod;
import mg.itu.framework.model.ModelView;
import com.test.model.Etudiant;
import java.util.ArrayList;
import java.util.List;

/**
 * SPRINT 7 : GET/POST distincts
 */
@MyAnnotation(value = "", method = HttpMethod.GET)
public class TestController {
    
    private static List<Etudiant> etudiants = new ArrayList<>();
    
    static {
        etudiants.add(new Etudiant("Rakoto", "Jean"));
        etudiants.add(new Etudiant("Rabe", "Marie"));
        etudiants.add(new Etudiant("Rasoa", "Paul"));
    }
    
    @MyAnnotation(value = "/hello", method = HttpMethod.GET)
    public String hello() {
        return "<h1>Hello from Sprint 7!</h1>";
    }
    
    @MyAnnotation(value = "/form", method = HttpMethod.GET)
    public ModelView showForm() {
        ModelView mv = new ModelView("form");
        return mv;
    }
    
    @MyAnnotation(value = "/form", method = HttpMethod.POST)
    public ModelView submitForm(@MyParam("nom") String nom, @MyParam("prenom") String prenom) {
        ModelView mv = new ModelView("result");
        Etudiant etudiant = new Etudiant(nom, prenom);
        mv.addItem("etudiant", etudiant);
        return mv;
    }
}