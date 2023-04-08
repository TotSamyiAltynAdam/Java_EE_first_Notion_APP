<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: darkblue">
            <div class="container-fluid">
                <a class="navbar-brand" href="/"><%=siteName%></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="/sort-task-page">Все задания по типу</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</body>
</html>
