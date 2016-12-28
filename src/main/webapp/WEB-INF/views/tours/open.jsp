<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.tour.open.js"/>"></script>

<div class="modal fade" id="ModalOpenTour" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <div id="nameToutPackage5" class="modal-title nameTour">
                        <a id="mapTour" href="#map" onclick="get_map(${tour.city.coordinateX}, ${tour.city.coordinateY})" title="Карта Google">
                            <i class="fa fa-map-marker fa-lg fa2" aria-hidden="true"></i></a> ${tour.hotel.hotelName}
                    </div>

                    <div id="nameToutPackage6" class="modal-title nameTour">${tour.hotel.hotelStar}</div>
                    <div id="nameToutPackage7" class="modal-title nameTour"></div>
                    <div id="nameToutPackage8" class="modal-title nameTour">${tour.country.countryName}, ${tour.city.cityName}</div>

                </div>

                <div class="block">
                    <table class="tableOpen">
                        <tr><td class="fa3"><i class="fa fa-plane fa-lg" aria-hidden="true"></i> Тур</td><td></td></tr>
                        <tr><td>Дата начала:</td><td><samp class="sampopen"><fmt:formatDate  value="${tour.beginDate}" pattern="dd.MM.yyyy"/></samp></td><td>Страна прибытия:</td><td><samp class="sampopen">${tour.country.countryName}</samp></td><td>Кол-во дней:</td><td><samp class="sampopen">${tour.numberDay}</samp></td></tr>
                        <tr><td>Дата окончания:</td><td><samp class="sampopen"><fmt:formatDate  value="${tour.endDate}" pattern="dd.MM.yyyy"/></samp></td><td>Город прибытия:</td><td><samp id="cityin" class="sampopen">${tour.city.cityName}</samp></td><td>Кол-во человек:</td><td><samp class="sampopen">${tour.personNumber}</samp></td></tr>

                        <tr><td colspan="2"><div id="addTransportOpen"></div></td></tr>
                    </table>

                    <div class="is-closed has-baggage-info js-ticket ticketNew" id="openAvia">

                        <div class="ticket__wrapper">
                            ${tour.transport.htmlAvia}
                        </div>
                    </div>
                    <div class="divTransport">Стоимость билетов: <samp class="sampopen2">${tour.transport.costTransport}</samp> Br</div>
                </div>

                <div class="block1">
                    <div class="fa4"><i class="fa fa-home fa-lg" aria-hidden="true"></i> Отель</div>
                    <div class="hotels-grid-item-block hotels-grid-item-block--info " data-place="hotel-info">
                        ${tour.hotel.htmlHotel}
                    </div>
                    <div class="serviceHotelClassOpen">
                        <samp class="sampopen3">ОПИСАНИЕ</samp>
                        <div>${tour.hotel.descriptionHotel}</div>
                        <samp class="sampopen3">В ОТЕЛЕ</samp>
                        <div id="servicesHotelOpen">${tour.hotel.servicesHotel}</div>
                        <samp class="sampopen3">В НОМЕРЕ</samp>
                        <div id="servicesHotelNumberOpen">${tour.hotel.servicesHotelNumber}</div>
                    </div>
                    <div class="divTransport">Стоимость номера: <samp class="sampopen2">${tour.hotel.costHotelNumber}</samp> Br</div>
                </div>


                <div class="nameTour3">
                <c:forEach items="${tour.documentTours}" var="documentTour">
                <div><a class="fancybox" rel="gallery1" href="/tours/eye-document-${documentTour.idDocTour}.jpg"><img height="85" src="/tours/eye-document-${documentTour.idDocTour}.jpg"/></a></div>
                </c:forEach>
                </div>
                <div id="costSummRemove">${tour.costTour}</div>
                <div id="costSummOpen" class="modal-title "></div>

                <div class="modal-footer">
                    <a id="removeOpen" onclick="get_removeTour(${tour.idTour})" class="btn btn-danger btn-font"><span class="glyphicon glyphicon-trash"></span></a>
                    <button type="button" class="btn btn-primary btn-font" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
                </div>
        </div>
    </div>
</div>

