<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.tour.add.js"/>"></script>
<script src="<c:url value="/resources/js/incrementing.js"/>"></script>

<div class="modal fade" id="ModalAddTour" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title otstup">Добавление тура</h2>
            </div>

            <div class="modal-body-2">
                <form:form id="addAviaForm">

                    <input name="cityDeparture" id="cityDepartureInput" type="hidden" value="">
                    <input name="cityArrival" id="cityArrivalInput" type="hidden" value="">

                    <input name="startDateAvia" id="startDateAviaInput" type="hidden" value="">
                    <input name="endDateAvia" id="endDateAviaInput" type="hidden" value="">

                    <input name="timeStart" id="timeStartInput" type="hidden" value="">
                    <input name="timeEnd" id="timeEndInput" type="hidden" value="">

                    <input name="airportStart" id="airportStartInput" type="hidden" value="Национальный">
                    <input name="costTransport" id="costTransportInput" type="hidden" value="">

                    <input name="htmlAvia" id="htmlAviaInput" type="hidden" value="">
                    <input name="city.idCity" id="cityAviaInput" type="hidden" value="">

                </form:form>
                <form:form id="addHotelForm">

                    <input name="hotelName" id="hotelNameInput" type="hidden" value="">
                    <input name="descriptionHotel" id="descriptionHotelInput" type="hidden" value="">

                    <input name="servicesHotel" id="servicesHotelInput" type="hidden" value="">
                    <input name="servicesHotelNumber" id="servicesHotelNumberInput" type="hidden" value="">

                    <input name="hotelStar" id="hotelStarInput" type="hidden" value="">
                    <input name="costHotelNumber" id="costHotelNumberInput" type="hidden" value="">

                    <input name="htmlHotel" id="htmlHotelInput" type="hidden" value="">
                    <input name="city.idCity" id="cityHotelInput" type="hidden" value="">

                </form:form>

                <div id="deleteAdd">
                <form:form id="addTourForm">
                    <div class="nameheader">
                        <div id="nameTourPackage1" class="modal-title nameTour"></div>
                        <div id="nameTourPackage2" class="modal-title nameTour"></div>
                        <div id="nameTourPackage3" class="modal-title nameTour"></div>
                        <div id="nameTourPackage4" class="modal-title nameTour"></div>
                    </div>
                    <input name="nameTour" id="nameTourInput" type="hidden" value="">
                    <input name="costHotel" id="costHotelInput" type="hidden" value="">
                    <input name="costAvia" id="costAviaInput" type="hidden" value="">
                    <input name="costTour" id="costTourInput" type="hidden" value="">
                    <input name="transport.idTransport" id="transportInput" type="hidden" value="">
                    <input name="hotel.idHotel" id="hotelInput" type="hidden" value="">

                    <div class="tour-add-inf">
                        <table class="tableModal">
                            <tr>
                                <td><input name="beginDate" id="beginDateInput" class="css-input" placeholder="Дата начала" required></td>
                                <td>
                                    <select id="selectCountry" name="country.idCountry" class="select_style" onChange="get_addCity(this.options[this.selectedIndex].value)">
                                    <option selected="selected">Страна</option>
                                    <c:forEach var="country" items="${countryList}">
                                        <option value="${country.idCountry}" >
                                            <c:out value="${country.countryName}"/>
                                        </option>
                                    </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><input name="endDate" id="endDateInput" class="css-input" placeholder="Дата окончания" required></td>
                                <td><div id="addCity">
                                    <select id="selectCity" name="city.idCity" class="select_style" onChange="get_addHotel(this.options[this.selectedIndex].value)">
                                        <option selected="selected">Город</option>
                                    </select></div></td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="numbers-row">
                                        <input name="personNumber" id="personNumberInput" type="number"  class="css-input" placeholder="Кол-во человек" required>
                                    </div>
                                </td>

                            </tr>
                        </table>

                    </div>

                    <div id="addBtnID"></div>
                    <div id="addHotelNew"></div>
                    <div id="serviceHotel" class="serviceHotelClass"></div>
                    <div id="serviceHotelNumber" class="serviceHotelClass"></div>
                    <div id="addAvia" class="ticket is-closed has-baggage-info js-ticket"></div>
                    <div id="costSumm" class="modal-title"></div>

                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success btn-font"><span class="glyphicon glyphicon-ok"></span></button>
                            <button type="button" class="btn btn-primary btn-font" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
                        </div>
                </form:form>


                </div>
                <div id="addDocuments"></div>
            </div>
        </div>
    </div>
</div>



