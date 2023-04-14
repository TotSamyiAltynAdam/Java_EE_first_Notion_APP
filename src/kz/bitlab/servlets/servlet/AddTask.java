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

@WebServlet(value = "/add-task")
public class AddTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long course_id = Long.parseLong(request.getParameter("task_name"));
        String description = request.getParameter("task_description");
        String deadline = request.getParameter("task_deadline");
        Course course = DBConnection.getCourse(course_id);
        if(course!=null) {
            Task task = new Task(
                    course,
                    description,
                    deadline,
                    false
            );
            DBConnection.addTask(task);
        }

        response.sendRedirect("/");
    }
}
