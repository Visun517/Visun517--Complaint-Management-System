<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.gdse71.model.Complains" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%
    String nextId = "C001";

    List<Complains> complaintsList1 = (List<Complains>) request.getAttribute("complainsList1");

    if (complaintsList1 != null && !complaintsList1.isEmpty()) {
        Complains lastComplaint = complaintsList1.get(complaintsList1.size() - 1);
        String lastId = lastComplaint.getComplainId();

        String numberPart = lastId.substring(1);
        int number = Integer.parseInt(numberPart);
        number++;

        nextId = "C" + String.format("%03d", number);
    }
%>
<head>
    <meta charset="UTF-8">
    <title>Complaints Dashboard</title>
    <link href="${pageContext.request.contextPath}/css/AdminDashCss.css" rel="stylesheet">
</head>
<body>

<header class="dashboard-header">
    <div class="header-container">
        <div class="header-content">
            <div class="company-info">
                <h1 class="company-title">Municipal IT Division</h1>
                <p class="company-description">
                    Submit and track your complaints easily using the <strong>Complaint Management System</strong>.
                </p>
            </div>
            <div class="header-actions">
                <div class="user-info">
                    <span class="user-role">Employee</span>
                    <form method="POST" action="ComplaintsDashBoardServlet">
                        <button type="submit" name="action" value="logout" class="btn btn-primary">Logout</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</header>

<main class="main-content">
    <div class="dashboard-container">

        <div class="form-section">
            <h3>Submit or Update Complaint</h3>
            <form method="POST" action="ComplaintsDashBoardServlet" class="update-form">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="complaintId" class="form-label">Complaint ID</label>
                        <input type="text" id="complaintId" name="complaintId" class="form-input" value="<%= nextId %>" readonly>
                    </div>

                    <div class="form-group">
                        <label for="description" class="form-label">Description</label>
                        <input type="text" id="description" name="description" class="form-input" required>
                    </div>
                </div>

                <input type="hidden" id="status" name="status" value="Pending">
                <input type="hidden" id="remark" name="remark" value="Not updated">
                <input type="hidden" id="creatAt" name="creatAt">
                <input type="hidden" id="updateId" name="updateId">
                <input type="hidden" id="deleteId" name="deleteId">

                <button type="submit" name="action" value="save" class="btn btn-primary">Save</button>
                <button type="submit" name="action" value="update" class="btn btn-secondary">Update</button>
                <button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
            </form>
        </div>

        <!-- Complaints Table -->
        <div class="table-section">
            <h3>My Complaints</h3>
            <div class="table-container">
                <table class="complaints-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Remarks</th>
                        <th>Created At</th>
                    </tr>
                    </thead>
                    <tbody id="complain-tbody">
                    <%
                        List<Complains> complaintsList = (List<Complains>) request.getAttribute("complainsList");

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
                        <td><%= c.getRemarks() %></td>
                        <td><%= c.getCreatedDate() %></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5" class="no-data">No complaints found.</td>
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
<script src="${pageContext.request.contextPath}/js/ComplaintsDash.js"></script>
</body>
</html>
