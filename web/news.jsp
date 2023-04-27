<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.servlets.db.News" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="container mt-3">
        <div class="row mt-3">
            <div class="col-12">
                <%
                    ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                    if(news!=null){
                        for(News n : news){
                %>
                        <div class="p-3 mb-3" style="background-color: #DEE1DF;">
                            <a href="/news-details?id=<%=n.getId()%>"><h3><%=n.getTitle()%></h3></a>
                            <p><%=n.getContent()%></p>
                            <p>
                                Posted by <strong><%=n.getUser().getFullName()%></strong>
                                At <strong><%=n.getPostDate()%></strong>
                            </p>
                        </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
