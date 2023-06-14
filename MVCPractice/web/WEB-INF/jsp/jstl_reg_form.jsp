<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SimpleRegForm</title>
</head>
<body>
        <label action="/registration" method="post">

            <label for="nameId"> <strong>Enter name:</strong>
                <input type="text" name="name" id="nameId" size="30">
            </label><br>

            <label for="birthdayId"> <strong>Birthday:</strong>
                <input type="date" name="birthday" id="birthdayId">
            </label><br>

            <label for="emailId"> <strong>Email:</strong>
                <input type="text" name="email" id="emailId" size="35">
            </label><br>

            <label for="passId"> <strong>Enter password:</strong>
                <input type="password" name="password" id="passId" size="26">
            </label><p>

            <label for="roleId"> <strong> Role: </strong>
                <select name="role" id="roleId">
                    <c:forEach var="role" items="${requestScope.roles}">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>
            </label></p>

                <c:forEach var="gender" items="${requestScope.genders}">
                    <input type="radio" name="gender" value="${gender}"> <strong>${gender}</strong>
                    <br>
                </c:forEach>

            <button type="submit">SEND</button>
        </label>

            <c:if test="${not empty requestScope.errors}">
                <div style="color: red">
                    <c:forEach var="error" items="${requestScope.errors}">
                        <span>${error.message}</span>
                        <br>
                    </c:forEach>
                </div>
            </c:if>

        </form>
</body>
</html>
