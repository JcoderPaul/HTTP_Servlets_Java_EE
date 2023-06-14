<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>Список перелетов :</h1>
        <ul>
            <c:forEach var="flight" items="${requestScope.flights}">
                    <li>
                        <a href="/jstl-tickets-by-id?flightId=${flight.id}">${flight.description}</a>
                    </li>
            </c:forEach>
        </ul>
</body>
</html>
