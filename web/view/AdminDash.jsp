<%@ page import="lk.ijse.gdse71.model.Complains" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - CMS</title>
    <link href="${pageContext.request.contextPath}/css/AdminDashCss.css" rel="stylesheet">
</head>
<body>
<%
    String nextId = "C001";

    List<Complains> complaintsList = (List<Complains>) request.getAttribute("complainsList");

    if (complaintsList != null && !complaintsList.isEmpty()) {
        Complains lastComplaint = complaintsList.get(complaintsList.size() - 1);
        String lastId = lastComplaint.getComplainId();

        String numberPart = lastId.substring(1);
        int number = Integer.parseInt(numberPart);
        number++;

        nextId = "C" + String.format("%03d", number);
    }
%>

<header class="dashboard-header">
    <div class="header-container">
        <div class="header-content">
            <div class="company-info">
                <h1 class="company-title">Municipal IT Division</h1>
                <p class="company-description">
                    You have been contacted by the Municipal IT Division to develop a prototype of a
                    <strong>Complaint Management System (CMS)</strong>
                </p>
            </div>
            <div class="header-actions">
                <div class="user-info">
                    <span class="user-role">Administrator</span>
                    <form method="POST" action="AdminDashServlet">
                        <button type="submit" name="action" value="logout" class="btn btn-primary">
                            Logout
                        </button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</header>

<main class="main-content">
    <div class="dashboard-container">
        <div class="dashboard-title">
            <h2>Admin Dashboard</h2>
            <p>Manage and track complaint statuses</p>
        </div>

        <div class="form-section">
            <h3>Update Complaint Status</h3>
            <form method="POST" action="AdminDashServlet" class="update-form">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="complain-id" class="form-label">Complaint ID</label>
                        <input type="text" class="form-input" id="complain-id" name="complain-id" value="<%= nextId %>" readonly>
                    </div>

                    <div class="form-group">
                        <label for="statusDropDown" class="form-label">Status</label>
                        <select class="form-select" id="statusDropDown" name="status" required>
                            <option value="" disabled selected>Select status</option>
                            <option value="Pending">Pending</option>
                            <option value="In Progress">In Progress</option>
                            <option value="Resolved">Resolved</option>
                        </select>
                    </div>

                    <div class="form-group form-group-full">
                        <label for="remark" class="form-label">Remarks</label>
                        <input type="text" class="form-input" id="remark" name="remark" placeholder="Enter remarks for this complaint">
                    </div>
                </div>

                <input type="hidden" id="description" name="description">
                <input type="hidden" id="createAt" name="createAt">

                <button type="submit" name="action" value="update" class="btn btn-primary">
                    Update Complaint
                </button>
            </form>
        </div>

        <div class="table-section">
            <h3>All Complaints</h3>
            <div class="table-container">
                <table class="complaints-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Remarks</th>
                        <th>Created At</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody id="complain-tbody-admin">
                    <%
                        if (complaintsList != null && !complaintsList.isEmpty()) {
                            for (Complains c : complaintsList) {
                    %>
                    <tr>
                        <td><%= c.getComplainId() %></td>
                        <td class="description-cell"><%= c.getDescription() %></td>
                        <td>
                                    <span class="status-badge status-<%= c.getStatus().toLowerCase().replace(" ", "-") %>">
                                        <%= c.getStatus() %>
                                    </span>
                        </td>
                        <td><%= c.getRemarks() != null ? c.getRemarks() : "-" %></td>
                        <td><%= c.getCreatedDate() %></td>
                        <td>
                            <form method="POST" action="AdminDashServlet" class="delete-form">
                                <input type="hidden" name="complaintId" value="<%= c.getComplainId() %>">
                                <button type="submit" name="action" value="delete" class="btn btn-danger btn-small"
                                        onclick="return confirm('Are you sure you want to delete this complaint?')">
                                    Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="no-data">No complaints found.</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>

<script src="${pageContext.request.contextPath}/lib/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/AdminDash.js"></script>
</body>
</html>