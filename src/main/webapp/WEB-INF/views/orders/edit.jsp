<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.order.edit.js"/>"></script>

<div class="modal fade" id="ModalEditOrder" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title otstup">Редактирование туриста</h2>
            </div>
            <div class="modal-body">
                <form:form  id="editOrder">
                    <input type="hidden" name="idOrder" value="${order.idOrder}">
                    <table class="tableModal">
                        <tr>
                            <td><input name="orderNumber" id="orderNumberEdit" type="text" class="form-control input-control" required value="${order.orderNumber}"></td>
                            <td><input name="orderDate" id="orderDateEdit" class="form-control input-control" value="${order.orderDate}"></td>
                        </tr>
                        <tr>
                            <td><input id="client-searchEdit" type="text" class="form-control input-control" value="${order.client.nameRu}"></td>
                            <td><input id="tour-searchEdit" type="text" class="form-control input-control" value="${order.tour.nameTour}"></td>
                        </tr>
                        <tr>
                            <td><input  name="client.idClient" id="idClientEdit" class="form-control input-control" value="${order.client.idClient}"></td>
                            <td><input  name="tour.idTour" id="idTourEdit" class="form-control input-control" value="${order.tour.idTour}"></td>
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