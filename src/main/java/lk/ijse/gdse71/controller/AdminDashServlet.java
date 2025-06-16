package lk.ijse.gdse71.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.model.Complains;
import lk.ijse.gdse71.dao.ComplainsDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AdminDashServlet")
public class AdminDashServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;
    String userId = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contextPath = req.getContextPath();

        HttpSession session1 = req.getSession(false);
        if (session1 == null || session1.getAttribute("id") == null) {
            resp.sendRedirect(contextPath + "/view/LogIn.jsp");
            return;
        }
        userId = (String) session1.getAttribute("id");

        ComplainsDao complainsDao = new ComplainsDao();
        try {
            ResultSet allComplainsAdmin = complainsDao.getAllComplainsAdmin(dataSource);
            List<Complains> complainsList = new ArrayList<>();

            while (allComplainsAdmin.next()) {
                Complains complains = new Complains();
                complains.setComplainId(allComplainsAdmin.getString("id"));
                complains.setUserId(allComplainsAdmin.getString("user_id"));
                complains.setDescription(allComplainsAdmin.getString("description"));
                complains.setStatus(allComplainsAdmin.getString("status"));
                complains.setRemarks(allComplainsAdmin.getString("remarks"));
                complains.setCreatedDate(allComplainsAdmin.getTimestamp("created_at"));
                complainsList.add(complains);
            }

            req.setAttribute("complainsList", complainsList);
            req.getRequestDispatcher("/view/AdminDash.jsp").forward(req, resp);

        } catch (SQLException e) {
            req.setAttribute("errorMessage", "Internal server error!");
            req.setAttribute("redirectTo", "/view/AdminDash.jsp");
            req.getRequestDispatcher("/view/Notification.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("action").equals("update")) {
            System.out.println("update");
            String status = req.getParameter("status");
            String remarks = req.getParameter("remark");
            String complainId = req.getParameter("complain-id");
            String creatAt = req.getParameter("createAt");
            String description = req.getParameter("description");

            Complains complains = new Complains();
            complains.setStatus(status);
            complains.setRemarks(remarks);
            complains.setUserId(userId);
            complains.setComplainId(complainId);
            complains.setDescription(description);

            ComplainsDao complainsDao = new ComplainsDao();
            try {
                int i = complainsDao.updateComplains(complains, dataSource);
                if (i > 0) {
                    req.setAttribute("successMessage", "Complain updated successfully!");
                    req.setAttribute("redirectTo", "/AdminDashServlet");
                    req.getRequestDispatcher("/view/Notification.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                req.setAttribute("errorMessage", "Internal server error!");
                req.setAttribute("redirectTo", "/view/AdminDash.jsp");
                req.getRequestDispatcher("/view/Notification.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }

        } else if (req.getParameter("action").equals("delete")) {
            System.out.println(req.getParameter("complaintId"));

            ComplainsDao complainsDao = new ComplainsDao();
            try {
                int i = complainsDao.deleteComplains(req.getParameter("complaintId"), dataSource);
                if (i > 0) {
                    req.setAttribute("successMessage", "Complain deleted successfully!");
                    req.setAttribute("redirectTo", "/AdminDashServlet");
                    req.getRequestDispatcher("/view/Notification.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                req.setAttribute("errorMessage", "Internal server error!");
                req.setAttribute("redirectTo", "/view/AdminDash.jsp");
                req.getRequestDispatcher("/view/Notification.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("action").equals("logout")) {
            System.out.println("logout");
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            resp.sendRedirect(req.getContextPath() + "/view/LogIn.jsp");

        }
    }
}
