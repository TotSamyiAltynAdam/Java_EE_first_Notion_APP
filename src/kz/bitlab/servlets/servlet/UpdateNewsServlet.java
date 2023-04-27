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

@WebServlet(value="/save-news")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Long id = Long.parseLong(request.getParameter("id"));

            News news = DBConnection.getNewsByID(id);
            if(news!=null) {
                news.setTitle(title);
                news.setContent(content);

                DBConnection.updateNews(news);
                response.sendRedirect("/news-details?id="+id);
            }else{
                response.sendRedirect("/");
            }
        }else{
            response.sendRedirect("/login");
        }
    }

}