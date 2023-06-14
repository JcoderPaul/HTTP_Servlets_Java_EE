<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>International Login</title>
</head>
<body>
    <%-- Самый простой и грубый вариант выставить (задать) параметры Local см. ниже:

    <fmt:setLocale value="ru_RU" />
    <fmt:setBundle basename="translations" />

    но лучше воспользоваться диррективой include, что бы не дублировать код там где
    это необходимо. Разместим часть этого кода на странице set_local.jsp, где будут
    кнопки выбора языка.
    --%>
    <%@ include file="header.jsp" %>
    <%@ include file="set_local.jsp" %>

    <form action="${pageContext.request.contextPath}/international_login" method="post">

        <label for="emailId"><strong><fmt:message key="page.login.email"/> :</strong>
            <input type="text" name="email" id="emailId" value="${param.email}" required>
        </label><br>

        <label for="passId"><strong><fmt:message key="page.login.password"/> :</strong>
            <input type="password" name="password" id="passId" required>
        </label><br>

        <button type="submit"><fmt:message key="page.login.submit.button"/> </button>
        <p>
        <a href="=${pageContext.request.contextPath}/registration">
            <button type="button"><fmt:message key="page.login.register.button"/></button>
        </a>

        <c:if test="${param.error != null}">
            <div style="color: brown">
                <span><strong><fmt:message key="page.login.message.error"/></strong></span>
            </div>
        </c:if>
        </p>

    </form>

    <%@ include file="footer.jsp" %>
</body>
</html>