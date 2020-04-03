<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table table-hover" >
    <thead>
    <tr>
        <th>读者号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>生日</th>
        <th>地址</th>
        <th>电话</th>
        <th>编辑</th>
        <th>删除</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${readers}" var="reader">
        <tr>
            <td><c:out value="${reader.readerId}"></c:out></td>
            <td><c:out value="${reader.name}"></c:out></td>
            <td><c:if test="${'0'.equals(reader.sex)}">男</c:if><c:if test="${'1'.equals(reader.sex)}">女</c:if></td>
            <td><fmt:formatDate value="${reader.birth}" pattern="yyyy-MM-dd"/></td>
            <td><c:out value="${reader.address}"></c:out></td>
            <td><c:out value="${reader.telcode}"></c:out></td>
            <td><a href="reader_edit.html?readerId=<c:out value="${reader.readerId}"></c:out>"><button type="button" class="btn btn-info btn-xs">编辑</button></a></td>
            <td><a href="reader_delete.html?readerId=<c:out value="${reader.readerId}"></c:out>"><button type="button" class="btn btn-danger btn-xs">删除</button></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>