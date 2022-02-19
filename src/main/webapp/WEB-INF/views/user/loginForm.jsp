<%--
Created by IntelliJ IDEA.
User: Moon
Date: 2022-02-19
Time: 오후 4:08
To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
    </form>
</div>
<button id="btn-login" class="btn btn-primary">로그인</button>
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>
