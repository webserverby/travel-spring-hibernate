<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="order-client">
    <input  name="client.idClient" type="hidden" value="${client.idClient}">
    <div class="btnTourClient">
        <button type="button" onclick="get_client_delete()" class="btn btn-danger btn-avia"><span class="glyphicon glyphicon-trash"></span></button>
    </div>
    <div class="order-client-header" onclick="get_open_client(${client.idClient})">${client.nameRu}</div>
    <div onclick="get_open_client(${client.idClient})">
        <div class="col-md-6 sampopen">
            <c:if test="${client.personSex == 'Мужской'}">
                <i class="fa fa-male" aria-hidden="true"></i><i class="fa fa-mars" aria-hidden="true"></i>&nbsp; ${client.nameEn}
            </c:if>
            <c:if test="${client.personSex == 'Женский'}">
                <i class="fa fa-female" aria-hidden="true"></i><i class="fa fa-venus" aria-hidden="true"></i>&nbsp; ${client.nameEn}
            </c:if>
        </div>
        <div class="col-md-6 sampopen"><i class="fa fa-phone" aria-hidden="true"></i>&nbsp; ${client.phoneMobile}</div>
        <div class="col-md-6 sampopen"><i class="fa fa-birthday-cake" aria-hidden="true"></i>&nbsp; <fmt:formatDate  value="${client.birthDate}" pattern="dd.MM.yyyy"/></div>
        <div class="col-md-6 sampopen"><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp; ${client.mail}</div>
    </div>
</div>