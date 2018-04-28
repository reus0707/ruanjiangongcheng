<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>
<br/>
<s:form action="home">
<s:url var="myUrl" value="images/home.png" />
<s:submit type="image" width="50" height="50" src="%{#myUrl}" />
</s:form>
<div style="text-align:center">

<article>
  <h1>Name:<font size="6" face="times new roman" color=blue><s:property value = "myAuthor.Name"/></font></h1>
  <h1>AuthorID:<font size="6" face="times new roman" color=blue><s:property value = "myAuthor.AuthorID"/></font></h1>
  <h1>Age:<font size="6" face="times new roman" color=blue><s:property value = "myAuthor.Age"/></font></h1>
  <h1>Country:<font size="6" face="times new roman" color=blue><s:property value = "myAuthor.Country"/></font></h1>
</article>
<s:iterator value="Booklist">
<details>
	  <summary> <font size="6" face="Arial" color=black><s:property value = "Title"/></font></summary>
	  <p> ISBN:<s:property value = "ISBN"/></p>
	  <p> AuthorID:<s:property value = "AuthorID"/></p>
	  <p> Publisher:<s:property value = "Publisher"/></p>
	  <p> PublishDate:<s:property value = "PublishDate"/></p>
	  <p> Price:<s:property value = "Price"/></p>
		<s:url action="delete" var="url" >
		<s:param name="name" value="%{ISBN}" />
		</s:url>
		<s:a href="%{url}">delete</s:a>

		
</details>
</s:iterator>
</div>
</body>
</html>