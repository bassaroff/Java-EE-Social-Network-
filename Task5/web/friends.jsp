<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.Post" %>
<%@ page import="javaee.db.Request" %>
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
            ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
            ArrayList<Request> requests = (ArrayList<Request>)request.getAttribute("requests");
            ArrayList<Request> requestsBySender = (ArrayList<Request>)request.getAttribute("requestsBySender");
            ArrayList<User> friends = (ArrayList<User>)request.getAttribute("friends");

            ArrayList<User> sUsers = new ArrayList<>();
            if(requestsBySender!=null && requestsBySender.size()>0){
        %>
        <div class="row my-3 shadow-none p-3 mb-2 bg-light rounded">
            <h2>Want friend with you:</h2>
        </div>
        <%

            for(Request req: requestsBySender){
                for (User user: users){
                    if(req.getId_sender()==currentUser.getId()){
                        if(req.getId_user()==user.getId()){
                            sUsers.add(user);
        %>

            <div class="row shadow-sm py-3">
                <div class="col-sm-12 d-flex align-items-center">
                    <div class="col-sm-2">
                        <img src="<%=user.getPictureURL()%>" class="rounded-circle" width="60px">
                    </div>

                    <div class="col-sm-8">
                        <h4>
                            <a href="/profile?id=<%=user.getId()%>">
                                <%=user.getFullName()%>
                            </a>

                        </h4>
                    </div>

                    <div class="col-sm-2">
                        <form method="post" action="/addFriend">
                            <input type="hidden" name="id_user" value="<%=user.getId()%>">
                            <input type="hidden" name="id_request" value="<%=req.getId()%>">
                            <button class="btn btn-success">Add friend</button>
                        </form>

                        <form method="post" action="/deleteRequest">
                            <input type="hidden" name="id_request" value="<%=req.getId()%>">
                            <button class="btn btn-danger">Delete request</button>
                        </form>
                    </div>
                </div>
            </div>
        <%
                            }
                        }
                    }
                }
            }

        %>



            <%

                if(requests!=null && requests.size()>0){

            %>
                <div class="row my-3 shadow-none p-3 mb-2 mt-5 bg-light rounded">
                    <h2>Requests:</h2>
                </div>
            <%
                    for(Request req: requests){
                        for(User u: users){
                            if(req.getId_user()== currentUser.getId()){
                                if(req.getId_sender()==u.getId()){
                                    sUsers.add(u);

            %>

                <div class="row shadow-sm py-3">
        <div class="col-sm-12 d-flex align-items-center">
            <div class="col-sm-2">
                <img src="<%=u.getPictureURL()%>" class="rounded-circle" width="60px">
            </div>

            <div class="col-sm-8">
                <h4>
                    <a href="/profile?id=<%=u.getId()%>">
                        <%=u.getFullName()%>
                    </a>
                </h4>
            </div>

            <div class="col-sm-2">
                <form method="post" action="/deleteRequest">
                    <input type="hidden" name="id_request" value="<%=req.getId()%>">
                    <button class="btn btn-danger">Delete request</button>
                </form>
            </div>
        </div>
    </div>
            <%

                                }
                            }
                        }
                    }
                }
            %>




        <div class="row my-3 shadow-none p-3 mb-2 mt-5 bg-light rounded">
            <h2 >Can be your friends:</h2>
        </div>
        <%
            for(User u: friends){
                sUsers.add(u);
            }

            for (User u: users){
                boolean exist = false;
                if(u.getId()!=currentUser.getId()){
                    for (User su:sUsers){
                        if(su.getId()==u.getId()){
                            exist = true;
                        }
                    }
                    if(!exist){
        %>
            <div class="row shadow-sm py-3">
                <div class="col-sm-12 d-flex align-items-center">
                    <div class="col-sm-2">
                        <img src="<%=u.getPictureURL()%>" class="rounded-circle" width="60px">
                    </div>

                    <div class="col-sm-8">
                        <h4>
                            <a href="/profile?id=<%=u.getId()%>">
                                <%=u.getFullName()%>
                            </a>

                        </h4>
                    </div>

                    <div class="col-sm-2">
                        <form method="post" action="/addRequest">
                            <input type="hidden" name="id_user" value="<%=u.getId()%>">
                            <button class="btn btn-primary">Send request</button>
                        </form>
                    </div>
                </div>
            </div>
        <%
                    }
                }
            }
        %>
    <%
        if(friends!=null && friends.size()>0){
    %>
    <div class="row my-3 shadow-none p-3 mb-2 mt-5 bg-light rounded">
        <h2 >Your friends:</h2>
    </div>
    <%


        for (User u: friends){

    %>
    <div class="row shadow-sm py-3">
        <div class="col-sm-12 d-flex align-items-center">
            <div class="col-sm-2">
                <img src="<%=u.getPictureURL()%>" class="rounded-circle" width="60px">
            </div>

            <div class="col-sm-6">
                <h4>
                    <a href="/profile?id=<%=u.getId()%>">
                        <%=u.getFullName()%>
                    </a>

                </h4>
            </div>

            <div class="col-sm-4 d-flex justify-content-between">
                <div>
                    <button class="btn btn-success" data-toggle="modal" data-target="#send_message">Send Message</button>

                    <div class="modal fade" id="send_message" tabindex="-1" aria-labelledby="SendMessageModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="SendMessageModalLabel">Send Message to <%=u.getFullName()%>?</h5>
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
                                        <input type="hidden" name="id_user" value="<%=u.getId()%>">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                        <button type="submit" class="btn btn-primary">SEND</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>


                <form method="post" action="/deleteFriend">
                    <input type="hidden" name="id_user" value="<%=u.getId()%>">
                    <button class="btn btn-secondary">Delete friend</button>
                </form>
            </div>
        </div>
    </div>
    <%
            }
        }
    %>



</div>

</body>
</html>
