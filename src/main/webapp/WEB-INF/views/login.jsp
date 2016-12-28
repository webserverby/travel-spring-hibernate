<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Бон Вояж</title>

    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png">
    <!-- Основные js и css -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/my.style.css"/>">
    <script src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">

</head>

<body>
<div class="container">
<div class="contain">

    <form:form name='loginForm' action="${pageContext.servletContext.contextPath}/login" method='POST'>

        <div class="panel panel-default" id="login-box">

            <div class="panel-heading">
                <div class="lang">
                    <a href="?lang=en">EN</a>
                    <a href="?lang=ru">RU</a>
                </div>
                <div class="log-message">
                    <p>${title}</p>
                    <p>${message}</p>
                </div>
            </div>
            <div class="panel-body">

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="username"><i class="fa fa-user fa-lg"></i></label>
                    <input type="text" class="css-input-login" id="username" name="ssoId" placeholder="<spring:message code="login.username"/>" required>
                </div>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="password"><i class="fa fa-lock fa-lg"></i></label>
                    <input type="password" class="css-input-login" id="password" name="password" placeholder="<spring:message code="login.password"/>" required>
                </div>
                <div class="checkbox-lang">

                        <input type="checkbox" id="rememberme" name="remember-me" class="css-checkbox">
                        <label for="rememberme" class="css-label"><spring:message code="login.remember"/></label>

                </div>

            </div>
            <div class="panel-footer">

                <div class="form-actions">
                    <button class="btn btn-block btn-info btn-font" type="submit"><spring:message code="login.login"/></button>
                </div>

            </div>
        </div>
                    <!--
                    При включенной защите csrf() используемый form:form сам отправляет token
                    Вместо <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
                    -->
    </form:form>

</div>

</div>
</body>
</html>