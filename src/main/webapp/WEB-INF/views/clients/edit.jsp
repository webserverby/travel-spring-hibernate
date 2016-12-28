<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.client.edit.js"/>"></script>

<div class="modal fade" id="ModalEdit" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog modal-lg">
          <div class="modal-content">
               <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title otstup">Редактирование туриста</h2>
               </div>
               <div class="modal-body">
                    <form:form  id="editClient">

                         <input type="hidden" name="idClient" value="${client.idClient}">

                         <table class="tableModal">
                              <tr>
                                   <td><input type="text" name="nameRu" class="form-control input-control" required value="${client.nameRu}"></td>
                                   <td><input type="text" name="address" class="form-control input-control" value="${client.address}"></td>
                              </tr>
                              <tr>
                                   <td><input type="text" name="nameEn" class="form-control input-control" value="${client.nameEn}"></td>
                                   <td><input type="text" name="seriesNumber" class="form-control input-control" value="${client.seriesNumber}"></td>
                              </tr>
                              <tr>
                                   <td>
                                        <select name="personSex" class="form-control input-control">
                                             <c:if test="${client.personSex == 'Мужской'}">
                                                  <option value="Мужской" selected>Мужской</option>
                                                  <option value="Женский">Женский</option>
                                             </c:if>
                                             <c:if test="${client.personSex == 'Женский'}">
                                                  <option value="Мужской">Мужской</option>
                                                  <option value="Женский" selected>Женский</option>
                                             </c:if>
                                             <c:if test="${client.personSex != 'Мужской' && client.personSex != 'Женский'}">
                                                  <option value="Мужской">Мужской</option>
                                                  <option value="Женский">Женский</option>
                                             </c:if>
                                        </select>
                                   </td>
                                   <td><input type="text" name="received" class="form-control input-control" value="${client.received}"></td>
                              </tr>
                              <tr>
                                   <td><input name="birthDate" id="birthDateEdit" class="form-control input-control" value="${client.birthDate}"></td>
                                   <td><input  type="text" name="issueDate" id="issueDateEdit" class="form-control input-control" value="${client.issueDate}"></td>
                              </tr>
                              <tr>
                                   <td><input type="text" name="phoneMobile" id="phoneMobileEdit" class="form-control input-control" value="${client.phoneMobile}"></td>
                                   <td><input type="text" name="expiryDate" id="expiryDateEdit" class="form-control input-control" value="${client.expiryDate}"></td>
                              </tr>
                              <tr>
                                   <td><input type="text" name="mail" class="form-control input-control" value="${client.mail}"></td>
                                   <td><input name="regDate" id="regDateEdit" class="form-control input-control" value="${client.regDate}"></td>
                              </tr>
                         </table>
                         <div class="modal-footer">
                              <button type="submit" class="btn btn-success btn-font"><span class="glyphicon glyphicon-ok"></span></button>
                              <button type="button" class="btn btn-primary btn-font" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
                         </div>
                    </form:form>
               </div>
          </div>
     </div>
</div>