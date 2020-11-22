<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
  <head>
    <title>Show</title>
    <link id="css" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}//css/index.css">
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
	<div class="container">
		<div class="sub-container">
			<div class="table-title">
				<div class="title-item">USERNAME</div>
				<div class="title-item">NAME</div>
				<div class="title-item">AGE</div>
				<div class="title-item">TELENO</div>
			</div>
			<c:forEach items="${personList}" var="item">
				<div class="table-content">
					<div class="table-item">
						${item.username}
					</div>
					<div class="table-item">
						${item.name}
					</div>
					<div class="table-item">
						${item.age}
					</div>
					<div class="table-item">
						${item.teleno}
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="sub-container">
			<div class="table-title">
				<div class="title-item">USERNAME</div>
				<div class="title-item">NAME</div>
			</div>
			<c:forEach items="${userList}" var="item">
				<div class="table-content">
					<div class="table-item">
						${item.username}
					</div>
					<div class="table-item">
						${item.password}
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="detail-container">
			<div class="detail">
				<a href="index.jsp">返回数据库操作页面</a>
			</div>
		</div>
	</div>
  </body>
</html>