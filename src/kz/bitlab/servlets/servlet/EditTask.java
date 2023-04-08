package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.DBManager;
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
        String name = request.getParameter("task_name");
        String description = request.getParameter("task_description");
        String deadline = request.getParameter("task_deadline");

        boolean finish = false;
        if(request.getParameter("task_done").equals("true")) finish=true;

        Task task = DBManager.getTask(id);
        if(task!=null){
            task.setName(name);
            task.setDescription(description);
            task.setDeadlineDate(deadline);
            task.setDone(finish);
            DBManager.updateTask(task);
            response.sendRedirect("/details?task_id="+id);
        }else{
            response.sendRedirect("/");
        }
    }
}
