package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.Course;
import kz.bitlab.servlets.db.DBConnection;
import kz.bitlab.servlets.db.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit-task")
public class EditTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("task_id"));
        Long course_id = Long.parseLong(request.getParameter("task_name"));
        String description = request.getParameter("task_description");
        String deadline = request.getParameter("task_deadline");

        boolean finish = false;
        if(request.getParameter("task_done").equals("true")) finish=true;

        Task task = DBConnection.getTask(id);
        Course course = DBConnection.getCourse(course_id);
        if(task!=null && course!=null){
            task.setCourse(course);
            task.setDescription(description);
            task.setDeadlineDate(deadline);
            task.setDone(finish);

            DBConnection.updateTask(task);
            response.sendRedirect("/details?task_id="+id);
        }else{
            response.sendRedirect("/");
        }
    }
}
