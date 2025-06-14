<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.gdse71.model.Complains" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<html>
<head>
    <title>Complaints Dashboard</title>
    <link href="${pageContext.request.contextPath}/css/ComplaintsDashCss.css" rel="stylesheet">
</head>
<body>
<h2 style="text-align: center;">Complaints Dashboard</h2>


<form method="POST" action="ComplaintsDashBoardServlet">
    <label for="complaintId">Complaints-id</label>
    <input type="text" id="complaintId" name="complaintId" value="<%= nextId %>" readonly>

    <label for="status">Status</label>
    <input type="text" id="status" name="status" value="Pending" readonly>

    <label for="description">Description</label>
    <input type="text" id="description" name="description" required>

    <input type="hidden" id="remark" name="remark" required>

    <input type="hidden" id="creatAt" name="creatAt" required>

    <button type="submit">Save</button>

    <form method="POST" action="ComplaintsDashBoardServlet">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="complaintId" id="updateId">
        <button type="submit">Update</button>
    </form>

    <form method="POST" action="ComplaintsDashBoardServlet">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="complaintId" id="deleteId" >
        <button type="submit">Delete</button>
    </form>
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
    </tr>
    </thead>
    <tbody id="complain-tbody">
    <%
        if (complaintsList != null && !complaintsList.isEmpty()) {
            for (Complains c : complaintsList) {
    %>
    <tr>
        <td><%= c.getComplainId() %></td>
        <td><%= c.getDescription() %></td>
        <td><%= c.getStatus() %></td>
        <td><%= c.getRemarks() %></td>
        <td><%= c.getCreatedDate() %></td>
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
<script src="${pageContext.request.contextPath}/js/ComplaintsDash.js"></script>
</body>
</html>
