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

    <title>${post.name}</title>
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<body>
<div class="container mt-3">
    <div class="row">
        <h4 class="text-center">${post.name}</h4>
    </div>
    <table>
        <tr>
            <td class="row2" valign="top" width="99%">
                <div><span>Дата публикации: ${post.created.getTime()}
                    <br>Сообщение #1 </span>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <i><span style="font-size:14pt;line-height:100%">
                        ${post.description}
                    </span></i>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
