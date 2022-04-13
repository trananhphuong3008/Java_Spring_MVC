<%-- 
    Document   : cates-stats
    Created on : Apr 8, 2022, 9:22:07 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info">CATEGORY STATS</h1>

<div class="row">
    <div class="col-md-5">
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Number of products</th>
            </tr>

            <c:forEach items="${stats}" var="s">
                <tr>
                    <th>${s[0]}</th>
                    <th>${s[1]}</th>
                    <th>${s[2]}</th>
                </tr> 
            </c:forEach>
        </table>
    </div>
    <div class="col-md-7">
        <canvas id="myChart"></canvas>
    </div>
</div>

<script>
            let lables=[], data=[]
            <c:forEach items="${stats}" var="s">
                lables.push('${s[1]}')
                data.push(${s[0]})
            </c:forEach>
    window.onload = () =>{
        let ctx = document.getElementById("myChart").getContext("2d")
        drawPieChart(ctx, lables, data)
    }
</script>

