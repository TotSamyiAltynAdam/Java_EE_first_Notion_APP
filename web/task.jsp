<%@ page import="kz.bitlab.servlets.db.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.bitlab.servlets.db.Task" %>
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
                <%
                    if(currentUser!=null && currentUser.getRole() == 1){
                %>
                        <button type="button" class="btn btn-sm btn-dark" data-bs-toggle="modal" data-bs-target="#addTask" style="background-color: darkblue">
                            + Добавить задание
                        </button>
                        <form action="/add-task" method="post">
                            <div class="modal fade" id="addTask" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Новое задание</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <%@include file="form.jsp"%>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                            <button type="submit" class="btn btn-primary">Добавить</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                <%
                    }
                %>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Наименование</th>
                            <th>Крайнии срок</th>
                            <th>Выполнено</th>
                            <th style="width: 10%">Детали</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Task> taskList = (List<Task>) request.getAttribute("tasks");
                            if(taskList != null){
                                for(Task task : taskList){
                                    String finish = "Нет";
                                    if(task.isDone()) finish = "Да";
                        %>
                                    <tr>
                                        <td><%=task.getId()%></td>
                                        <td><%=task.getCourse().getCourse_name()%></td>
                                        <td><%=task.getDeadlineDate()%></td>
                                        <td><%=finish%></td>
                                        <td>
                                            <a class="btn btn-dark btn-sm"
                                               style="background-color: darkblue"
                                               href="/details?task_id=<%=task.getId()%>">Детали</a>
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
