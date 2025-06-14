package lk.ijse.gdse71.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse71.model.Complains;
import lk.ijse.gdse71.model.dao.ComplainsDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ComplaintsDashBoardServlet")
public class ComplaintsDashBoardServlet extends HttpServlet {

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
            ResultSet allComplains = complainsDao.getAllComplains(userId, dataSource);

            List<Complains> complainsList = new ArrayList<>();

            while (allComplains.next()) {
                Complains complains = new Complains();
                complains.setComplainId(allComplains.getString("id"));
                complains.setUserId(allComplains.getString("user_id"));
                complains.setDescription(allComplains.getString("description"));
                complains.setStatus(allComplains.getString("status"));
                complains.setRemarks(allComplains.getString("remarks"));
                complains.setCreatedDate(allComplains.getTimestamp("created_at"));
                complainsList.add(complains);
            }

            req.setAttribute("complainsList", complainsList);
            req.getRequestDispatcher("/view/ComplaintDash.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("complaintId");
        String description = req.getParameter("description");
        String status = req.getParameter("status");

        ComplainsDao complainsDao = new ComplainsDao();

        if (req.getParameter("action").equals("update")) {
            System.out.println("update");

        } else if (req.getParameter("action").equals("delete")) {
            System.out.println("delete");

            try {
                int i = complainsDao.deleteComplains(id, dataSource);

                if (i > 0) {
                    System.out.println("Delete success");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            Complains complains = new Complains();
            complains.setComplainId(id);
            complains.setDescription(description);
            complains.setStatus(status);
            complains.setUserId(userId);

            try {
                int i = complainsDao.saveComplains(complains, dataSource);
                if (i > 0) {
                    System.out.println("Save success");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
