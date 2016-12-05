<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zgq
  Date: 2016/12/3
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理-创建客户</title>
</head>
<body>
<h1>客户管理界面</h1>
<table>
    <tr>
        <th>客户名称</th>
        <th>客户联系人</th>
        <th>联系电话</th>
        <th>邮箱</th>
        <th>操作</th>
    </tr>
    <c:forEach var="customer" items="${customerList}">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.contact}</td>
            <td>${customer.telephone}</td>
            <td>${customer.email}</td>
            <td>delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>