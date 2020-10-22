package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addRequest")
public class AddRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current = (User)request.getSession().getAttribute("CURRENT_USER");
        int id = Integer.parseInt(request.getParameter("id_user"));
        DBManager.addRequest(current.getId(),id);
        if(request.getParameter("id")==null) {
            response.sendRedirect("/friends");
        }else
            response.sendRedirect("/profile?id="+id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/friends.jsp").forward(request, response);
    }
}
