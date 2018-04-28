<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/booklist.css">
</head>


<body >
<a href="<s:url action="home"/>"><img src="images/return.jpg" style="position:fixed"/></a>
<div style="text-align:center">
<div class="log">
<img src="images/timg.jpg"  />
</div>
<s:iterator value="Booklist">

<div class="tooltip"> <font size="8" face="times new roman" color=#F75000><s:property value = "Title"/></font>
   <span >
    <br/>
    ISBN:<s:property value = "ISBN"/><br/>
    Publisher:<s:property value = "Publisher"/><br/>
	PublishDate:<s:property value = "PublishDate"/><br/>
	Price:<s:property value = "Price"/><br/>
   </span>
</div>
<br/>
<br/>
<s:url action="toauthor" var="urlTag" >
<s:param name="authorid" value="%{AuthorID}" />
</s:url>
<s:a href="%{urlTag}">AuthorID:<s:property value = "AuthorID"/></s:a>

<br/>
<s:url action="delete" var="url" >
<s:param name="name" value="%{ISBN}" />
</s:url>
<s:a href="%{url}">删除本书</s:a>

<%-- 两种删除方法 --%>
<br/>
<form action="delete">
	<button type="submit"  name="name" value="<s:property value="ISBN"/>"  style=color:red  onclick="alert('删除成功！')"  >删除本书</button>
</form>

<br/>
<br/><br/>
</s:iterator>
</div>

</body>
</html>
