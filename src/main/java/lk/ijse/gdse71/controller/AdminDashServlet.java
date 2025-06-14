package lk.ijse.gdse71.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse71.model.Complains;
import lk.ijse.gdse71.model.dao.ComplainsDao;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            throw new RuntimeException(e);
        }

    }
}
