package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.DBConnection;
import kz.bitlab.servlets.db.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/sort-task-page")
public class SortTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> taskArrayList = DBConnection.getTasks();
        request.setAttribute("tasks",taskArrayList);
        request.getRequestDispatcher("/sortTaskPage.jsp").forward(request,response);
    }
}
