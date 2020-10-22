package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.Post;
import javaee.db.Request;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            User user = DBManager.getUser(Integer.parseInt(request.getParameter("id")));

            ArrayList<Request> requests = DBManager.getRequests(currentUser.getId());

            ArrayList<User> friends = DBManager.getFriends(currentUser.getId());
            request.setAttribute("requests", requests);
            request.setAttribute("friends", friends);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
