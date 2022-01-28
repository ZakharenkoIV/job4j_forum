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

    <title>${topic}</title>
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<body>
<div class="container mt-3">
    <div class="row">
        <h4 class="text-center mt-3 mb-5">${topic}</h4>
    </div>

    <c:forEach items="${posts}" var="post">
        <div>
            <i><span style="font-size:14pt;line-height:100%"> ${post.comment} </span></i>
        </div>
        <div class="mt-3"><span style="font-size:10pt; line-height: 0.9em;">
            <p><small> Дата публикации: ${post.created.getTime()}<br>
            Сообщение #${post.orderOfAddition}<br>
            От ${post.user.username}</small></p> </span>
        </div>
        <div class="row justify-content-end">
            <c:if test="${userId == post.user.id || isAdmin}">
                <div class="col-auto">
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
                </div>
            </c:if>
        </div>
        <hr class="mt-3 mb-3"/>
    </c:forEach>

    <form name='message' action="<c:url value='/posts/save'/>" method='POST'>
        <div class="form-group">
            <label for="formMessage"></label>
            <textarea class="form-control" id="formMessage" rows="3" name="comment"
                      placeholder="Напишите ваш комментарий"></textarea>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
                <button class="mt-3" type="submit">Опубликовать</button>
            </div>
        </div>
        <input type="hidden" name="topic" value="${topic}"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>
