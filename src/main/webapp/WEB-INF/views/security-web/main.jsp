<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>
<body>

<h1>메인 페이지 입니다!!(로그인 하지 않은 사용자도 엑세스 가능)</h1>
<ul>
    <li><a href="/security-web/user">일반 사용자 페이지로!</a></li>
    <li><a href="/security-web/admin">관리자 페이지로!</a></li>
</ul>

<form action="/security-web/logout" method="post">
    <button type="submit">로그아웃</button>
</form>

</body>
</html>
