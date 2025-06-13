<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.gdse71.model.Complains" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaints Dashboard</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
<h2 style="text-align: center;">Complaints Dashboard</h2>

<form method="POST" action="ComplaintsDashBoardServlet">
    <label for="complaintId">Complaints-id</label>
    <input type="text" id="complaintId" name="complaintId" required>

    <label for="status">Status</label>
    <input type="text" id="status" name="status" required>

    <label for="description">Description</label>
    <input type="text" id="description" name="description" required>

    <label for="remarks">Remarks</label>
    <input type="text" id="remarks" name="remarks" required>

    <button type="submit">Save</button>
    <button type="button">Delete</button>
    <button type="button">Update</button>
</form>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>description</th>
        <th>status</th>
        <th>remarks</th>
        <th>created_at</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Complains> complaintsList = (List<Complains>) request.getAttribute("complainsList");
        if (complaintsList != null) {
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
    <tr><td colspan="4">No complaints found.</td></tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
