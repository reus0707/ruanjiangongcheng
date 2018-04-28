<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>addauthor</title>
</head>
<body>
there is no such author, please add a new one!
<s:form action="addauthor">
      AuthorID: <input type="number" name="myAuthor.AuthorID"><br>
      Name: <input type="text" name="myAuthor.Name"><br>
      Age: <input type="number" name="myAuthor.Age"><br>
      Country: <input type="text" name="myAuthor.Country"><br>
      <s:submit/>
</s:form>

</body>
</html>