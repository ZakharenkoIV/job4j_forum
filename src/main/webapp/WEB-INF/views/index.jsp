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

    <title>Форум job4j</title>
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<body>
<div class="container mt-3">
    <div class="row">
        <h4 class="text-center">Форум job4j</h4>
    </div>
    <div class="row">
        <div class="text-end">
            <form action="<c:url value="/posts/create"/>" method="get">
                <button type="submit">Добавить тему</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Тема</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td>
                        <a href="<c:url value="/posts/${post.id}"/>">
                            <c:out value="${post.name}"/>
                        </a>
                    </td>
                    <td width=125px>
                        <c:if test="${userId == post.user.id || isAdmin}">
                            <button title="Редактировать">
                                <a href="<c:url value="/posts/${post.id}/edit"/>">
                                    <img src="<c:url value="/image/edit.svg"/>" alt="">
                                </a>
                            </button>
                            <button title="Удалить">
                                <a href="<c:url value="/posts/${post.id}/delete"/>">
                                    <img src="<c:url value="/image/x.svg"/>" alt="">
                                </a>
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>