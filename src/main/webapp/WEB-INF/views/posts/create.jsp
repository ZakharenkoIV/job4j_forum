<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <title>Create Post</title>
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<body>
<div class="container mt-3">
    <div class="row">
        <h4 class="text-center">Новая тема</h4>
    </div>
    <div class="card-body">
        <form action="<c:url value='/posts/save'/>" method="post">
            <div class="form-group">
                <div class="mb-3">
                    <label for="postName" class="form-label"> Название темы: </label>
                    <input id="postName" type="text" class="form-control" name="topic" placeholder="Краткое название темы">
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="formMessage"> Описание: </label>
                <textarea class="form-control" id="formMessage" rows="3" name="comment"
                          placeholder="Напишите подробнее суть обсуждаемой темы"></textarea>
            </div>
            <div class="form-group row">
                <div class="col-sm-10">
                    <button class="mt-3" type="submit">Опубликовать</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
