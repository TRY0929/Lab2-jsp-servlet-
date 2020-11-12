<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div> 数据库操作结果： </div>
	<c:choose>
        <c:when test="${tableType==0&&operatorResult==0}">
            <div>person：${username}&nbsp;插入失败</div>
        </c:when>
        <c:when test="${tableType==0&&operatorResult==1}">
            <div>person：${username}&nbsp;插入成功</div>
        </c:when>
        <c:when test="${tableType==0&&operatorResult==2}">
            <div>person：${username}&nbsp;更新失败</div>
        </c:when>
        <c:when test="${tableType==0&&operatorResult==3}">
            <div>person：${username}&nbsp;更新成功</div>
        </c:when>
        <c:when test="${tableType==1&&operatorResult==0}">
            <div>user：${username}&nbsp;删除失败</div>
        </c:when>
        <c:when test="${tableType==1&&operatorResult==1}">
            <div>user：${username}&nbsp;删除成功</div>
        </c:when>
        <c:otherwise>
            <h4>qaq</h4>
        </c:otherwise>
    </c:choose>
	<a href="ShowServlet">click to see results.</a>
</body>
</html>