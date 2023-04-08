package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.DBManager;
import kz.bitlab.servlets.db.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = -1L;
        try {
            id = Long.valueOf(request.getParameter("task_id"));
        } catch (Exception e){
        }
        Task task = DBManager.getTask(id);
        request.setAttribute("foundTask",task);
        request.getRequestDispatcher("/details.jsp").forward(request,response);
    }
}
