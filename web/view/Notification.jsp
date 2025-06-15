<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Popup Notifications</title>
    <link href="${pageContext.request.contextPath}/css/Notification.css" rel="stylesheet">
</head>
<body>

<!-- Notification Modal -->
<div id="notificationModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeNotification()">&times;</span>

        <c:if test="${not empty successMessage}">
            <p class="message success">${successMessage}</p>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <p class="message error">${errorMessage}</p>
        </c:if>
        <c:if test="${not empty infoMessage}">
            <p class="message info">${infoMessage}</p>
        </c:if>
    </div>
</div>

<script>

    window.onload = function () {
        const modal = document.getElementById('notificationModal');
        const message = document.querySelector('.message');

        const redirectTo = "${pageContext.request.contextPath}${redirectTo}";

        if (message && message.textContent.trim() !== '') {
            modal.style.display = 'block';

            setTimeout(() => {
                modal.style.display = 'none';
                window.location.href = redirectTo;
            }, 3000);
        }
    };

    function closeNotification() {
        document.getElementById('notificationModal').style.display = 'none';
        const redirectTo = "${pageContext.request.contextPath}${redirectTo}";
        window.location.href = redirectTo;
    }

</script>
</body>
</html>
