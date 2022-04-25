<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="data.Question" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vaalikoneen kysymykset</title>


</head>
<body>
<h2>Vaalikoneen kysymykset</h2>
<ol>
<c:forEach var="question" items="${requestScope.questionlist}" >
<li>${question.kysymys}
</c:forEach>
</ol>




</body>
</html>