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
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <button id="btn-login" name="remember" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=693f9b4dd3aaa35932ab9fb9aee71d7e&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
            <image height="38" src="/image/kakao_login.png" /></a>
    </form>
</div>

<%@ include file="../layout/footer.jsp" %>
