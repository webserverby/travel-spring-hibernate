<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="orderResults">
    <div class="numbertyr">
        <spring:message code="menu.order"/> <span class="badge">${orders.size()}</span>
    </div>

    <c:url var="ordersUrl" value="/orders"/>
    <table id="loadTable" class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="page.orderNumberSearch"/></th>
            <th><spring:message code="page.orderDate"/></th>
            <th><spring:message code="page.fullname"/></th>
            <th>Вылет</th>
            <th>Прилет</th>
            <th><spring:message code="page.costTour"/>, Br</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${orders}" var="order">
            <tr id="tr_${order.idOrder}" onclick="get_openOrder(${order.idOrder})" style="cursor: pointer;" >
                <td>${order.orderNumber}</td>
                <td><fmt:formatDate  value="${order.orderDate}" pattern="dd.MM.yyyy"/></td>
                <td>${order.client.nameRu}</td>
                <td><fmt:formatDate  value="${order.tour.beginDate}" pattern="dd.MM.yyyy"/></td>
                <td><fmt:formatDate  value="${order.tour.endDate}" pattern="dd.MM.yyyy"/></td>
                <td>${order.costOrder}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty orders}">
            <tr>
                <td colspan="5">Заказы не найдены</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <div class="buttonGroup">
        <ul class="pager">
            <c:if test="${searchCriteria.page > 0}">
                <li><a id="prevResultsLink"  class="ajaxLink"
                       href="${ordersUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page - 1}"><< ${searchCriteria.page +1}</a></li>
            </c:if>
            <c:if test="${not empty orders && fn:length(orders) == searchCriteria.pageSize}">
                <li><a id="moreResultsLink" class="ajaxLink"
                       href="${ordersUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page + 1}">${searchCriteria.page +1} >> ${searchCriteria.page + 2}</a></li>
            </c:if>
        </ul>
    </div>

</div>