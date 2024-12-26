<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("newline", "\n");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>방명록</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/add" method="post">
    <table border=1 width=500>
        <tr>
            <td>이름</td>
            <td><input type="text" name="name"></td>
            <td>비밀번호</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan=4><textarea name="contents" cols=60 rows=5></textarea></td>
        </tr>
        <tr>
            <td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
        </tr>
    </table>
</form>
<br>
<c:forEach items="${guestbooks}" var="item" varStatus="status">
    <table width=510 border=1>
        <tr>
            <td>[${guestbooks.size()-status.index}]</td>
            <td>${item.name}
            </td>
            <td>${item.regDate}
            </td>
            <td><a href="${pageContext.request.contextPath}/delete/${item.id}">삭제</a></td>
        </tr>
        <tr>
            <td colspan=4>${fn:replace(item.contents, newline, "<br>")}
            </td>
        </tr>
    </table>
    <br>
</c:forEach>
</body>
</html>
