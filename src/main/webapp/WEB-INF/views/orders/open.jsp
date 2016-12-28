<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="modal fade" id="ModalOpenOrder" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class=" nameHeader">${order.orderNumber}</div>
                <div><i class="fa fa-calendar" aria-hidden="true"></i>&nbsp;  <samp class="sampopen"><fmt:formatDate  value="${order.orderDate}" pattern="dd.MM.yyyy"/></samp></div>
            </div>

            <div class="order-client-open">
                <div class="order-client-header" onclick="get_open_client(${order.client.idClient})">${order.client.nameRu}</div>
                <div onclick="get_open_client(${order.client.idClient})">
                    <div class="col-md-6 sampopen">
                        <c:if test="${order.client.personSex == 'Мужской'}">
                            <i class="fa fa-male" aria-hidden="true"></i><i class="fa fa-mars" aria-hidden="true"></i>&nbsp; ${order.client.nameEn}
                        </c:if>
                        <c:if test="${order.client.personSex == 'Женский'}">
                            <i class="fa fa-female" aria-hidden="true"></i><i class="fa fa-venus" aria-hidden="true"></i>&nbsp; ${order.client.nameEn}
                        </c:if>
                    </div>
                    <div class="col-md-6 sampopen"><i class="fa fa-phone" aria-hidden="true"></i>&nbsp; ${order.client.phoneMobile}</div>
                    <div class="col-md-6 sampopen"><i class="fa fa-birthday-cake" aria-hidden="true"></i>&nbsp; <fmt:formatDate  value="${order.client.birthDate}" pattern="dd.MM.yyyy"/></div>
                    <div class="col-md-6 sampopen"><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp; ${order.client.mail}</div>
                </div>
            </div>



            <div class="order-tour-open">
                <div class="order-client-header" onclick="get_openTour(${order.tour.idTour})">${order.tour.nameTour}</div>
                <div>
                    <table class="tableAddOrder" onclick="get_openTour(${order.tour.idTour})">
                        <tr><td><i class="fa fa-plane" aria-hidden="true"></i> Тур</td><td></td></tr>
                        <tr><td>Дата начала:</td><td><samp class="sampopen"><fmt:formatDate  value="${order.tour.beginDate}" pattern="dd.MM.yyyy"/></samp></td><td>Страна прибытия:</td><td><samp class="sampopen">${order.tour.country.countryName}</samp></td><td>Кол-во дней:</td><td><samp class="sampopen">${order.tour.numberDay}</samp></td></tr>
                        <tr><td>Дата окончания:</td><td><samp class="sampopen"><fmt:formatDate  value="${order.tour.endDate}" pattern="dd.MM.yyyy"/></samp></td><td>Город прибытия:</td><td><samp id="cityin" class="sampopen">${order.tour.city.cityName}</samp></td><td>Кол-во человек:</td><td><samp class="sampopen">${order.tour.personNumber}</samp></td></tr>
                        <tr><td colspan="6" class="costHotel">Стоимость авиабилетов: ${order.tour.costAvia} Br</td></tr>
                        <tr><td><i class="fa fa-home" aria-hidden="true"></i> Отель</td><td></td></tr>
                        <tr><td colspan="6">Название: <samp class="sampopen">${order.tour.hotel.hotelName}</samp></td></tr>
                        <tr><td colspan="6">В отеле: <samp class="sampopen">${order.tour.hotel.servicesHotel}</samp></td></tr>
                        <tr><td colspan="6">В номере: <samp class="sampopen">${order.tour.hotel.servicesHotelNumber}</samp></td></tr>
                        <tr><td colspan="6" class="costHotel">Стоимость номера: ${order.tour.costHotel} Br</td></tr>
                        <tr><td colspan="6" class="costHotel">Стоимость тура: ${order.tour.costTour} Br</td></tr>
                    </table>
                </div>
            </div>

            <div class="div-orderService-open">
                <c:forEach items="${order.serviceOrders}" var="serviceOrder">
                    <label class="float-open">
                        <div class="div1-open">
                            <div class="div2-open">${serviceOrder.nameService}</div>
                            <div class="div3-open">${serviceOrder.descriptionService}</div>
                            <div class="div4-open">${serviceOrder.costService} Br</div>
                        </div>
                    </label>
                </c:forEach>
            </div>
            <div id="costSummOrder" class="modal-title">
                <div id="costSummOrderAdd" class="costSummTour"><i>Стоимость заказа: </i><samp class="sampopen2">${order.costOrder} (${order.costOrderString})</samp></div>


            </div>
            <div class="modal-footer">
                <a id="removeOpen" onclick="get_removeOrder(${order.idOrder})" class="btn btn-danger btn-font"><span class="glyphicon glyphicon-trash"></span></a>
                <a onclick="get_editOrder(${order.idOrder})" class="btn btn-warning btn-font"><span class="glyphicon glyphicon-pencil"></span></a>
                <button type="button" class="btn btn-primary btn-font" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
            </div>
        </div>
    </div>
</div>