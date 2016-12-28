<%@ page language="java" contentType="text/html"	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="header-footer" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>

<header-footer:header/>
<script src="<c:url value="/resources/js/moment.js"/>"></script>
<script src="<c:url value="/resources/js/moment-with-locales.js"/>"></script>
<script src="<c:url value="/resources/js/Chart.min.js"/>"></script>
<script src="<c:url value="/resources/js/Chart.bundle.min.js"/>"></script>
<script src="<c:url value="/resources/js/utils.js"/>"></script>

<script src="<c:url value="/resources/js/myScript/my.index.grafik.js"/>"></script>

    <div class="contain" id=”tooltip”>

        <div class="index-perk">
            <label class="float-index">
                <div class="div1-index">
                    <div class="div2-index"><spring:message code="page.indexNumberOrder"/></div>
                    <div class="div3-index-otstup">${orders.size()}</div>
                </div>
            </label>
            <label class="float-index">
                <div class="div1-index">
                    <div class="div2-index"><spring:message code="page.indexNumberTour"/></div>
                    <div class="div3-index-otstup">${tours.size()}</div>
                </div>
            </label>
            <label class="float-index">
                <div class="div1-index">
                    <div class="div2-index"><spring:message code="page.indexNumberTourist"/></div>
                    <div class="div3-index-otstup">${clients.size()}</div>
                </div>
            </label>
            <label class="float-index">
                <div class="div1-index">
                    <div class="div2-index"><spring:message code="page.indexNumberLn"/></div>
                    <div class="div3-index">
                        <div><a href="?lang=en">Английский(EN)</a></div>
                        <div><a href="?lang=ru">Русский(RU)</a></div>
                    </div>
                </div>
            </label>
        </div>

        <div>
            <div class="grafik" style="width:49%;">
                <canvas id="grOrder"></canvas>
            </div>
            <div class="grafik" style="width:49%;">
                <canvas id="grClient"></canvas>
            </div>
        </div>
        <div>
            <div class="grafik" style="width:49%;">
                <canvas id="grFinans"></canvas>
            </div>
            <div class="grafik" style="width:49%;">
                <canvas id="grFinansMonth"></canvas>
            </div>
        </div>

    <div id="addClient" class="div1-index-report-button">
        <button type="button" class="btn btn-font" onclick="get_report_order()" id="addBtnReport"><span class="glyphicon glyphicon-usd"></span> Прибыль фирмы за неделю</button>
    </div>

        <div class="div1-index-report">
            <div class="div3-index-report">
        <table id="loadTable" class="table table-hover">
            <thead>
            <tr>
                <td><spring:message code="page.orderNumberSearch"/></td>
                <td><spring:message code="page.orderDate"/></td>
                <td><spring:message code="page.fullname"/></td>
                <td>Вылет</td>
                <td>Прилет</td>
                <td><spring:message code="page.costTour"/>, Br</td>
            </tr>
            </thead>
            <tbody id="tbody">
            <tr style="background: #FFE1E1; font-weight: bold; color: #800000;"><td colspan="4">Итого результатов: ${orderReport.size()}</td><td>Стоимость:</td><td>${orderReportSum}</td></tr>
            <c:forEach items="${orderReport}" var="order">
                <tr id="tr_${order.idOrder}" style="cursor: pointer;" >
                    <td>${order.orderNumber}</td>
                    <td><fmt:formatDate  value="${order.orderDate}" pattern="dd.MM.yyyy"/></td>
                    <td>${order.client.nameRu}</td>
                    <td><fmt:formatDate  value="${order.tour.beginDate}" pattern="dd.MM.yyyy"/></td>
                    <td><fmt:formatDate  value="${order.tour.endDate}" pattern="dd.MM.yyyy"/></td>
                    <td style="color: #800000;">${order.costOrder}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            </div>
        </div>

    </div>

<header-footer:footer/>

