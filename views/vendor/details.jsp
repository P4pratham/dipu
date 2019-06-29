<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${requestScope.status}</h3>
<h3>User Details : ${sessionScope.user_dtls}</h3>
<h5>
<a href="<spring:url value='/user/logout'/>">Logout</a>
</h5>
</body>
</html>