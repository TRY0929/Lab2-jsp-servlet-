<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DatabaseUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>index</title>
	<link id="css" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}//css/index.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}//js/jQuery-3.5.1.js"></script>
	<script src="${pageContext.request.contextPath}//js/index.js"></script>
</head>
<body>
	<div class="container">
		<div class="sub-container">
			<div class="title">
				<h3 class=person-title-content>
					数据表person插入信息
				</h3>
			</div>
			<div class="table">
				<form action="AddServlet" method="post">
					<div class="item">
						<label for="username" class="item-name">username</label>
						<input class="item-input" type="text" name="username" id="username" autocomplete="off"
							placeholder="请输入长度4~12字符"
						 />
					</div>
					<div class="item">
						<label for="age" class="item-name">age</label>
						<input class="item-input" type="text" name="age" id="age" autocomplete="off"
							placeholder="请输入0~99岁之间的数字"
						/>
					</div>
					<div class="item">
						<label for="name" class="item-name">name</label>
						<input class="item-input" type="text" name="name" id="name" autocomplete="off"
							placeholder="请输入长度4~12字符"
						/>
					</div>
					<div class="item">
						<label for="teleno" class="item-name">teleno</label>
						<input class="item-input" type="text" name="teleno" id="teleno" autocomplete="off"
							placeholder="请输入11位手机号码"
						/>
					</div>
					<div class="submit">
						<input class="submit-btn" type="submit" class="btn-two yellow block" value="插入" disabled>
					</div>
				</form>
			</div>
			
		</div>
		<div class="sub-container">
			<div class="title">
				<h3 class=person-title-content>
					数据表user删除信息
				</h3>
			</div>
			<form action="DeleteServlet" method="post">
				<div class="item">
					<label for="username" class="item-name">username</label>
					<input class="item-input" type="text" name="username" id="username" autocomplete="off"
						placeholder="请输入长度4~12字符"
					/>
				</div>
				<div class="submit">
					<input class="submit-btn" type="submit" class="btn-two yellow block" value="删除" disabled>
				</div>
			</form>
		</div>
	</div>
	
</body>
<%
	DatabaseUtil databaseUtil = new DatabaseUtil();
    application.setAttribute("databaseUtil",databaseUtil);
%>
</html>