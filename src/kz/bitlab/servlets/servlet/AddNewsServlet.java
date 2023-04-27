package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.DBConnection;
import kz.bitlab.servlets.db.News;
import kz.bitlab.servlets.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/add-news-page")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null){
            request.getRequestDispatcher("/addnews.jsp").forward(request, response);
        }else {
            response.sendRedirect("/login");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null){
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setUser(user);

            DBConnection.addNews(news);
            response.sendRedirect("/add-news-page");
        }else{
            response.sendRedirect("/login");
        }
    }

}