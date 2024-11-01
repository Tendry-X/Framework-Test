<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.model.Etudiant" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sprint 3 - ModelView</title>
</head>
<body>
    <h1>Sprint 3 : ModelView</h1>
    <%
        Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
        if (etudiant != null) {
    %>
        <p>Nom: <%= etudiant.getNom() %></p>
        <p>Prénom: <%= etudiant.getPrenom() %></p>
    <%
        }
    %>
</body>
</html>