<%--
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
</head>
<body>

<h2 style="text-align: center;">Admin Dashboard</h2>


<form method="POST" action="ComplaintsDashBoardServlet">
    <label for="complaintId">Complaints-id</label>
    <input type="text" id="complaintId" name="complaintId"  readonly>

    <label for="status">Status</label>
    <input type="text" id="status" name="status" value="Pending" readonly>

    <label for="description">Description</label>
    <input type="text" id="description" name="description" required>

    <input type="hidden" id="remark" name="remark" value="Not updated" required>
    <input type="hidden" id="creatAt" name="creatAt" required>

    <input type="hidden" id="updateId" name="updateId">
    <input type="hidden" id="deleteId" name="deleteId">

    <button type="submit" name="action" value="save">Save</button>
    <button type="submit" name="action" value="update">Update</button>
    <button type="submit" name="action" value="delete">Delete</button>
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

</body>
</html>
