<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
  <head>
    <title>Show</title>
  <style type="text/css">
    table {
      border: 1px solid pink;
      margin: 0 auto;
    td{
      width: 150px;
      border: 1px solid pink;
      text-align: center;
    }
  </style>
  </head>
  <body>
	<table>
	  <tr>
	    <td>USERNAME</td>
	    <td>NAME</td>
	    <td>AGE</td>
	    <td>TELENO</td>
	  </tr>
	  <c:forEach items="${personList}" var="item">
	    <tr>
	      <td>${item.username}</td>
	      <td>${item.name}</td>
	      <td>${item.age}</td>
	      <td>${item.teleno}</td>
	    </tr>
	  </c:forEach>
	</table>

	<table>
	  <tr>
	    <td>USERNAME</td>
	    <td>NAME</td>
	  </tr>
	  <c:forEach items="${userList}" var="item">
	    <tr>
	      <td>${item.username}</td>
	      <td>${item.password}</td>
	    </tr>
	  </c:forEach>
	</table>
  	<div>
  		<a href="index.jsp">返回数据库操作页面</a>
  	</div>
  </body>
</html>