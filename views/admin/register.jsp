<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sf:form method="post" modelAttribute="vendor">
	<!-- model Attribute= the name of the model Attribute under which form data is bound -->
		<table style="background-color: cyan; margin: auto;">
			<tr>
				<td>Enter User Email</td>
				<td><sf:input path="email" /></td>
			</tr>
			<tr>
				<td>Enter User Name</td>
				<td><sf:input path="name" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><sf:password path="password" /></td>
			</tr>
			<tr>
				<td>Choose role</td>
				<td><sf:radiobutton path="role" value="admin" />admin</td>
				<td><sf:radiobutton path="role" value="vendor" />vendor</td>
			</tr>
			<tr>
				<td>Enter City</td>
				<td><sf:input path="city" /></td>
			</tr>
			<tr>
				<td>Enter PhoneNo</td>
				<td><sf:input path="phn" /></td>
			</tr>
			<tr>
				<td>Enter Reg Amount</td>
				<td><sf:input type="number" path="regAmount" /></td>
			</tr>

			<tr>
				<td>Enter Reg Date</td>
				<td><sf:input type="date" path="regDate" /></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Register Me" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>