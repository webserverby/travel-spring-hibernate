<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" href="<c:url value="/resources/css/my.tour.document.css"/>">

<div class="panel panel-default" id="login-box">
<form:form id="addDocument" method="POST" modelAttribute="multiFileBucket" enctype="multipart/form-data" class="form-horizontal">
     <div class="panel-body">
          <div class="msg">Фотографии отеля(5 шт.) сохранены во временной папке<br><i>"D://TourImage/temporal/".</i>
               Можете загрузить их или выбрать другие.</div>
          <div class="error">После сохранения тура, фотографии из временной папки будут удалены!</div>

          <div class="row">
               <c:forEach var="v" varStatus="vs" items="${multiFileBucket.files}">
                    <form:input type="file" path="files[${vs.index}].file" id="files[${vs.index}].file" class="form-control control"/>
                    <div class="has-error">
                         <form:errors path="files[${vs.index}].file" class="help-inline"/>
                    </div>
               </c:forEach>
          </div>

     </div>
     <div class="panel-footer">
          <div class="form-actions">
               <button type="submit" class="btn btn-success btn-font btnDoc"><span class="glyphicon glyphicon-ok"></span></button>
          </div>
     </div>
     </form:form>
</div>
