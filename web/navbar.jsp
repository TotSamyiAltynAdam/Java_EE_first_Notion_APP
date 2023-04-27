<%@ page import="kz.bitlab.servlets.db.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
%>
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
                        <li class="nav-item">
                            <a class="nav-link" href="/courses">Курсы</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/news">All News</a>
                        </li>
                        <%
                            if(currentUser!=null){
                        %>
                            <li class="nav-item">
                                <a class="nav-link" href="/add-news-page">Add News</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <%=currentUser.getFullName()%>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="/profile">Profile</a></li>
                                    <li><a class="dropdown-item" href="/">Settings</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="/logout">Logout</a></li>
                                </ul>
                            </li>
                        <%
                            }else{
                        %>
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Login</a>
                            </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</body>
</html>
