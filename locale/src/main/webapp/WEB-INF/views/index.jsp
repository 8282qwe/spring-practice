<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css" type="text/css"/>
    <script>
        window.addEventListener('load', function () {
            let anchors = document.querySelectorAll("#languages a");

            anchors.forEach(function (el) {
                el.addEventListener('click', function (e) {
                    e.preventDefault();
                    console.log(this.getAttribute('data-lang'));
                    document.cookie = "lang=" + this.getAttribute('data-lang') + "; path=${pageContext.request.contextPath}; max-age=" + (30 * 24 * 60 * 60) + ";"
                    location.href = '${pageContext.request.contextPath}';
                });
            });
        });
    </script>
</head>
<body>
<h1><spring:message code="index.title"/></h1>
<div id="languages">
    <c:choose>
        <c:when test='${lang == "en"}'>
            <a href="" data-lang="ko">KO</a><a href="" class="active" data-lang="en">EN</a>
        </c:when>
        <c:when test='${lang == "ko"}'>
            <a href="" data-lang="ko" class="active">KO</a><a href="" data-lang="en">EN</a>
        </c:when>
    </c:choose>
</div>
</body>
</html>
