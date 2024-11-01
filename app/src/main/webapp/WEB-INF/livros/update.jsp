<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Livro</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <h1>Editar Livro</h1>
        <form action="/livros/update" method="post">
            <input type="hidden" name="id" value="${livro.id}" />
            <div class="mb-3">
                <label class="form-label">Título</label>
                <input type="text" class="form-control" name="titulo" value="${livro.titulo}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Gênero</label>
                <select name="genero" class="form-select" required>
                    <c:forEach var="g" items="${generos}">
                        <option value="${g.id}" ${livro.genero.id == g.id ? "selected" : ""}>${g.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Editora</label>
                <select name="editora" class="form-select" required>
                    <c:forEach var="e" items="${editoras}">
                        <option value="${e.id}" ${livro.editora.id == e.id ? "selected" : ""}>${e.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Autor(es)</label>
                <c:forEach var="a" items="${autores}">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="autores" value="${a.id}" 
                            ${livro.autores.contains(a) ? "checked" : ""} />
                        <label class="form-check-label">${a.nome}</label>
                    </div>
                </c:forEach>
            </div>
            <button type="submit" class="btn btn-success">Salvar</button>
        </form>
    </div>
</body>
</html>
