<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.client.add.js"/>"></script>

<div class="modal fade" id="ModalAdd" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog modal-lg">
          <div class="modal-content">
               <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title otstup">Добавление туриста</h2>
               </div>
               <div class="modal-body">
                    <form:form id="addClient" >

                         <div>
                              <table class="tableModal">
                                   <tr>
                                        <td><input type="text" name="nameRu" id="nameRuInput" class="css-input" placeholder="ФИО" required></td>
                                        <td><input type="text" name="address" id="addressInput" class="css-input" placeholder="Адрес"></td>
                                   </tr>
                                   <tr>
                                        <td><input type="text" name="nameEn" id="nameEnInput" class="css-input" placeholder="ФИО лат."></td>
                                        <td><input type="text" name="seriesNumber" id="seriesNumberInput" class="css-input" placeholder="Серия и номер"></td>
                                   </tr>
                                   <tr>
                                        <td>
                                             <select name="personSex" class="select_style">
                                                  <option value="Мужской">Мужской</option>
                                                  <option value="Женский">Женский</option>
                                             </select>
                                        </td>
                                        <td><input type="text" name="received" id="receivedInput" class="css-input" placeholder="Кем выдан"></td>
                                   </tr>
                                   <tr>
                                        <td><input name="birthDate" id="birthDateInput" class="css-input" placeholder="Дата рождения"></td>
                                        <td><input  type="text" name="issueDate" id="issueDateInput" class="css-input" placeholder="Когда выдан"></td>
                                   </tr>
                                   <tr>
                                        <td><input type="text" name="phoneMobile" id="phoneMobileInput" class="css-input" placeholder="Телефон"></td>
                                        <td><input type="text" name="expiryDate" id="expiryDateInput" class="css-input" placeholder="Срок действия"></td>
                                   </tr>
                                   <tr>
                                        <td><input type="text" name="mail" id="mailInput" class="css-input" placeholder="E-mail"></td>
                                        <td><input name="regDate" id="regDateInput" class="css-input" placeholder="Дата регистрации"></td>
                                   </tr>
                              </table>
                              <div class="modal-footer">
                                   <button type="submit" class="btn btn-success btn-font"><span class="glyphicon glyphicon-ok"></span></button>
                                   <button type="button" class="btn btn-primary btn-font" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
                              </div>

                         </div>

                    </form:form>
               </div>
          </div>
     </div>
</div>

