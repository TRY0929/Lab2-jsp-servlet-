<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link id="css" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}//css/index.css">
</head>
<body>
	<div class="container">
		<div class="sub-container">
			<div class="result-title">
				<h3>数据库操作结果：</h3>
			</div>
			<c:choose>
		        <c:when test="${tableType==0&&operatorResult==0}">
		            <div class="result-item">
						person：<span class="username">${username}</span>
					&nbsp;插入失败
					</div>
		        </c:when>
		        <c:when test="${tableType==0&&operatorResult==1}">
		            <div class="result-item">
						person：<span class="username">${username}</span>
					&nbsp;插入成功
					</div>
		        </c:when>
		        <c:when test="${tableType==0&&operatorResult==2}">
		            <div class="result-item">
						person：<span class="username">${username}</span>
					&nbsp;更新失败
					</div>
		        </c:when>
		        <c:when test="${tableType==0&&operatorResult==3}">
		            <div class="result-item">
						person：<span class="username">${username}</span>
					&nbsp;更新成功
					</div>
		        </c:when>
		        <c:when test="${tableType==1&&operatorResult==0}">
		            <div class="result-item">
						person：<span class="username">${username}</span>
					&nbsp;删除失败
					</div>
		        </c:when>
		        <c:when test="${tableType==1&&operatorResult==1}">
		            <div class="result-item">
						person：<span class="username">${username}</span>
					&nbsp;删除成功
					</div>
		        </c:when>
		        <c:otherwise>
		            <h4>qaq</h4>
		        </c:otherwise>
		    </c:choose>
		    <div class="detail-container">
				<div class="detail">
					<a href="ShowServlet">click to see results.</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>