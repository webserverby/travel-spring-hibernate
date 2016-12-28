<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="toursUrl" value="/tours"/>
<c:forEach items="${tours}" var="tour">
    <tr id="tr_${tour.idTour}" onclick="get_openTour(${tour.idTour})" style="cursor: pointer;" >
        <td>${tour.nameTour}</td>
        <td><fmt:formatDate  value="${tour.beginDate}" pattern="dd.MM.yyyy"/> - <fmt:formatDate  value="${tour.endDate}" pattern="dd.MM.yyyy"/></td>
        <td>${tour.personNumber}</td>
        <td>${tour.costHotel}</td>
        <td>${tour.costAvia}</td>
        <td>${tour.costTour}</td>
    </tr>
</c:forEach>
<c:if test="${empty tours}">
    <tr>
        <td colspan="5">Туры не найдены</td>
    </tr>
</c:if>