<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jstl_img_reg_form</title>
</head>
<body>
        <%--
        Если просто ввести: http://localhost:8080/Image/users/birthday.jpg
        т.е. сделать запрос к 'удаленному' серверу то браузер попытается
        скачать файл с картинкой на сторону пользователя (как мы обычно
        качаем картинки из сети).
        --%>
        <img src="${pageContext.request.contextPath}/Image/users/birthday.jpg"
             alt="User image">
        <%--
        В теги формы добавляем параметр enctype="multipart/form-data" т.к.
        в теле формы содержится возможность загружать бинарный файлы на
        'удаленный' сервер, в данном случае картинки.
        --%>
        <form action="${pageContext.request.contextPath}/registration"
              method="post"
              enctype="multipart/form-data">
        <%--
        В теге <input> добавляем парамтр 'required' на случай если
        пользователь ничего не введет, хотя основные проверки можно
        реализовать и в JavaScript на стороне фронта.
        >--%>
            <label for="nameId"> <strong>Enter name:</strong>
                <input type="text" name="name" id="nameId" size="30" required>
            </label><br>

            <label for="birthdayId"> <strong>Birthday:</strong>
                <input type="date" name="birthday" id="birthdayId" required>
            </label><br>

            <label for="emailId"> <strong>Email:</strong>
                <input type="text" name="email" id="emailId" size="35" required>
            </label><br>
            <%--
               В теге <input> добавляем тип парамтра 'file' и конечно не забываем
               в заголовках формы enctype="multipart/form-data" это важно.
             --%>
            <label for="imageId"> <strong>Load image:</strong>
                <input type="file" name="image" id="imageId" required>
            </label><br>

            <label for="passId"> <strong>Enter password:</strong>
                <input type="password" name="password" id="passId" size="26" required>
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
