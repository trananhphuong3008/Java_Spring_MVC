<%-- 
    Document   : user-detail
    Created on : Apr 1, 2022, 5:27:31 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-info">USER DETAILS</h1>
<c:url value="/user/${pageContext.request.userPrincipal.name}"/>
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <div class="form-group">       
        <label>Avatar</label>
        <form:input type="file" path="file" class="form-control" />
        <img src="${pageContext.session.getAttribute("currentUser").avatar}" 
             class="rounded-cirlce" width="40"
             alt="${user.username}"/>
    </div>
<input type="submit" value="Update" class="btn btn-danger"/>        
</form:form>