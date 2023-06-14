<%-- Директива 'page' указывает настройки 'контент-тайп' в данном случае кодировку UTF-8 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<H1> Header. Верх страницы</H1>
<div>
    <%--
    Данная форма отправляется методом POST, по умолчанию формы
    отправляются методом GET. В данном случае мы проверяем есть ли
    'залогиненный' пользователь в сессии, если да, то кнопка будет
    видна на экране, если нет, то ее нет смысла отображать --%>
    <c:if test="${not empty sessionScope.user}">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit"> LOGOUT </button>
        </form>
    </c:if>

</div>
