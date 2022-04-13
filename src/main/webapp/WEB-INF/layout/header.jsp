<%-- 
    Document   : header
    Created on : Mar 21, 2022, 4:57:29 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <!-- Brand -->
    <a class="navbar-brand" href="<c:url value="/"  />">WEDDING-Website</a>

    <!-- Toggler/collapsibe Button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Navbar links -->
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/" />">Home</a>
            </li>
            <c:forEach items="${categories}" var="c">
                <li class="nav-item" >
                    <c:url value="/" var="cateFilter">
                        <c:param name="categoryId" value="${c.id}"/>
                    </c:url>
                    <a class="nav-link" href="${cateFilter}">${c.name}</a>
                </li> 
            </c:forEach>
            <li class="nav-item" >
                <a class="nav-link text-info" href="<c:url value="/cart"/>">CART <span class="badge badge-danger cartCounter">${cartStats.totalQuantity}</span></a>
            </li>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li class="nav-item" >
                    <a class="nav-link text-success" href="<c:url value="/register"/>">Register</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link text-success" href="<c:url value="/login"/>">Login</a>
                </li> 
            </c:if>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <li class="nav-item" >
                    <a class="nav-link text-success" href="<c:url value="/user/${pageContext.request.userPrincipal.name}"/>">
                        <img src="${pageContext.session.getAttribute("currentUser").avatar}" class="rounded-cirlce" width="40"/>
                        
                        ${pageContext.request.userPrincipal.name}</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link text-success" href="<c:url value="/logout"/>">Logout</a>
                </li> 

            </c:if>  

        </ul>
    </div>
    <c:url value="/" var="homeAction" />
    <form class="form-inline" action="${homeAction}">
        <input class="form-control mr-sm-2" 
               name="kw"
               type="text" 
               placeholder=" Nhap tu khoa....">
        <button class="btn btn-success" type="submit">Search</button>
    </form>
</nav>
