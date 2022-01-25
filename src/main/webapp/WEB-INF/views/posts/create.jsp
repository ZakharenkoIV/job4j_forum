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
                    <input id="postName" type="text" class="form-control" name="name">
                </div>
                <div class="mb-3">
                    <label for="postDescription" class="form-label"> Описание: </label>
                    <input id="postDescription" type="text" class="form-control" name="description">
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Опубликовать</button>
        </form>
    </div>
</div>
</body>
</html>
