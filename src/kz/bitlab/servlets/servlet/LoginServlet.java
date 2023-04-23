package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.Course;
import kz.bitlab.servlets.db.DBConnection;
import kz.bitlab.servlets.db.Task;
import kz.bitlab.servlets.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = DBConnection.getUser(email);
        if(user!=null && user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("currentUser",user);
            response.sendRedirect("/profile");
        }else{
            response.sendRedirect("/login?error");
        }
    }
}
