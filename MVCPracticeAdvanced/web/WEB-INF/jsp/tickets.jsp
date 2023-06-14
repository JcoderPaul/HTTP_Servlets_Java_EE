<%--
  Применяем директиву taglib для подключения ядра библиотеки JSTL
  core и библиотеки функций functions. Данные сюда прилетают из:
  http://localhost:8080/jstl-tickets-by-id с параметром ?flightId=2,
  например...
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>getTicket JSTL Demo</title>
</head>
<body>
    <H1>Купленные билеты</H1>
    <ul>
        <c:forEach var="tickets" items="${requestScope.tickets}">

            <li>${tickets.id} - ${tickets.flight_id} - ${tickets.seat_no}</li>
        </c:forEach>
        <%-- Применим функцию из библиотеки с псевдонимом 'fn', например узнаем размер списка --%>
        <p>Размер списка билетов: ${fn:length(tickets)}</p>
    </ul>
</body>
</html>
