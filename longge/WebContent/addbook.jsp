<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>

<s:form action="addbook">
	 ISBN: <input type="text" name="myBook.ISBN"><br/>
     Title: <input type="text" name="myBook.Title"><br/>
     AuthorID: <input type="number" name="myBook.AuthorID"><br/>
 	 Publisher: <input type="text" name="myBook.Publisher"><br/>
 	 PubpishDate: <input type="text" name="myBook.PublishDate"><br/>
 	 Price: <input type="number" name="myBook.Price"><br/>
    <s:submit value="Click" align="center" />
</s:form>
</body>
</html>