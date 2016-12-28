<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="tourResults">
    <div class="numbertyr">
        <spring:message code="menu.tour"/> <span class="badge">${tours.size()}</span>
    </div>

    <c:url var="toursUrl" value="/orders/tours"/>
    <table id="loadTable" class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="page.nameTour"/></th>
            <th><spring:message code="page.beginDate"/></th>
            <th><spring:message code="page.costTour"/>, Br</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${tours}" var="tour">
            <tr id="tr_${tour.idTour}" onclick="get_add_tour(${tour.idTour})" style="cursor: pointer;" >
                <td>${tour.nameTour}</td>
                <td><fmt:formatDate  value="${tour.beginDate}" pattern="dd.MM.yyyy"/> - <fmt:formatDate  value="${tour.endDate}" pattern="dd.MM.yyyy"/></td>
                <td>${tour.costTour}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty tours}">
            <tr>
                <td colspan="5">Туры не найдены</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <div class="buttonGroup">
        <ul class="pager">
            <c:if test="${searchCriteria.page > 0}">
                <li><a id="prevResultsLink"  class="ajaxLinkTour"
                       href="${toursUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page - 1}"><< ${searchCriteria.page +1}</a></li>
            </c:if>
            <c:if test="${not empty tours && fn:length(tours) == searchCriteria.pageSize}">
                <li><a id="moreResultsLink" class="ajaxLinkTour"
                       href="${toursUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page + 1}">${searchCriteria.page +1} >> ${searchCriteria.page + 2}</a></li>
            </c:if>
        </ul>
    </div>
</div>