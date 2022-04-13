<%-- 
    Document   : index
    Created on : Mar 14, 2022, 11:12:39 PM
    Author     : ADMIN
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="container">
            <h1 class="text-center text-info">WEDDING-LIST</h1>
            <ul class="pagination">
                <c:forEach begin="1" end="${Math.ceil(productCounter/6)}" var="i">
                    <c:url value="/" var="myAction">
                        <c:param name="page" value="${i}"/>
                    </c:url>
                    <li class="page-item"><a class="page-link" href="${myAction}">${i}</a></li>
                    </c:forEach>
            </ul>
            <div class="row">
                <c:forEach items="${products}" var="p">
                    <div class="col-md-4 col-sm-12" style="padding:10px">
                        <div class="card" >
                            <img class="card-img-top" src="${p.images}" alt="${p.name}">
                            <div class="card-body">
                                <h4 class="card-title">${p.name}</h4>
                                <p class="card-text">${p.price} VND</p>
                                <a href="<c:url value="/products/${p.id}" />" class="btn btn-primary">See Details</a>
                                <a href="#"
                                   onclick="addToCart(${p.id}, '${p.name}', ${p.price})"
                                   class="btn btn-danger">Add to Cart</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div> 
