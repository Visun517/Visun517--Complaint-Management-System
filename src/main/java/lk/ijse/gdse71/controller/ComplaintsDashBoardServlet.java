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

            ResultSet allComplainsAdmin = complainsDao.getAllComplainsAdmin(dataSource);
            List<Complains> complainsList1 = new ArrayList<>();

            while (allComplainsAdmin.next()) {
                Complains complains = new Complains();
                complains.setComplainId(allComplainsAdmin.getString("id"));
                complains.setUserId(allComplainsAdmin.getString("user_id"));
                complains.setDescription(allComplainsAdmin.getString("description"));
                complains.setStatus(allComplainsAdmin.getString("status"));
                complains.setRemarks(allComplainsAdmin.getString("remarks"));
                complains.setCreatedDate(allComplainsAdmin.getTimestamp("created_at"));
                complainsList1.add(complains);
            }

            req.setAttribute("complainsList", complainsList);
            req.setAttribute("complainsList1", complainsList1);
            req.getRequestDispatcher("/view/ComplaintDash.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("complaintId");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        String remark = req.getParameter("remark");
        //String creatAt = req.getParameter("creatAt");

        ComplainsDao complainsDao = new ComplainsDao();

        if (req.getParameter("action").equals("update")) {
            System.out.println("update");

            Complains complains = new Complains();
            complains.setComplainId(id);
            complains.setDescription(description);
            complains.setStatus(status);
            complains.setUserId(userId);
            complains.setRemarks(remark);

            try {
                if (!isResolved(id)) {
                    int i = complainsDao.updateComplains(complains, dataSource);

                    if (i > 0) {
                        System.out.println("Update success");
                        resp.sendRedirect("ComplaintsDashBoardServlet");
                    }
                }else {
                    System.out.println("You can't delete this complain");
                    resp.sendRedirect("ComplaintsDashBoardServlet");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (req.getParameter("action").equals("delete")) {
            System.out.println("delete");

            try {
                if (!isResolved(id)) {
                    int i = complainsDao.deleteComplains(id, dataSource);

                    if (i > 0) {
                        System.out.println("Delete success");
                        resp.sendRedirect("ComplaintsDashBoardServlet");
                    }
                }else {
                    System.out.println("You can't delete this complain");
                    resp.sendRedirect("ComplaintsDashBoardServlet");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (req.getParameter("action").equals("save")) {
            Complains complains = new Complains();
            complains.setComplainId(id);
            complains.setDescription(description);
            complains.setStatus(status);
            complains.setUserId(userId);
            complains.setRemarks(remark);

            try {
                int i = complainsDao.saveComplains(complains, dataSource);
                if (i > 0) {
                    System.out.println("Save success");
                    resp.sendRedirect("ComplaintsDashBoardServlet");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public boolean isResolved(String id) throws Exception {
        ComplainsDao complainsDao = new ComplainsDao();
        ResultSet resolved = complainsDao.isResolved(id, dataSource);

        if (resolved.next()) {
            return !resolved.getString("status").equals("Resolved");
        } else {
            throw new Exception("No complaint found with the given ID: " + id);
        }
    }

}
