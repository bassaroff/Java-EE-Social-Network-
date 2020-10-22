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
import java.sql.SQLOutput;
import java.sql.Timestamp;

@WebServlet(value = "/sendMessage")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current = (User)request.getSession().getAttribute("CURRENT_USER");
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        String message = request.getParameter("message_text");

        Chat chat = new Chat();
        chat.setId_user(current.getId());
        chat.setId_friend(id_user);
        chat.setLatest_message_time(new Timestamp(System.currentTimeMillis()));
        chat.setLatest_message_text(message);
        chat.setCreated_date(new Timestamp(System.currentTimeMillis()));
        Chat chat1 = DBManager.chatExist(chat);


        if(chat1.getId()!=0){
            Message mess = new Message();
            mess.setId_sender(current.getId());
            mess.setId_receiver(id_user);
            mess.setMessage(message);
            mess.setSended_time(new Timestamp(System.currentTimeMillis()));
            mess.setId_chat(chat1.getId());
            if(DBManager.addMessage(mess)){
                response.sendRedirect("/getChatMessages?chat="+chat1.getId());
            }


        }else {
            if(DBManager.addChat(chat,message)){
                response.sendRedirect("/messages");
            }


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/friends.jsp").forward(request, response);
    }
}
