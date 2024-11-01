<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Autores</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <%@ include file="../_template/menubar.jsp" %>
    <div class="container">
        <h1>Autores</h1>
        <a href="/autores/insert" class="btn btn-primary">Novo autor</a>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${autores}">
                    <tr>
                        <td>${a.id}</td>
                        <td>${a.nome}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
