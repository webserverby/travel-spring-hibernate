<!DOCTYPE html>
<%@ page language="java" contentType="text/html"	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="header-footer" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>

<header-footer:header/>

<div class="contain">

	<div class="divButton">
		<div class="form-inline clientButton">
			<a href="<c:url value='/admin/newuser' />" >
				<button class="btn btn-success btn-font" id="addBtn"><span class="glyphicon glyphicon-plus"></span> <spring:message code="btn.addUser"/></button>
			</a>
		</div>
	</div>

	<div class="generic-container">

		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Список пользователей</span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Имя</th>
				        <th>Фамилия</th>
				        <th>Email</th>
				        <th>SSO ID</th>
				        <th>Роли</th>
				        <sec:authorize access="hasRole('ADMIN')">
							<th width="140"></th>
							<th width="140"></th>
				        </sec:authorize>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.ssoId}</td>
						<td>
						<c:forEach items="${user.userProfiles}" var="userProfile">
							${userProfile.type},
						</c:forEach>
						</td>
					    <sec:authorize access="hasRole('ADMIN')">
						<td><a href="<c:url value='/admin/edit-user-${user.ssoId}' />" class="btn btn-warning btn-font"><span class="glyphicon glyphicon-pencil"></span>&nbsp; <spring:message code="btn.edit"/></a></td>
						<td><a href="<c:url value='/admin/delete-user-${user.ssoId}' />" class="btn btn-danger btn-font"><span class="glyphicon glyphicon-trash"></span>&nbsp; <spring:message code="btn.delete"/></a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
				<c:forEach items="${users2}" var="userProfile">
					<tr>
						<td>${userProfile.type}</td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>

   	</div>
</div>




<header-footer:footer/>