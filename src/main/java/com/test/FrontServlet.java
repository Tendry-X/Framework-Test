package com.test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class FrontServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());
        
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>Sprint 1</title>");
        out.println("    <style>");
        out.println("        body { font-family: Arial, sans-serif; margin: 40px; background: #f5f5f5; }");
        out.println("        .container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("        h1 { color: #27ae60; }");
        out.println("        .info { background: #ecf0f1; padding: 15px; border-radius: 5px; margin: 10px 0; }");
        out.println("        .label { font-weight: bold; color: #2c3e50; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>✅ Sprint 1 - Affichage de l'URL</h1>");
        out.println("        <div class='info'>");
        out.println("            <p><span class='label'>URL demandee :</span> " + path + "</p>");
        out.println("        </div>");
        out.println("        <hr>");
        out.println("        <p><em>URI complet : " + uri + "</em></p>");
        out.println("        <p><em>Context Path : " + contextPath + "</em></p>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
        
        out.flush();
    }
}