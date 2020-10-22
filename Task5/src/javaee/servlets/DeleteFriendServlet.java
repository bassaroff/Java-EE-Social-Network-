package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteFriend")
public class DeleteFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_friend = Integer.parseInt(request.getParameter("id_user"));
        User current = (User)request.getSession().getAttribute("CURRENT_USER");
        DBManager.deleteFriend(current.getId(),id_friend);

        if(request.getParameter("id")==null) {
            response.sendRedirect("/friends");
        }else
            response.sendRedirect("/profile?id="+request.getParameter("id"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
