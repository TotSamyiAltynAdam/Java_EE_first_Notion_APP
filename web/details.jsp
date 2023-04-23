<%@ page import="kz.bitlab.servlets.db.Task" %>
<%@ page import="kz.bitlab.servlets.db.Course" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="container mt-4 w-75">
        <%
            Task task = (Task) request.getAttribute("foundTask");
            if(task!=null){
                String finish = "Нет";
                if(task.isDone()) finish = "Да";
        %>
                <div class="row">
                    <div class="col-12">
                        <label>Наименование:</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="task_name" readonly value="<%=task.getCourse().getCourse_name()%>">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>Описание:</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="task_description" readonly value="<%=task.getDescription()%>">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>Крайний Срок:</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="task_deadline" readonly value="<%=task.getDeadlineDate()%>">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>Выполнено:</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="task_done" readonly value="<%=finish%>">
                    </div>
                </div>

                <%
                    if(currentUser!=null){
                %>
                        <button type="button" class="btn btn-primary mt-3" data-bs-toggle="modal" data-bs-target="#editTask">
                            Изменить
                        </button>
                        <button type="button" class="btn btn-danger mt-3 ms-3 me-3"
                                data-bs-toggle="modal" data-bs-target="#removeTask">Удалить
                        </button>
                <%
                    }
                %>
                        <a href="/">
                            <button type="button" class="btn btn-secondary mt-3" data-bs-dismiss="modal">Закрыть</button>
                        </a>
                <%
                    if(currentUser!=null) {
                %>
                        <form action="/remove-task" method="post">
                            <input type="hidden" name="task_id" value="<%=task.getId()%>">
                            <div class="modal fade" id="removeTask" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5">Потвердите удаление</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <h5 class="text-center">Вы точно хотите удалить?</h5>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Передумал</button>
                                            <button type="submit" class="btn btn-danger">Удалю</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <div class="modal fade" id="editTask" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5">Изменить задание</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <%@include file="edit.jsp"%>
                                    </div>
                                </div>
                            </div>
                        </div>
                <%
                    }
                %>
        <%
            } else{
        %>
                <h3 class="text-center">
                    Task Not Found
                </h3>
        <%
            }
        %>
    </div>
</body>
</html>
