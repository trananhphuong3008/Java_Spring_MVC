<%-- 
    Document   : revenue-stats
    Created on : Apr 8, 2022, 9:23:22 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center text-info">PRODUCT STATS</h1>

<div class="row">
    <div class="col-md-5">
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Revenue</th>
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
        <form>
            <div class="form-group">
                <input type="text" class="form-control" 
                       name="kw"
                       placeholder="Enter keyword..."/>
            </div>
            <div class="form-group">
                <input type="date" class="form-control" 
                       name="fromDate"/>
            </div>
            <div class="form-group">
                <input type="date" class="form-control" 
                       name="toDate"/>
            </div>
            <input type="submit" value="Filter" class="btn btn-danger"/>
        </form>
        <canvas id="myChart"></canvas>
    </div>
</div>

<script>
    let lables = [], data = []
    <c:forEach items="${stats}" var="s">
    lables.push('${s[1]}')
    data.push(${s[0]})
    </c:forEach>
    window.onload = () => {
        let ctx = document.getElementById("myChart").getContext("2d")
        drawBarChart(ctx, lables, data)
    }
</script>