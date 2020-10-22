<style><%@include file="/assets/style.css"%></style>
<%@include file="user.jsp"%>
<%--<nav class="navbar navbar-expand-lg navbar-dark sticky-top <%=theme%>">--%>
<nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: #031c6e;">
    <div class="container justify-content-between">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><img src="https://img.pngio.com/avengers-logo-png-transparent-avengers-logopng-images-pluspng-logo-a-png-1376_1600.png" alt="logo" width="30" height="30" style="filter: invert(100%); margin-right: 10px;">Aralasu.kz</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#publications" aria-controls="publications" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <%
                    if (!ONLINE) {
                %>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/register" class="nav-link">Register</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/login" class="nav-link">Login</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/faq" class="nav-link">FAQ</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/about" class="nav-link">About</a></li>
                <%
                    }
                    else {
                %>
<%--                <li class="nav-item"><a href="${pageContext.request.contextPath}/profile" class="nav-link"><%=currentUser.getFullName()%></a></li>--%>
<%--                <li class="nav-item"><a href="${pageContext.request.contextPath}/logout" class="nav-link">Log Out</a></li>--%>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/" class="nav-link"><svg width="1.5em" height="1em" viewBox="0 0 16 16" class="bi bi-rss-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm1.5 2.5a1 1 0 0 0 0 2 8 8 0 0 1 8 8 1 1 0 1 0 2 0c0-5.523-4.477-10-10-10zm0 4a1 1 0 0 0 0 2 4 4 0 0 1 4 4 1 1 0 1 0 2 0 6 6 0 0 0-6-6zm.5 7a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
                </svg>Feed</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/friends" class="nav-link"><svg width="1.5em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                </svg>Friends</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/groups" class="nav-link"><svg width="1.5em" height="1em" viewBox="0 0 16 16" class="bi bi-people-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5.784 6A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216zM4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
                </svg>Groups</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/my_posts" class="nav-link"><svg width="1.5em" height="1em" viewBox="0 0 16 16" class="bi bi-file-post-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M12 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zM4.5 3a.5.5 0 0 0 0 1h5a.5.5 0 0 0 0-1h-5zm0 2a.5.5 0 0 0-.5.5v8a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 .5-.5v-8a.5.5 0 0 0-.5-.5h-7z"/>
                </svg>My Posts</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/messages" class="nav-link"><svg width="1.5em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-right-dots-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M16 2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h9.586a1 1 0 0 1 .707.293l2.853 2.853a.5.5 0 0 0 .854-.353V2zM5 6a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                </svg>Messages</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/pictures" class="nav-link"><svg width="1.5em" height="1em" viewBox="0 0 17 16" class="bi bi-image" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M14.002 2h-12a1 1 0 0 0-1 1v9l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094L15.002 9.5V3a1 1 0 0 0-1-1zm-12-1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm4 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                </svg>Pictures</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/videos" class="nav-link"><svg width="1.5em" height="1em" viewBox="0 0 16 16" class="bi bi-film" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0h8v6H4V1zm8 8H4v6h8V9zM1 1h2v2H1V1zm2 3H1v2h2V4zM1 7h2v2H1V7zm2 3H1v2h2v-2zm-2 3h2v2H1v-2zM15 1h-2v2h2V1zm-2 3h2v2h-2V4zm2 3h-2v2h2V7zm-2 3h2v2h-2v-2zm2 3h-2v2h2v-2z"/>
                </svg>Videos</a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>

</nav>
