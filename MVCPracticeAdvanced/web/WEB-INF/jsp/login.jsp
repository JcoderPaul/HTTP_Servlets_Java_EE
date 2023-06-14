<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <label for="emailId"><strong>Email:</strong>
            <input type="text" name="email" id="emailId" value="${param.email}" required>
        </label><br>

        <label for="passId"><strong>Password:</strong>
            <input type="password" name="password" id="passId" required>
        </label><br>

        <button type="submit">LOGIN</button>
        <p>
        <a href="=${pageContext.request.contextPath}/registration">
            <button type="button">REGISTER</button>
        </a>
        <%--
        Обрабатываем неверно введенные данные, т.е.
        параметр 'error' из сервлета пришел не нулевым
        --%>
        <c:if test="${param.error != null}">
            <div style="color: brown">
                <span><strong>Email or password is not correct!</strong></span>
            </div>
        </c:if>
        </p>

    </form>
</body>
</html>
