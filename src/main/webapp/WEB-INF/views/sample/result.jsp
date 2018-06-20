<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
  <head>
    <title>result.jsp</title>
  </head>
  <body>
  String return 타입은 반환한 문자열.jsp 파일을 찾아서 실행합니다.
  <div>
    Hello ${msg}
  </div>

  <hr>
  <div>
    <span>${member.name}</span>
    <span>${member.age}</span>
    <span>${member.gender}</span>
    <br>
    <span>${memberVO.name}</span>
    <span>${memberVO.age}</span>
    <span>${memberVO.gender}</span>
  </div>
  </body>
</html>
