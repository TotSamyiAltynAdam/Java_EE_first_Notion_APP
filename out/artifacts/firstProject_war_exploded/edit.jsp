<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="container mt-4 w-75">
        <form action="/edit-task" method="post">
            <input type="hidden" name="task_id" value="<%=task.getId()%>">
            <div class="row">
                <div class="col-12">
                    <label>Наименование:</label>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-12">
                    <select class="form-control" name="task_name">
                        <%
                            ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
                            if(courses!=null){
                                for(Course course: courses){
                        %>
                        <option <%=(course.getCourse_id().equals(task.getCourse().getCourse_id())?"selected":"")%>
                                value="<%=course.getCourse_id()%>"><%=course.getCourse_name()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <label>Описание:</label>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-12">
                    <input type="text" class="form-control" name="task_description" value="<%=task.getDescription()%>">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <label>Крайний Срок:</label>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-12">
                    <input type="text" class="form-control" name="task_deadline" value="<%=task.getDeadlineDate()%>">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <label>Выполнено:</label>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-12">
                    <select class="form-select" aria-label="Default select example" name="task_done">
                        <option value="true" <%=(task.isDone() ?"selected":"")%> >Да</option>
                        <option value="false" <%=(!task.isDone() ?"selected":"")%> >Нет</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-success mt-3">Сохранить</button>
            <button type="button" class="btn btn-secondary mt-3 ms-3" data-bs-dismiss="modal">Закрыть</button>
        </form>
    </div>
</body>
</html>
