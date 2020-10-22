package javaee.servlets;

import javaee.db.Chat;
import javaee.db.DBManager;
import javaee.db.Message;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/messages")
public class MessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current = (User)request.getSession().getAttribute("CURRENT_USER");

        ArrayList<Chat> chats = DBManager.getChats(current.getId());
        ArrayList<User> friends = DBManager.getFriends(current.getId());

        request.setAttribute("chats", chats);
        request.setAttribute("friends", friends);
        request.getRequestDispatcher("/messages.jsp").forward(request, response);
    }
}
