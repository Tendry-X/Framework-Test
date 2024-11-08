package com.test.controller;

import mg.itu.framework.annotation.MyAnnotation;
import mg.itu.framework.annotation.HttpMethod;
import mg.itu.framework.model.ModelView;
import com.test.model.Etudiant;
import java.util.ArrayList;
import java.util.List;

/**
 * SPRINT 3bis : Paramètres dynamiques {id}
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
        return "<h1>Hello from Sprint 3bis!</h1>";
    }
    
    @MyAnnotation(value = "/etudiant/{id}", method = HttpMethod.GET)
    public ModelView showEtudiant() {
        ModelView mv = new ModelView("etudiant");
        mv.addItem("etudiant", etudiants.get(0));
        return mv;
    }
}