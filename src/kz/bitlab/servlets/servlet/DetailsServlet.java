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
import java.util.List;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = -1L;
        try {
            id = Long.valueOf(request.getParameter("task_id"));
        } catch (Exception e){
        }
        Task task = DBConnection.getTask(id);
        request.setAttribute("foundTask",task);

        List<Course> courses = DBConnection.getCourses();
        request.setAttribute("courses",courses);

        request.getRequestDispatcher("/details.jsp").forward(request,response);
    }
}
