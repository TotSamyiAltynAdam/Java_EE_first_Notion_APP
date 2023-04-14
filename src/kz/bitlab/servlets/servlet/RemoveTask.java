package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/remove-task")
public class RemoveTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = -1L;
        try {
            id = Long.valueOf(request.getParameter("task_id"));
        }catch (Exception e){
        }
//        DBManager.removeTask(id);
        DBConnection.removeTask(id);
        response.sendRedirect("/");
    }
}
