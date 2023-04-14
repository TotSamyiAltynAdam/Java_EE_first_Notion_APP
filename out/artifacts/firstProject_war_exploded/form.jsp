<%@ page import="kz.bitlab.servlets.db.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
                            <option value="<%=course.getCourse_id()%>"><%=course.getCourse_name()%></option>
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
            <textarea type="text" class="form-control" name="task_description" placeholder="Описание..." rows="5"></textarea>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-12">
            <label>Крайний Срок:</label>
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-12">
            <input type="date" class="form-control" name="task_deadline">
        </div>
    </div>