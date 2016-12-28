<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="tableOpen">
     <tr><td style="width: 150px">Адрес:</td><td><samp class="sampopen">${client.address}</samp></td></tr>
     <tr><td>Серия и номер:</td><td><samp class="sampopen">${client.seriesNumber}</samp></td></tr>
     <tr><td>Кем выдан:</td><td><samp class="sampopen">${client.received}</samp></td></tr>
     <tr><td>Когда выдан:</td><td><samp class="sampopen">${client.issueDate}</samp></td></tr>
     <tr><td>Срок действия:</td><td><samp class="sampopen">${client.expiryDate}</samp></td></tr>
</table>