<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <script src="<c:url value="/resources/js/myScript/my.index.js"/>"></script>

    <!-- datepicker -->
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css"/>">
    <script src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.ui.datepicker-ru.js"/>"></script>

    <!-- inputmask скрипт для ввода даты и телефона-->
    <script src="<c:url value="/resources/js/jquery.inputmask.js"/>"></script>
    <script src="<c:url value="/resources/js/inputmask.date.extensions.js"/>"></script>

    <!-- Autocomplete -->
    <link rel="stylesheet" href="<c:url value="/resources/css/autocomplete.css"/>">
    <script src="<c:url value="/resources/js/jquery.autocomplete.min.js"/>"></script>

    <!-- Уведомления -->
    <script src="<c:url value="/resources/js/jquery.noty.packaged.js"/>"></script>

    <!-- Стиль иконки -->
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

    <!-- Сумма прописью -->
    <script src="<c:url value="/resources/js/myScript/sum_propis.js"/>"></script>

    <!-- Галерея изображений -->
    <script src="<c:url value="/resources/js/jquery.fancybox.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.mousewheel-3.0.6.pack.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/fancybox.css"/>">

    <!-- Анимация загрузки ajax -->
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.faloading.css"/>">
    <script src="<c:url value="/resources/js/jquery.faloading.js"/>"></script>

</head>

<body>

<!-- Кнопка прокрутки сайта  -->
<div class="scrollup">
    <!-- Иконка fa-chevron-up (Font Awesome) -->
    <i class="fa fa-chevron-up"></i>
</div>

<div class="container" id="page">

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="<c:url value="/"/>" id="log" ><img src="/resources/images/bon_8.png" class="img-responsive" alt="Blockchain"></a>
                <a class="navbar-brand" href="<c:url value="/"/>">Bon Voyage!</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/"/>"><spring:message code="menu.home"/></a></li>
                    <li><a href="<c:url value="/clients/client"/>"><spring:message code="menu.tourist"/></a></li>
                    <li><a href="<c:url value="/tours/tour"/>"><spring:message code="menu.tour"/></a></li>
                    <li><a href="<c:url value="/orders/order"/>"><spring:message code="menu.order"/></a></li>
                    <li><a href="<c:url value="/client"/>"><spring:message code="menu.booker"/></a></li>
                    <li><a href="<c:url value="/client"/>"><spring:message code="menu.worker"/></a></li>
                    <li class="time"><span id=tick2></span><p><span id=tick3></span></p></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="" data-toggle="dropdown" class="dropdown-toggle"><span class="glyphicon glyphicon-user"></span> ${loggedinuser} <b class="caret"></b></a>
                        <ul role="menu" class="dropdown-menu">
                            <sec:authorize access="hasRole('ADMIN')">
                                <li><a href="<c:url value="/admin"/>"><i class="fa fa-cog" aria-hidden="true"></i> <spring:message code="menu.settings"/></a></li>
                                <li><a href="<c:url value="/resources/logging/log4j_logs.log"/>"><i class="fa fa-eye" aria-hidden="true"></i> <spring:message code="menu.logging"/></a></li>
                                <li class="divider"></li>
                            </sec:authorize>
                            <li><a href="<c:url value="/logout"/>"><i class="fa fa-sign-out" aria-hidden="true"></i> <spring:message code="menu.exit"/></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>