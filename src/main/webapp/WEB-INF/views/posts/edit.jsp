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

    <title>Post edit</title>
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<body>
<div class="container mt-3">
    <div class="row">
        <h4 class="text-center">Редактирование комментария</h4>
        <h5 class="text-center">${post.topic}</h5>
    </div>
    <div class="card-body">
        <form action="<c:url value='/posts/save'/>" method="post">
            <input type="hidden" name="id" value="${post.id}"/>
            <input type="hidden" name="topic" value="${post.topic}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="formMessage"> Комментарий: </label>
                <textarea class="form-control" id="formMessage" rows="3" name="comment"
                          placeholder="Напишите ваш комментарий">
                    ${post.comment}
                </textarea>
            </div>
            <div class="form-group row">
                <div class="col-sm-10">
                    <button class="mt-3" type="submit">Сохранить</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
