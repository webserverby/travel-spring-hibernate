<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="clientsUrl" value="/clients"/>
<c:forEach items="${clients}" var="client">
    <tr id="tr_${client.idClient}" onclick="get_open(${client.idClient})" style="cursor: pointer;" >
        <td>${client.nameRu}</td>
        <td>${client.phoneMobile}</td>
        <td>${client.mail}</td>
        <td><fmt:formatDate  value="${client.regDate}" pattern="dd.MM.yyyy"/></td>
    </tr>
</c:forEach>

<c:if test="${empty clients}">
    <tr>
        <td colspan="5">Клиенты не найдены</td>
    </tr>
</c:if>