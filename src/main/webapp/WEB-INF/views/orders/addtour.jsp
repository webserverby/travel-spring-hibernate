<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="order-tour">
    <input  name="tour.idTour" type="hidden" value="${tour.idTour}">
    <div class="btnTourClient">
        <button type="button"  onclick="get_tour_delete()" class="btn btn-danger btn-avia"><span class="glyphicon glyphicon-trash"></span></button>
    </div>
    <div class="order-client-header" onclick="get_openTour(${tour.idTour})">${tour.nameTour}</div>
    <div>
        <table class="tableAddOrder" onclick="get_openTour(${tour.idTour})">
            <tr><td><i class="fa fa-plane" aria-hidden="true"></i> Тур</td><td></td></tr>
            <tr><td>Дата начала:</td><td><samp class="sampopen"><fmt:formatDate  value="${tour.beginDate}" pattern="dd.MM.yyyy"/></samp></td><td>Страна прибытия:</td><td><samp class="sampopen">${tour.country.countryName}</samp></td><td>Кол-во дней:</td><td><samp class="sampopen">${tour.numberDay}</samp></td></tr>
            <tr><td>Дата окончания:</td><td><samp class="sampopen"><fmt:formatDate  value="${tour.endDate}" pattern="dd.MM.yyyy"/></samp></td><td>Город прибытия:</td><td><samp id="cityin" class="sampopen">${tour.city.cityName}</samp></td><td>Кол-во человек:</td><td><samp class="sampopen">${tour.personNumber}</samp></td></tr>
            <tr><td colspan="6" class="costHotel">Стоимость авиабилетов: ${tour.costAvia} Br</td></tr>
            <tr><td><i class="fa fa-home" aria-hidden="true"></i> Отель</td><td></td></tr>
            <tr><td colspan="6">Название: <samp class="sampopen">${tour.hotel.hotelName}</samp></td></tr>
            <tr><td colspan="6">В отеле: <samp class="sampopen">${tour.hotel.servicesHotel}</samp></td></tr>
            <tr><td colspan="6">В номере: <samp class="sampopen">${tour.hotel.servicesHotelNumber}</samp></td></tr>
            <tr><td colspan="6" class="costHotel">Стоимость номера: ${tour.costHotel} Br</td></tr>
        </table>
    </div>
    <div id="costSummOpenTour" class="costSummTourOrder">${tour.costTour}</div>
</div>
