<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<header class="card-header row">
    <c:if test="${authUser != null}">
        <div class="mx-3">
            <a href="<c:url value="/"/>" class="text-decoration-none text-reset" title="На главную">
<%--                <button type="button" class="btn bg-transparent" title="На главную">--%>
                    <img src="<c:url value="/image/home.svg"/>" alt="">
<%--                </button>--%>
            </a>
                ${authUser}
            |
            <a href="<c:url value="/logout"/>">Выход</a>
        </div>
    </c:if>
    <c:if test="${authUser == null}">
        <div class="mx-3">
            <a href="<c:url value="/login"/>">Вход</a>
            или
            <a href="<c:url value="/reg"/>">Регистрация</a>
        </div>
    </c:if>
</header>