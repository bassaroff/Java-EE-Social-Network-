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

@WebServlet(value = "/getChatMessages")
public class GetChatMessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_chat = Integer.parseInt(request.getParameter("chat"));
        User current = (User)request.getSession().getAttribute("CURRENT_USER");

        ArrayList<Message> messages = DBManager.getMessagesByChat(id_chat);
        ArrayList<Chat> chats = DBManager.getChats(current.getId());
        ArrayList<User> friends = DBManager.getFriends(current.getId());

        request.setAttribute("chats", chats);
        request.setAttribute("friends", friends);
        request.setAttribute("messagesByChat", messages);
        request.getRequestDispatcher("/chat.jsp").forward(request, response);
    }
}
