<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DatabaseUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="./css/index.css">
<title>test</title>
</head>
<body>
	<form action="AddServlet" method="post">
		<table id="gradient-style" summary="Meeting Results">
			<caption>数据库表person插入信息</caption>
		    <thead>
		    	<tr>
		        	<th scope="col">条目</th>
		            <th scope="col">值</th>
		        </tr>
		    </thead>
		    <tfoot>
		    	<tr>
		        	<td colspan="4">
		        		<input type="submit" class="btn-two yellow block">
		        	</td>
		        </tr>
		    </tfoot>
		    <tbody>
		    	<tr>
		        	<td>Username</td>
		            <td class="input-text">
		            	<input type="text" name="username" />
		            </td>
		        </tr>
		        <tr>
		        	<td>Name</td>
		            <td class="input-text">
		            	<input type="text" name="name" />
		            </td>
		        </tr>
		        <tr>
		        	<td>Age</td>
		            <td class="input-text">
		            	<input type="text" name="age" />
		            </td>
		        </tr>
		        <tr>
		        	<td>Telenum</td>
		            <td class="input-text">
		            	<input type="text" name="teleno" />
		            </td>
		        </tr>
		    </tbody>
		</table>
	</form>
	<form action="DeleteServlet" method="post">
		<table id="gradient-style" summary="Meeting Results">
			<caption>数据库表user删除信息</caption>
		    <thead>
		    	<tr>
		        	<th scope="col">条目</th>
		            <th scope="col">值</th>
		        </tr>
		    </thead>
		    <tfoot>
		    	<tr>
		        	<td colspan="4">
		        		<input type="submit" class="btn-two yellow block">
		        	</td>
		        </tr>
		    </tfoot>
		    <tbody>
		    	<tr>
		        	<td>Username</td>
		            <td class="input-text">
		            	<input type="text" name="username" />
		            </td>
		        </tr>
		    </tbody>
		</table>
	</form>
</body>
<%
	DatabaseUtil databaseUtil = new DatabaseUtil();
    application.setAttribute("databaseUtil",databaseUtil);
%>
</html>