package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.Comment;
import kz.bitlab.servlets.db.DBConnection;
import kz.bitlab.servlets.db.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        News news = DBConnection.getNewsByID(id);
        request.setAttribute("news",news);
        if(news!=null) {
            ArrayList<Comment> comments = DBConnection.getComments(id);
            request.setAttribute("comments", comments);
        }

        request.getRequestDispatcher("/newsdetails.jsp").forward(request,response);
    }
}
