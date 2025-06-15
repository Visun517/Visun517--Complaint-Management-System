package lk.ijse.gdse71.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse71.model.User;
import lk.ijse.gdse71.model.dao.UserDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contextPath = req.getContextPath();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        System.out.println(username);
        System.out.println(password);
        System.out.println(role);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserName(username);
        user.setPassword(password);
        user.setRole(role);

        UserDao userDao = new UserDao();
        try {
            int i = userDao.saveUser(user, dataSource);
            if (i>0){
                req.setAttribute("successMessage", "User registered successfully!");
                req.setAttribute("redirectTo", "/view/LogIn.jsp");
                req.getRequestDispatcher("/view/Notification.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("errorMessage", "Internal server error!");
            req.setAttribute("redirectTo", "/view/LogIn.jsp");
            req.getRequestDispatcher("/view/Notification.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
