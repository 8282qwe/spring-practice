<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bean Validation</title>
</head>
<body>
<h1>Bean Validation</h1>
<form:form action="${pageContext.request.contextPath }/ex03" method="post" modelAttribute="user">
	<label for="name">name:</label>
	<form:input path="name" />
	<p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
		<form:errors path="name" />
	</p>

	<label for="email">email:</label>
	<form:input path="email" />
	<p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
		<form:errors path="email" />
	</p>

	<label for="password">password:</label>
	<form:password path="password" />
	<p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
		<form:errors path="password" />
	</p>

	<form:radiobutton path="" value="male" label="남" />
	<form:radiobutton path="" value="female" label="여" />

	<input type="submit" value="sign up">
</form:form>
</body>
</html>