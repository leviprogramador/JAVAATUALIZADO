<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Livros</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <%@ include file="../_template/menubar.jsp" %>
    <div class="container">
        <h1>Livros</h1>
        <a href="/livros/insert" class="btn btn-primary mb-3">Novo Livro</a>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Gênero</th>
                    <th>Editora</th>
                    <th>Autores</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="l" items="${livros}">
                    <tr>
                        <td>${l.id}</td>
                        <td>${l.titulo}</td>
                        <td>${l.genero.nome}</td>
                        <td>${l.editora.nome}</td>
                        <td>
                            <c:forEach var="a" items="${l.autores}">
                                ${a.nome},
                            </c:forEach>
                        </td>
                        <td>
                            <a href="/livros/update?id=${l.id}" class="btn btn-warning">Editar</a>
                            <a href="/livros/delete?id=${l.id}" class="btn btn-danger">Remover</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
