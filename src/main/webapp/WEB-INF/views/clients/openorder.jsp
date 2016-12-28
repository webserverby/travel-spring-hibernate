<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table id="loadTable" class="table table-hover">
     <thead>
     <tr>
          <th><spring:message code="page.orderNumberSearch"/></th>
          <th><spring:message code="page.orderDate"/></th>
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