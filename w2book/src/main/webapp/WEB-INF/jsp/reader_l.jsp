<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table table-hover">
    <thead>
    <tr>
        <th>图书号</th>
        <th>借出日期</th>
        <th>归还日期</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="alog">
        <tr>
            <td><c:out value="${alog.bookId}"></c:out></td>
            <td><c:out value="${alog.lendDate}"></c:out></td>
            <td><c:out value="${alog.backDate}"></c:out></td>
            <c:if test="${empty alog.backDate}">
                <td>未还</td>
            </c:if>
            <c:if test="${!empty alog.backDate}">
                <td>已还</td>
            </c:if>
            <c:if test="">
                <td>超期</td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>