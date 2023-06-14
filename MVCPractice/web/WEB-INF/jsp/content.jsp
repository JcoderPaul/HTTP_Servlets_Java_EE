<%--
  Для проверки работоспособности в браузере набираем (в моей версии настроек):
  http://localhost:8080/content-jstl-demo?id=7&test=hello_from_jsp
  произойдет обращение к сервлету content-jstl-demo
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%@ include file="header.jsp"%>
        <div>
                <span>Content. Проверка русской кодировки.</span>
                <p>flightDtoList size: ${requestScope.flightsSize} </p>
                <p>RequestScope flightsSize: ${requestScope.flights.size()} </p>
                <p>Id : ${requestScope.flights.get(0).id} </p>
                <p>Id 2: ${requestScope.flights[1].id} </p>
                <p>MAP Id 3: ${sessionScope.flightsMap[2]} </p>
                <p>JSESSION id: ${cookie["JSESSIONID"].value}, unique identifier </p>
                <p>Header: ${header["Cookie"]} </p>
                <p>Param id: ${param.id} </p>
                <p>Param test: ${param.test} </p>
        </div>
        <%@ include file="footer.jsp"%>
</body>
</html>
