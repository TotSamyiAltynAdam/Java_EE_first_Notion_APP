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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Task> tasks = DBConnection.getTasks();
        request.setAttribute("tasks", tasks);

        List<Course> courses = DBConnection.getCourses();
        request.setAttribute("courses",courses);

        request.getRequestDispatcher("/task.jsp").forward(request,response);
    }
}
