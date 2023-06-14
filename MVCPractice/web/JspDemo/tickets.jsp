<%@ page import="AirportSimulator.Service.TicketService" %>
<%@ page import="AirportSimulator.DTO.TicketDto" %>
<%@ page import="java.util.List" %>
<%--
  Для запуска в браузере вводим:
  http://localhost:8080/JspDemo/tickets.jsp?flightId=4
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <H1> Купленные билеты </H1>
    <ul>
        <%--
        В данном случае используем устаревшую и вредную технологию СКРИПЛЕТОВ
        '<% same code %>', поскольку в данном случае мы нашу бизнес логику
        переносим на уровень отображения, т.е. нарушаем архитектурный принцип MVC!
        --%>
        <%
            Long flightId = Long.valueOf(request.getParameter("flightId"));
            List<TicketDto> tickets = TicketService.getInstance().findAllByFlightId(flightId);
            for (TicketDto ticket : tickets){
                out.write(String.format("<li>%s</li>", ticket.getSeat_no()));
            }
        %>
    </ul>>
</body>
</html>
