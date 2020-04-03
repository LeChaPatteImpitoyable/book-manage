<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th>流水号</th>
        <th>图书号</th>
        <th>读者证号</th>
        <th>借出日期</th>
        <th>归还日期</th>
        <th>是否超过7天</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="alog">
        <tr>
            <td><c:out value="${alog.sernum}"></c:out></td>
            <td><c:out value="${alog.bookId}《${alog.bookName}》"></c:out></td>
            <td><c:out value="${alog.readerId}<${alog.readerName}>"></c:out></td>
            <td><fmt:formatDate value="${alog.lendDate}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${alog.backDate}" pattern="yyyy-MM-dd"/></td>
            <%--<td><a href="deletebook.html?bookId=<c:out value="${alog.sernum}"></c:out>"><button type="button" class="btn btn-danger btn-xs">删除</button></a></td>--%>
            <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
            <%--<c:if test="${nowDate - alog.lendDate.time > 7*24*60*60}">--%>
                <%--<td>是</td>--%>
            <%--</c:if>--%>
            <c:choose>

                <c:when test="${empty alog.backDate && nowDate - alog.lendDate.time > 7*24*60*60*1000}">
                    <td style="background-color: red">是</td>
                </c:when>

                <c:otherwise>
                    <td>否</td>
                </c:otherwise>
            </c:choose>
            <c:if test="${empty alog.backDate}">
                <td><a href="returnbook.html?bookId=<c:out value="${alog.bookId}&flag=1"></c:out>"><button type="button" class="btn btn-primary btn-xs">归还</button></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>