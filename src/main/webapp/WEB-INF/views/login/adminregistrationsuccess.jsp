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

	<div class="generic-container">
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			<a href="<c:url value='/admin' />">Админ панель</a>
		</span>
	</div>
</div>

<header-footer:footer/>