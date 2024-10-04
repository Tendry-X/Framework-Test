package com.test.controller;

import mg.itu.framework.annotation.MyAnnotation;
import mg.itu.framework.annotation.HttpMethod;

@MyAnnotation(value = "", method = HttpMethod.GET)
public class HelloController {

    @MyAnnotation(value = "/hello", method = HttpMethod.GET)
    public String sayHello() {
        return "<h1>Hello from Framework MVC!</h1>" +
               "<p>Le Framework fonctionne parfaitement!</p>" +
               "<p>Sprints 1-8 : COMPLETS</p>";
    }

    @MyAnnotation(value = "/", method = HttpMethod.GET)
    public String home() {
        return "<h1>Bienvenue sur le Framework MVC</h1>" +
               "<p>Essayez : <a href='/framework-test/hello'>/hello</a></p>";
    }

    @MyAnnotation(value = "/test/{id}", method = HttpMethod.GET)
    public String testWithParam(int id) {
        return "<h1>Test avec paramètre dynamique</h1>" +
               "<p>ID reçu : <strong>" + id + "</strong></p>" +
               "<p>Sprint 3bis fonctionne!</p>";
    }
}