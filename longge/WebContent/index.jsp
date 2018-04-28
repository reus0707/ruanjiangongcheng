<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<link rel="stylesheet" type="text/css" href="css/index.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
</head>

<ul>
  <li><a href="<s:url action="toaddbook"/>">add new book</a></li>
  <li><a href="<s:url action="showall"/>">show all books</a></li>
</ul>
<br/>
<br/>
<br/>
<br/>
<br/>
<h1 id="borderimg3" align="center">Welcome to book library</h1>
<br/>
<br/>
<div style="width:100%;text-align:center">
	<form action="query">
		<input type="text" name="myBook.Title"/>
	    <input type="submit" value="搜索一下"/>
	</form>
</div>
</body>
</html>