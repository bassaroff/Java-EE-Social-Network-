<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.Post" %>
<%@ page import="javaee.db.Request" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
</head>
<% String theme = (String) request.getAttribute("theme"); %>
<body class="<%=theme%>">
    <%@include file="assets/topnav.jsp"%>
    <div class="container">
        <div class="row mt-4">
            <%
               User user = (User)request.getAttribute("user");
               ArrayList<Request> requests = (ArrayList<Request>)request.getAttribute("requests");
               ArrayList<User> friends = (ArrayList<User>)request.getAttribute("friends");

            %>
            <div class="col-sm-3">
                <div class="card">
                    <img class="card-img-top" src="<%=user.getPictureURL()%>" alt="Card image cap">
                    <div class="card-body" style="margin-bottom: -15px">
                        <%

                            Request request1 = null;
                            boolean exist = false;
                            for(Request req: requests) {
                                
                                if (req.getId_user() == currentUser.getId() && req.getId_sender() == user.getId()) {
                                    exist = true;

                        %>
                        <form method="post" action="/deleteRequest">
                            <input type="hidden" name="id_request" value="<%=req.getId()%>">
                            <input type="hidden" name="id" value="<%=user.getId()%>">
                            <button class="btn btn-danger btn-block">Delete request</button>
                        </form>

                        <%
                                }
                            }
                            if(!exist){
                                for(User friend: friends){
                                    if(friend.getId()==user.getId()){


                        %>

                        <div class="mb-2">
                            <button class="btn btn-success btn-block" data-toggle="modal" data-target="#send_message">Send Message</button>

                            <div class="modal fade" id="send_message" tabindex="-1" aria-labelledby="SendMessageModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="SendMessageModalLabel">Send Message to <%=friend.getFullName()%>?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="/sendMessage" method="post">
                                            <div class="modal-body">
                                                <textarea name="message_text" cols="30" rows="5" class="form-control" required></textarea>
                                            </div>
                                            <div class="modal-footer">
                                                <input type="hidden" name="chat_id" value="0">
                                                <input type="hidden" name="id_user" value="<%=friend.getId()%>">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                <button type="submit" class="btn btn-primary">SEND</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <form method="post" class="d-flex justify-content-center" action="/deleteFriend">
                                <input type="hidden" name="id_user" value="<%=user.getId()%>">
                                <button class="btn btn-secondary btn-block">Delete friend</button>
                            </form>
                        <%
                                    }else{

                        %>
                            <form method="post" class="d-flex justify-content-center" action="/addRequest">
                                <input type="hidden" name="id_user" value="<%=user.getId()%>">
                                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">

                                <button class="btn btn-primary btn-block">Send request</button>
                            </form>
                        <%
                                    }
                                }
                            }
                        %>

                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="card mb-4">
                    <div class="card-header">
                        <h4><%=user.getFullName()%></h4>

                    </div>
                    <div class="card-body">
                        <blockquote class="blockquote mb-0">
                            <p>Info: </p>

                            <p>Age: <%=user.getAge()%></p>
                            <p>Birthdate: <%=user.getBirthdate()%></p>
                            <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
                        </blockquote>
                    </div>
                </div>

                <%
                    ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
                    if (posts != null) {
                        for (Post post : posts) {

                %>

                    <div class="card w-100 mt-4">
                        <div class="card-body">
                            <h5 class="card-title"><%=post.getTitle()%></h5>
                            <p class="card-text"><%=post.getShortContent()%></p>
                            <a href="/details?post_id=<%=post.getId()%>" class="btn btn-outline-primary">Read More</a>
                        </div>
                        <div class="card-footer text-muted">
                            Posted on <%=post.getPostDate().getDate() + "." + (post.getPostDate().getMonth()+1) + "." + (post.getPostDate().getYear() + 1900)%> by <b style="color: #031c6e"><%=post.getUser().getFullName()%></b>
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
    </div>
</body>
</html>
