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
                    News news = (News) request.getAttribute("news");
                    if(news!=null){
                %>
                        <div class="p-3 mb-3" style="background-color: #DEE1DF;">
                            <h3><%=news.getTitle()%></h3>
                            <p><%=news.getContent()%></p>
                            <p>
                                Posted by <strong><%=news.getUser().getFullName()%></strong>
                                At <strong><%=news.getPostDate()%></strong>
                            </p>
                        </div>
                        <%
                            if(currentUser!=null && currentUser.getId().equals(news.getUser().getId())){
                        %>
                            <div>
                                <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editNews">
                                    EDIT
                                </button>
                                <div class="modal fade" id="editNews" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <form action="/save-news" method="post">
                                                <input type="hidden" name="id" value="<%=news.getId()%>">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit News</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <label>
                                                                TITLE :
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <div class="col-12">
                                                            <input type="text" class="form-control" name="title" placeholder="Insert title:" required value="<%=news.getTitle()%>">
                                                        </div>
                                                    </div>

                                                    <div class="row mt-3">
                                                        <div class="col-12">
                                                            <label>
                                                                CONTENT :
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <div class="col-12">
                                                            <textarea class="form-control" name="content" placeholder="Insert content: " required rows="10"><%=news.getContent()%></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                    <button type="submit" class="btn btn-success">Update</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <%
                            }
                        %>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
