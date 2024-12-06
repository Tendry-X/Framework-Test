<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sprint 7 - Formulaire</title>
</head>
<body>
    <h1>Sprint 7 : GET/POST distincts</h1>
    <form method="POST" action="/framework-test/form">
        <label>Nom: <input type="text" name="nom" required></label><br>
        <label>Prénom: <input type="text" name="prenom" required></label><br>
        <button type="submit">Envoyer</button>
    </form>
</body>
</html>