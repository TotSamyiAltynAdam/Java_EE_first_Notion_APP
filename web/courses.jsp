<%@ page import="kz.bitlab.servlets.db.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col-12">
            <button type="button" class="btn btn-sm btn-dark" data-bs-toggle="modal" data-bs-target="#addCourse" style="background-color: darkblue">
                + Добавить курсы
            </button>
            <form action="/add-course" method="post">
                <div class="modal fade" id="addCourse" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Новое задание</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-12">
                                        <label>Наименование курса:</label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <input type="text" class="form-control" name="course_name">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                <button type="submit" class="btn btn-primary">Добавить</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-12">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Наименование</th>
                    <th style="width: 10%">Детали</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Course> courseList = (List<Course>) request.getAttribute("courses");
                    if(courseList != null){
                        for(Course course : courseList){
                %>
                            <tr>
                                <td><%=course.getCourse_id()%></td>
                                <td><%=course.getCourse_name()%></td>
                                <td>
                                    <a class="btn btn-dark btn-sm"
                                       style="background-color: darkblue"
                                       href="/">Детали</a>
                                </td>
                            </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
