    package lk.ijse.gdse71.controller;

    import jakarta.annotation.Resource;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import lk.ijse.gdse71.model.User;
    import lk.ijse.gdse71.dao.UserDao;

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
                        response.sendRedirect(contextPath + "/AdminDashServlet");
                    } else if (role.equals("employee")) {
                        response.sendRedirect(request.getContextPath() + "/ComplaintsDashBoardServlet");
                    }
                } else {
                    request.setAttribute("errorMessage", "Invalid username, password, or role!");
                    request.setAttribute("redirectTo", "/view/LogIn.jsp");
                    request.getRequestDispatcher("/view/Notification.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                request.setAttribute("errorMessage", "Internal server error!");
                request.setAttribute("redirectTo", "/view/LogIn.jsp");
                request.getRequestDispatcher("/view/Notification.jsp").forward(request, response);
                throw new RuntimeException(e);
            }

        }
    }
