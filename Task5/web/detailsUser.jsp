<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
    <link href="/assets/all.css " rel="stylesheet">
</head>

<% String theme = (String) request.getAttribute("theme"); %>
<body class="<%=theme%>">
    <%@include file="assets/topnav.jsp"%>
    <div class="container pb-4">
        <%
            if (ONLINE) {
        %>
        <div class="col-sm-12 row mt-4">
            <div class="col-sm-3">
                <div class="row">
                    <img src="<%=currentUser.getPictureURL()%>" class="container-fluid" style="padding-left: 0px;">
                </div>
                <div class="row mt-4">
                    <ul class="list-group col-sm-12">
                        <li class="list-group-item"><h4 class="mt-4"><%=currentUser.getFullName() + ", " + currentUser.getAge()%></h4></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/editProfile"><b>My Profile</b></a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/settings"><b>Add post</b></a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout" style="color: crimson"><b>Log Out</b></a></li>
                    </ul>
                </div>
            </div>



<%--            TODO: MAIN BLOCK NEWS--%>
            <div class="col-sm-6">
                <div class="row">

                <%
                    ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("user_posts");
                    if (posts != null) {
                        for (Post post : posts) {
                %>
                <div class="row mt-4">
                    <div class="card w-100">
                        <div class="card-body">
                            <h5 class="card-title"><%=post.getTitle()%></h5>
                            <p class="card-text"><%=post.getShortContent()%></p>
                            <a href="/details?post_id=<%=post.getId()%>" class="btn btn-outline-primary">Read More</a>
                        </div>
                        <div class="card-footer text-muted">
                            Posted on <%=post.getPostDate().getDate() + "." + (post.getPostDate().getMonth()+1) + "." + (post.getPostDate().getYear() + 1900)%> by <b style="color: #031c6e"><%=post.getUser().getFullName()%></b>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>


            <div class="col-sm-3">
                <div class="mb-4">
                    <div class="card mb-3" style="max-width: 18rem;">
                        <div class="card-header text-white bg-info">Latest birthdays</div>
                        <div class="card-body">
    <%--                        <h5 class="card-title">Info card title</h5>--%>
                            <p class="card-text">Musa Uatbayev, tomorrow</p>
                            <p class="card-text">Azamat Tolegenov, 02 October</p>
                            <p class="card-text">Yerik Utemuratov, 05 October</p>
                            <p class="card-text">Aybek BAgit, 10 October</p>
                        </div>
                    </div>
                </div>
                <div class="">
                    <div class="card mb-3" style="max-width: 18rem;">
                        <div class="card-header text-white bg-info">My Games</div>
                        <div class="card-body">
                            <p class="card-text"><a href="#"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-emoji-expressionless" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path fill-rule="evenodd" d="M4 10.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm5 0a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z"/>
                            </svg> FOOTBALL ONLINE</a></p>
                            <p class="card-text"><a href="#"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-emoji-frown" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path fill-rule="evenodd" d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
                                <path d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
                            </svg> POOL ONLINE</a></p>
                            <p class="card-text"><a href="#"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-emoji-laughing" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path fill-rule="evenodd" d="M12.331 9.5a1 1 0 0 1 0 1A4.998 4.998 0 0 1 8 13a4.998 4.998 0 0 1-4.33-2.5A1 1 0 0 1 4.535 9h6.93a1 1 0 0 1 .866.5z"/>
                                <path d="M7 6.5c0 .828-.448 0-1 0s-1 .828-1 0S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 0-1 0s-1 .828-1 0S9.448 5 10 5s1 .672 1 1.5z"/>
                            </svg> CHESS ONLINE</a></p>
                            <p class="card-text"><a href="#"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-emoji-smile-upside-down" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 1a7 7 0 1 1 0 14A7 7 0 0 1 8 1zm0-1a8 8 0 1 1 0 16A8 8 0 0 1 8 0z"/>
                                <path fill-rule="evenodd" d="M4.285 6.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 4.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 3.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
                                <path d="M7 9.5C7 8.672 6.552 8 6 8s-1 .672-1 1.5.448 1.5 1 1.5 1-.672 1-1.5zm4 0c0-.828-.448-1.5-1-1.5s-1 .672-1 1.5.448 1.5 1 1.5 1-.672 1-1.5z"/>
                            </svg> RACES ONLINE</a></p>
                        </div>
                    </div>
                </div>
            </div>



        </div>
        <%
            }
        %>
    </div>


<%--    <%@include file="assets/footer.jsp"%>--%>
</body>
</html>
