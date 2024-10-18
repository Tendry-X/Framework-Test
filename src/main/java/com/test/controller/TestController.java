package com.test.controller;

import mg.itu.framework.annotation.MyAnnotation;
import mg.itu.framework.annotation.HttpMethod;

/**
 * SPRINT 2 : Premier contrôleur avec annotations
 */
@MyAnnotation(value = "", method = HttpMethod.GET)
public class TestController {
    
    @MyAnnotation(value = "/hello", method = HttpMethod.GET)
    public String hello() {
        return "<h1>Hello from Sprint 2!</h1>" +
               "<p>Le framework supporte maintenant les annotations @MyAnnotation</p>";
    }
    
    @MyAnnotation(value = "/", method = HttpMethod.GET)
    public String home() {
        return "<h1>Framework-Test - Sprint 2</h1>" +
               "<p>Essayez : <a href='/framework-test/hello'>/hello</a></p>";
    }
}