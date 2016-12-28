<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src="<c:url value="/resources/js/myScript/my.client.document.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/my.tour.document.css"/>">
<div class="panel panel-default" id="login-box">
          <div class="panel-body">
               <div class="msg">
                    <div id="listDocuments">
                         <table class="table table-hover">
                              <thead>
                              <tr>
                                   <th>№.</th>
                                   <th>Название</th>
                                   <th>Тип</th>
                                   <th>Описание</th>
                                   <th></th>
                                   <th></th>
                                   <th></th>

                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${documents}" var="doc" varStatus="counter">
                                   <tr>
                                        <td>${counter.index + 1}</td>
                                        <td>${doc.name}</td>
                                        <td>${doc.type}</td>
                                        <td>${doc.description}</td>

                                        <td><a href="<c:url value='/clients/eye-document-${doc.idDocument}' />" target="_blank" class="btn btn-success"><span class="glyphicon glyphicon-eye-open"></span></a></td>
                                        <td><a href="<c:url value='/clients/download-document-${doc.idDocument}' />" class="btn btn-success"><span class="glyphicon glyphicon-download-alt"></span></a></td>
                                        <td><a onclick="get_documentDelete(${client.idClient}, ${doc.idDocument})" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a></td>
                                   </tr>
                              </c:forEach>
                              </tbody>
                         </table>
                    </div>
               </div>

               <form:form id="addDocument" method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
                    <div class="row">
                         <form:input type="file" path="file" id="file" class="form-control control"/>
                         <div class="has-error">
                              <form:errors path="file" class="help-inline"/>
                         </div>
                    </div>
                    <div class="row">
                         <form:input type="text" path="description" id="description" placeholder="Описание" class="form-control control"/>
                    </div>

               <div class="panel-footer">
                    <div class="form-actions">
                         <button type="submit" onclick="get_documentForm(${client.idClient})" class="btn btn-success btn-font btnDoc"><span class="glyphicon glyphicon-ok"></span></button>
                    </div>
               </div>
               </form:form>
          </div>

</div>



