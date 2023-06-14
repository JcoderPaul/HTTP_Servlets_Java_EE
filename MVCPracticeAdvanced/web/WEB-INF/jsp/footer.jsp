<%-- Директива 'page' указывает настройки 'контент-тайп' в данном случае кодировку UTF-8 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<H1> Footer. Низ страницы</H1>

<div id="locale">
    <form action="${pageContext.request.contextPath}/locale" method="post">
        <button type="submit" name="lang" value="ru_RU">RU</button>
        <button type="submit" name="lang" value="en_US">EN</button>
    </form>
</div>

