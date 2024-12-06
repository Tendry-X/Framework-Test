<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.test.model.Etudiant" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultat</title>
</head>
<body>
    <h1>Formulaire soumis !</h1>
    <%
        Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
        if (etudiant != null) {
    %>
        <p>Nom: <%= etudiant.getNom() %></p>
        <p>Prénom: <%= etudiant.getPrenom() %></p>
    <%
        }
    %>
    <a href="/framework-test/form">Retour</a>
</body>
</html>