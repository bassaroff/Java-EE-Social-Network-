<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.Post" %>
<%@ page import="javaee.db.Chat" %>
<%@ page import="javaee.db.Message" %>
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
                ArrayList<Chat> chats = (ArrayList<Chat>)request.getAttribute("chats");
                if(chats.size()>0){
        %>
        <div class="col-sm-12 row mt-4">
            <div class="w-100 shadow-none p-3 mb-5 bg-light rounded">
                <h3 class=" text-center ">Messaging</h3>
            </div>

                <div class="messaging">
                    <div class="inbox_msg">
                        <div class="inbox_people">
                            <div class="headind_srch">
                                <div class="recent_heading">
                                    <h4>Friends</h4>
                                </div>
                                <div class="srch_bar">
                                    <div class="stylish-input-group">
                                        <input type="text" class="search-bar"  placeholder="Search" >
                                        <span class="input-group-addon">
                                            <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                                        </span> </div>
                                </div>
                            </div>

                            <%

                                ArrayList<User> friends = (ArrayList<User>)request.getAttribute("friends");
                                for(Chat chat: chats){
                                    for(User friend: friends){
                                        if(chat.getId_friend()==friend.getId() || chat.getId_user()==friend.getId()){
                            %>

                            <div class="inbox_chat">
                                <div class="chat_list active_chat">
                                    <div class="chat_people">
                                        <div class="chat_img"> <img class="rounded-circle" src="<%=friend.getPictureURL()%>" alt="sunil"> </div>
                                        <div class="chat_ib">
                                            <a href="/getChatMessages?chat=<%=chat.getId()%>&friend=<%=friend.getId()%>"><h5><%=friend.getFullName()%></a> <span class="chat_date"><%=chat.getLatest_message_time().toLocalDateTime().getDayOfMonth() + " " + chat.getLatest_message_time().toLocalDateTime().getMonth()%></span></h5>
                                            <p><%=chat.getLatest_message_text()%></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%
                                        }
                                    }
                                }
                            %>
                        </div>
                        <div class="mesgs">
                            <div class="msg_history">
<%--                                <%--%>
<%--                                    ArrayList<Message> messages = (ArrayList<Message>)request.getAttribute("messagesByChat");--%>

<%--                                    for(Message message:messages){--%>

<%--                                        if(message.getId_sender()==currentUser.getId()){--%>
<%--                                %>--%>
<%--                                <div class="outgoing_msg">--%>
<%--                                    <div class="sent_msg">--%>
<%--                                        <p><%=message.getMessage()%></p>--%>
<%--                                        <span class="time_date"><%=message.getSended_time().toLocalDateTime().getHour() + ":" +--%>
<%--                                                "" + message.getSended_time().toLocalDateTime().getMinute()%>   |--%>
<%--                                            <%=message.getSended_time().toLocalDateTime().getDayOfMonth() + " " +--%>
<%--                                                    "" + message.getSended_time().toLocalDateTime().getMonth() %></span> </div>--%>
<%--                                </div>--%>
<%--                                <%--%>
<%--                                        }else{--%>
<%--                                %>--%>
<%--                                <div class="incoming_msg">--%>
<%--                                    <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>--%>
<%--                                    <div class="received_msg">--%>
<%--                                        <div class="received_withd_msg">--%>
<%--                                            <p><%=message.getMessage()%></p>--%>
<%--                                            <span class="time_date"><%=message.getSended_time().toLocalDateTime().getHour() + ":" +--%>
<%--                                                    "" + message.getSended_time().toLocalDateTime().getMinute()%>   |--%>
<%--                                            <%=message.getSended_time().toLocalDateTime().getDayOfMonth() + " " +--%>
<%--                                                    "" + message.getSended_time().toLocalDateTime().getMonth() %></span></span></div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <%--%>
<%--                                        }--%>
<%--                                    }--%>
<%--                                %>--%>

                            </div>
<%--                            <div class="type_msg">--%>
<%--                                <div class="input_msg_write">--%>
<%--                                    <input type="text" class="write_msg" placeholder="Type a message" />--%>
<%--                                    <button class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
                    </div>




                </div>

        </div>
        <%
            }else {
        %>
        <h2 class="text-center mt-5">You have no friends, add friend to send message!</h2>
        <%
                }

            }
        %>
    </div>


<%--    <%@include file="assets/footer.jsp"%>--%>
</body>
</html>
