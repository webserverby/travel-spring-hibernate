<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="header-footer" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>

<header-footer:header/>
<script src="<c:url value="/resources/js/myScript/my.tour.js"/>"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBSNjRlNcL4mtTsgNrJyLKf-JQDP8nKoo&callback=initMap"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/my.avia.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/my.hotel.css"/>">

<div class="contain">

    <div class="divButton">
        <div class="form-inline clientButton">
            <button type="button" class="btn btn-success btn-font" onclick="get_addTour()" id="addBtn"><span class="glyphicon glyphicon-plus"></span> <spring:message code="btn.addTour"/></button>
        </div>
    </div>

    <form:form id="searchTourForm">
    <div class="div-search-tour">
        <div class="div-search-tour-header">Поиск туров по заданным критериям</div>
            <div class="div-search-tour-content">

                <div class="float-search">
                    <div class="div1-search-date">
                        <div class="div2-search">Выберите "Страну" и "Город"</div>
                        <div class="div3-search">
                            <div class="col-md-6 ser">
                                <select name="countrySearch" class="select_style css-input-search" onChange="get_addCitySearch(this.options[this.selectedIndex].value)">
                                    <option selected="selected">Страна</option>
                                    <c:forEach var="country" items="${countryList}">
                                        <option value="${country.idCountry}" >
                                            <c:out value="${country.countryName}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6 ser">
                                <div id="addSelectCity">
                                <select id="selectCitySearch" name="citySearch" class="select_style css-input-search">
                                    <option selected="selected">Город</option>
                                </select>
                                </div>
                            </div>
                        </div>
                        <div class="div4-search"></div>
                    </div>
                </div>

                <div class="float-search">
                    <div class="div1-search-date">
                        <div class="div2-search">Дата начала-окончания</div>
                        <div class="div3-search">
                            <div class="col-md-6 ser"><input name="beginDateSearch" id="beginDateSearch" class="css-input-search" placeholder="Дата начала"></div>
                            <div class="col-md-6 ser"><input name="endDateSearch" id="endDateSearch" class="css-input-search" placeholder="Дата окончания"></div>
                        </div>
                        <div class="div4-search">
                            <input type="checkbox" name="sortDate" id="sortDate" value="true" class="css-checkbox" />
                            <label for="sortDate" class="css-label">Сортировка по дате</label>
                        </div>
                    </div>
                </div>

                <div class="float-search">
                    <div class="div1-search-date">
                        <div class="div2-search">Стоимость туров</div>
                        <div class="div3-search">
                            <div class="col-md-6 ser">
                                <select name="beginCostSearch" class="select_style css-input-search">
                                    <option value="0.00" >от 0</option>
                                    <option value="500.00" >от 500 Br</option>
                                    <option value="1000.00" >от 1000 Br</option>
                                    <option value="2000.00" >от 2000 Br</option>
                                    <option value="3000.00" >от 3000 Br</option>
                                </select>
                            </div>
                            <div class="col-md-6 ser">
                                <select name="endCostSearch" class="select_style css-input-search">
                                    <option value="500.00" >до 500 Br</option>
                                    <option value="1000.00" >до 1000 Br</option>
                                    <option value="2000.00" >до 2000 Br</option>
                                    <option value="3000.00" >до 3000 Br</option>
                                    <option value="5000.00" >до 5000 Br</option>
                                </select>
                            </div>
                        </div>
                        <div class="div4-search">
                            <input type="checkbox" name="sortCost" id="sortCost" value="true" class="css-checkbox" />
                            <label for="sortCost" class="css-label">Сортировка по стоимости</label>
                        </div>
                    </div>
                </div>

            </div>
            <div class="div-search-tour-footer">
                <button type="button" class="btn btn-font" onclick="get_search_tours()" id="searchBtnTour"><span class="glyphicon glyphicon-search"></span> Поиск</button>
            </div>
    </div>
    </form:form>

    <div id="hotelResults">
            <div class="numbertyr">
                <spring:message code="menu.tour"/> <span class="badge">${numbers.size()}</span>
            </div>

            <c:url var="toursUrl" value="/tours"/>
            <table id="loadTable" class="table table-hover">
                <thead>
                <tr>
                    <th><spring:message code="page.nameTour"/></th>
                    <th><spring:message code="page.beginDate"/></th>
                    <th><spring:message code="page.human"/></th>
                    <th><spring:message code="page.costHotel"/>, Br</th>
                    <th><spring:message code="page.costAvia"/>, Br</th>
                    <th><spring:message code="page.costTour"/>, Br</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <c:forEach items="${tours}" var="tour">
                    <tr id="tr_${tour.idTour}" onclick="get_openTour(${tour.idTour})" style="cursor: pointer;" >
                        <td>${tour.nameTour}</td>
                        <td><fmt:formatDate  value="${tour.beginDate}" pattern="dd.MM.yyyy"/> - <fmt:formatDate  value="${tour.endDate}" pattern="dd.MM.yyyy"/></td>
                        <td>${tour.personNumber}</td>
                        <td>${tour.costHotel}</td>
                        <td>${tour.costAvia}</td>
                        <td>${tour.costTour}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty tours}">
                    <tr>
                        <td colspan="5">Туры не найдены</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

            <div class="buttonGroup">
                <ul class="pager">
                <c:if test="${searchCriteria.page > 0}">
                    <li><a id="prevResultsLink"  class="ajaxLink"
                       href="${toursUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page - 1}"><< ${searchCriteria.page +1}</a></li>
                </c:if>
                <c:if test="${not empty tours && fn:length(tours) == searchCriteria.pageSize}">
                    <li><a id="moreResultsLink" class="ajaxLink"
                       href="${toursUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page + 1}">${searchCriteria.page +1} >> ${searchCriteria.page + 2}</a></li>
                </c:if>
                </ul>
            </div>
    </div>

    <div id="addAddTour"></div>
    <div id="addOpenTour"></div>
    <div id="addEditTour"></div>
    <div id="setAvia"></div>
    <div id="setHotel"></div>
    <div id="setHotelNumber"></div>
    <div id="hint"></div>
</div>
<div id="mapCity"><div class="map" id="map"></div></div>

<header-footer:footer/>