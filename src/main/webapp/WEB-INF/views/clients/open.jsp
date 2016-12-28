<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="modal fade" id="ModalOpen" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog modal-lg">
          <div class="modal-content">
               <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title otstupopen">${client.nameRu}</h2>
                    <div class="col-md-6 sampopen">
                         <c:if test="${client.personSex == 'Мужской'}">
                              <i class="fa fa-male" aria-hidden="true"></i><i class="fa fa-mars" aria-hidden="true"></i>&nbsp; ${client.nameEn}
                         </c:if>
                         <c:if test="${client.personSex == 'Женский'}">
                              <i class="fa fa-female" aria-hidden="true"></i><i class="fa fa-venus" aria-hidden="true"></i>&nbsp; ${client.nameEn}
                         </c:if>
                    </div>
                    <div class="col-md-6 sampopen"><i class="fa fa-phone" aria-hidden="true"></i>&nbsp;  ${client.phoneMobile}</div>
                    <div class="col-md-6 sampopen"><i class="fa fa-birthday-cake" aria-hidden="true"></i>&nbsp; <fmt:formatDate  value="${client.birthDate}" pattern="dd.MM.yyyy"/></div>
                    <div class="col-md-6 sampopen"><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp; ${client.mail}</div>
               </div>

               <ul class="nav nav-tabs">
                    <li><a onclick="get_basicOpen(${client.idClient})">О туристе</a></li>
                    <li><a onclick="get_orderOpen(${client.idClient})">Заказы</a></li>
                    <li><a onclick="get_tourOpen(${client.idClient})">Туры</a></li>
                    <li><a onclick="get_documentOpen(${client.idClient})">Документы</a></li>
               </ul>

               <div class="modal-body" id="addTabs">
                    <table class="tableOpen">
                         <tr><td style="width: 150px">Адрес:</td><td><samp class="sampopen">${client.address}</samp></td></tr>
                         <tr><td>Серия и номер:</td><td><samp class="sampopen">${client.seriesNumber}</samp></td></tr>
                         <tr><td>Кем выдан:</td><td><samp class="sampopen">${client.received}</samp></td></tr>
                         <tr><td>Когда выдан:</td><td><samp class="sampopen">${client.issueDate}</samp></td></tr>
                         <tr><td>Срок действия:</td><td><samp class="sampopen">${client.expiryDate}</samp></td></tr>
                    </table>
               </div>
               <div class="modal-footer">
                    <a id="removeOpen" onclick="get_remove(${client.idClient})" class="btn btn-danger btn-font"><span class="glyphicon glyphicon-trash"></span></a>
                    <a onclick="get_edit(${client.idClient})" class="btn btn-warning btn-font"><span class="glyphicon glyphicon-pencil"></span></a>
                    <button type="button" class="btn btn-primary btn-font" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
               </div>
          </div>
     </div>
</div>
