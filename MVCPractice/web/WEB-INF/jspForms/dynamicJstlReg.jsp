<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SimpleRegForm</title>
</head>
<body>
        <form action="/dynamic-reg-form" method="post">
            <label for="nameId"> Enter name:
                <input type="text" name="name" id="nameId">
            </label><br>
            <label for="birthdayId"> Birthday:
                <input type="date" name="birthday" id="birthdayId">
            </label><br>
            <label for="emailId"> Email:
                <input type="text" name="email" id="emailId">
            </label><br>
            <label for="passId"> Enter password:
                <input type="password" name="password" id="passId">
            </label><br>
            <select name="role" id="roleId">
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role}">${role}</option>
                </c:forEach>
            </select><br>
                <c:forEach var="gender" items="${requestScope.genders}">
                    <input type="radio" name="gender" value="${gender}"> ${gender}
                    <br>
                </c:forEach>
            <button type="submit">SEND</button>
        </form>
</body>
</html>
