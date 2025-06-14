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


<form method="POST" action="AdminDashServlet">

    <label for="statusDropDown">Choose status</label>
    <select class="form-select" id="statusDropDown" name="role" required>
        <option value="" disabled selected>Select status</option>
        <option value="Pending">Pending</option>
        <option value="In Progress">In Progress</option>
        <option value="Resolved">Resolved</option>
    </select>

    <label for="remark">Remarks</label>
    <input type="text" id="remark" name="remark">

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
