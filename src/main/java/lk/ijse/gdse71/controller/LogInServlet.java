package lk.ijse.gdse71.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.model.User;
import lk.ijse.gdse71.model.dao.UserDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contextPath = request.getContextPath(); ;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        UserDao userDao = new UserDao();
        try {
            ResultSet resultSet = userDao.loginUser(user, dataSource);

            if (resultSet.next()) {
                String role = resultSet.getString("role");

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("username", username);
                httpSession.setAttribute("role", role);
                httpSession.setAttribute("id", resultSet.getString("id"));


                if (role.equals("admin")) {
                    response.sendRedirect(contextPath + "/view/AdminDash.jsp");
                } else if (role.equals("employee")) {
//                    response.sendRedirect(contextPath +"/view/ComplaintDash.jsp");
                    response.sendRedirect(request.getContextPath() + "/ComplaintsDashBoardServlet");
                }
            } else {
                response.sendRedirect(contextPath +"/view/LogIn.jsp");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Invalid username, password, or role");
            request.getRequestDispatcher(contextPath +"$/view/LogIn.jsp").forward(request, response);
            throw new RuntimeException(e);
        }

    }
}
