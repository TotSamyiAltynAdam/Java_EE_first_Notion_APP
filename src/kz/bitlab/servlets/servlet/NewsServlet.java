package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.Course;
import kz.bitlab.servlets.db.DBConnection;
import kz.bitlab.servlets.db.News;
import kz.bitlab.servlets.db.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/news")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<News> news = DBConnection.getNews();
        request.setAttribute("news",news);
        request.getRequestDispatcher("/news.jsp").forward(request,response);
    }
}
