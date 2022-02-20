<%--
Created by IntelliJ IDEA.
User: Moon
Date: 2022-02-19
Time: 오전 1:02
To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp" %>
<div>
    <c:forEach var="board" items="${boards}">
    <div class="container">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="#" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="layout/footer.jsp" %>
