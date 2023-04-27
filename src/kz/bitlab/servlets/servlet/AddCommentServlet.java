package kz.bitlab.servlets.servlet;

import kz.bitlab.servlets.db.Comment;
import kz.bitlab.servlets.db.DBConnection;
import kz.bitlab.servlets.db.News;
import kz.bitlab.servlets.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null){
            String commentText = request.getParameter("comment");
            Long newsId = Long.parseLong(request.getParameter("news_id"));
            News news = DBConnection.getNewsByID(newsId);
            if(news!=null){
                Comment comment = new Comment();
                comment.setNews(news);
                comment.setUser(user);
                comment.setComment(commentText);

                DBConnection.addComment(comment);
                response.sendRedirect("/news-details?id="+newsId);
            }else{
                response.sendRedirect("/");
            }

        }else{
            response.sendRedirect("/login");
        }
    }

}