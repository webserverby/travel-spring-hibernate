<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.order.add.js"/>"></script>

<div class="modal fade" id="ModalAddOrder" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title otstup">Добавление заказа</h2>
            </div>
            <div class="modal-body">

                <form:form id="addOrder" >
                    <div>
                        <div>
                            <div id="addClient" class="add-client-order">
                                <button type="button" class="btn btn-font" onclick="get_search_client()" id="addBtnClientOrder"><span class="glyphicon glyphicon-plus"></span> Покупателя</button>
                            </div>
                            <div id="addTourOrder" class="add-tour-order">
                                <button type="button" class="btn btn-font" onclick="get_search_tour()" id="addBtnTourOrder"><span class="glyphicon glyphicon-plus"></span> Тур</button>
                            </div>
                        </div>

                        <div id="addOrderClient"></div>
                        <div id="addOrderTour"></div>


                        <div class="div-orderService">
                        <c:forEach items="${serviceList}" var="serviceOrder">
                            <label class="float">
                                <input type="checkbox" name="serviceOrders" class="none" multiple="true" value="${serviceOrder.idService}">
                                <div class="div1">
                                    <div class="div2">${serviceOrder.nameService}</div>
                                    <div class="div3">${serviceOrder.descriptionService}</div>
                                    <div class="div4">${serviceOrder.costService} Br</div>
                                </div>
                            </label>
                        </c:forEach>
                        </div>


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
