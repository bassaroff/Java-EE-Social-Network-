package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.Request;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/friends")
public class FriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = DBManager.getUsers();

        User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
        ArrayList<Request> requests = DBManager.getRequests(current_user.getId());
        ArrayList<Request> requestsBySender = DBManager.getRequestsBySender(current_user.getId());
        ArrayList<User> friends = DBManager.getFriends(current_user.getId());
        request.setAttribute("friends",friends);

        request.setAttribute("requests",requests);
        request.setAttribute("requestsBySender", requestsBySender);
        request.setAttribute("users", users);

        request.getRequestDispatcher("/friends.jsp").forward(request, response);
    }
}
