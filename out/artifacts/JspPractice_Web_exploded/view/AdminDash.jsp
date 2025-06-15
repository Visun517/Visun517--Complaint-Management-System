<%@ page import="lk.ijse.gdse71.model.Complains" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Visun
  Date: 13/06/2025
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
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

<h2 style="text-align: center;">Admin Dashboard</h2>


<form method="POST" action="AdminDashServlet">

    <label for="complain-id">Complain-id</label>
    <input type="text" id="complain-id" name="complain-id" value="<%= nextId %>">

    <label for="statusDropDown">Choose status</label>
    <select class="form-select" id="statusDropDown" name="status" required>
        <option value="" disabled selected>Select status</option>
        <option value="Pending">Pending</option>
        <option value="In Progress">In Progress</option>
        <option value="Resolved">Resolved</option>
    </select>

    <label for="remark">Remarks</label>
    <input type="text" id="remark" name="remark">

    <input type="hidden" id="description" name="description" >

    <input type="hidden" id="createAt" name="createAt">

    <button type="submit" name="action" value="update">Update</button>
</form>


<br>

    <table border="1" cellpadding="5" cellspacing="0">
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
                    <form method="POST" action="AdminDashServlet">
                        <td><%= c.getComplainId() %></td>
                        <td><%= c.getDescription() %></td>
                        <td><%= c.getStatus() %></td>
                        <td><%= c.getRemarks() %></td>
                        <td><%= c.getCreatedDate() %></td>

                        <input type="hidden" name="complaintId" value="<%= c.getComplainId() %>">

                        <td><button type="submit" name="action" value="delete">Delete</button></td>
                    </form>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="5">No complaints found.</td>
                </tr>
                <%
                    }
                %>
                </tbody>
    </table>
<script src="${pageContext.request.contextPath}/lib/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/AdminDash.js"></script>
</body>
</html>
