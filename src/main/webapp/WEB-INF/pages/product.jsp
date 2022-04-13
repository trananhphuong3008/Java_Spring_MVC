<%-- 
    Document   : index
    Created on : Mar 14, 2022, 11:12:39 PM
    Author     : ADMIN
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-danger">WEDDING MANAGEMENT</h1>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>

</c:if>
<form:form method="post" action="" modelAttribute="product" enctype="multipart/form-data">
    <div class="form-group">
        <label>Wedding Name</label>
        <form:input path="name" class="form-control" />
    </div>
    <div class="form-group">
        <label>Wedding Price</label>
        <form:input path="price" class="form-control" />
    </div>
    <div class="form-group">
        <label>Wedding Image</label>
        <form:input type="file" path="file" class="form-control" />
    </div>
    <div class="form-group">
        <label>Wedding Category</label>
        <form:select path="categoryId" class="form-control">
            <c:forEach items="${categories}" var="c">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>

        </form:select>
    </div>
    <input type="submit" value="Add Product" class="btn btn-danger"/>
</form:form>
