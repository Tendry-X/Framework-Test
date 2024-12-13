package com.test.controller;

import mg.itu.framework.annotation.MyAnnotation;
import mg.itu.framework.annotation.MyParam;
import mg.itu.framework.annotation.HttpMethod;
import mg.itu.framework.model.ModelView;
import com.test.model.Etudiant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SPRINT 8 : Formulaires complexes
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
        return "<h1>Hello from Sprint 8!</h1><p>Framework MVC complet!</p>";
    }
    
    @MyAnnotation(value = "/form", method = HttpMethod.GET)
    public ModelView showForm() {
        ModelView mv = new ModelView("form");
        return mv;
    }
    
    @MyAnnotation(value = "/form", method = HttpMethod.POST)
    public ModelView submitForm(Map<String, Object> params) {
        ModelView mv = new ModelView("result");
        String nom = (String) params.get("nom");
        String prenom = (String) params.get("prenom");
        Etudiant etudiant = new Etudiant(nom, prenom);
        mv.addItem("etudiant", etudiant);
        mv.addItem("allParams", params);
        return mv;
    }
    
    @MyAnnotation(value = "/etudiant/{id}", method = HttpMethod.GET)
    public ModelView showEtudiant(int id) {
        ModelView mv = new ModelView("etudiant");
        if (id >= 0 && id < etudiants.size()) {
            mv.addItem("etudiant", etudiants.get(id));
        }
        return mv;
    }
}