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
<script src="<c:url value="/resources/js/myScript/my.order.js"/>"></script>

<script src="<c:url value="/resources/js/myScript/my.order.tour.js"/>"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBSNjRlNcL4mtTsgNrJyLKf-JQDP8nKoo&callback=initMap"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/my.avia.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/my.hotel.css"/>">

<div class="contain">

    <div class="divButton">
        <div class="form-inline clientButton">
            <button type="button" class="btn btn-success btn-font" onclick="get_addOrder()" id="addBtn"><span class="glyphicon glyphicon-plus"></span> <spring:message code="btn.addOrder"/></button>
        </div>
    </div>

    <form:form id="searchOrderForm">
        <div class="div-search-tour">
            <div class="div-search-tour-header">Поиск заказов по заданным критериям</div>
            <div class="div-search-tour-content">

                <div class="float-search">
                    <div class="div1-search-date">
                        <div class="div2-search">Дата добавления заказа</div>
                        <div class="div3-search">
                            <div class="col-md-6 ser"><input name="beginDateSearchOrder" id="beginDateSearchOrder" class="css-input-search" placeholder="Дата начала"></div>
                            <div class="col-md-6 ser"><input name="endDateSearchOrder" id="endDateSearchOrder" class="css-input-search" placeholder="Дата окончания"></div>
                        </div>
                        <div class="div4-search">
                            <input type="checkbox" name="sortDateOrder" id="sortDateOrder" value="true" class="css-checkbox" />
                            <label for="sortDateOrder" class="css-label">Сортировка по дате заказа</label>
                        </div>
                    </div>
                </div>

                <div class="float-search">
                    <div class="div1-search-date">
                        <div class="div2-search">Дата вылета</div>
                        <div class="div3-search">
                            <div class="col-md-6 ser"><input name="beginDateSearchAvia" id="beginDateSearchAvia" class="css-input-search" placeholder="Дата вылета"></div>
                            <div class="col-md-6 ser"><input name="endDateSearchAvia" id="endDateSearchAvia" class="css-input-search" placeholder="Дата прилета"></div>
                        </div>
                        <div class="div4-search">
                            <input type="checkbox" name="sortDateAvia" id="sortDateAvia" value="true" class="css-checkbox" />
                            <label for="sortDateAvia" class="css-label">Сортировка по дате вылета</label>
                        </div>
                    </div>
                </div>

                <div class="float-search">
                    <div class="div1-search-date">
                        <div class="div2-search">Стоимость заказов</div>
                        <div class="div3-search">
                            <div class="col-md-6 ser">
                                <select name="beginCostSearchOrder" class="select_style css-input-search">
                                    <option value="0.00" >от 0</option>
                                    <option value="500.00" >от 500 Br</option>
                                    <option value="1000.00" >от 1000 Br</option>
                                    <option value="2000.00" >от 2000 Br</option>
                                    <option value="3000.00" >от 3000 Br</option>
                                </select>
                            </div>
                            <div class="col-md-6 ser">
                                <select name="endCostSearchOrder" class="select_style css-input-search">
                                    <option value="500.00" >до 500 Br</option>
                                    <option value="1000.00" >до 1000 Br</option>
                                    <option value="2000.00" >до 2000 Br</option>
                                    <option value="3000.00" >до 3000 Br</option>
                                    <option value="5000.00" >до 5000 Br</option>
                                </select>
                            </div>
                        </div>
                        <div class="div4-search">
                            <input type="checkbox" name="sortCostOrder" id="sortCostOrder" value="true" class="css-checkbox" />
                            <label for="sortCostOrder" class="css-label">Сортировка по стоимости</label>
                        </div>
                    </div>
                </div>

            </div>
            <div class="div-search-tour-footer">
                <button type="button" class="btn btn-font" onclick="get_search_orders()" id="searchBtnTour"><span class="glyphicon glyphicon-search"></span> Поиск</button>
            </div>
        </div>
    </form:form>

    <div id="orderResults">
            <div class="numbertyr">
                <spring:message code="menu.order"/> <span class="badge">${numbers.size()}</span>
            </div>

            <c:url var="ordersUrl" value="/orders"/>
            <table id="loadTable" class="table table-hover">
                <thead>
                <tr>
                    <th><spring:message code="page.orderNumberSearch"/></th>
                    <th><spring:message code="page.orderDate"/></th>
                    <th><spring:message code="page.fullname"/></th>
                    <th>Вылет</th>
                    <th>Прилет</th>
                    <th><spring:message code="page.costTour"/>, Br</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <c:forEach items="${orders}" var="order">
                    <tr id="tr_${order.idOrder}" onclick="get_openOrder(${order.idOrder})" style="cursor: pointer;" >
                        <td>${order.orderNumber}</td>
                        <td><fmt:formatDate  value="${order.orderDate}" pattern="dd.MM.yyyy"/></td>
                        <td>${order.client.nameRu}</td>
                        <td><fmt:formatDate  value="${order.tour.beginDate}" pattern="dd.MM.yyyy"/></td>
                        <td><fmt:formatDate  value="${order.tour.endDate}" pattern="dd.MM.yyyy"/></td>
                        <td>${order.costOrder}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty orders}">
                    <tr>
                        <td colspan="5">Заказы не найдены</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

            <div class="buttonGroup">
                <ul class="pager">
                <c:if test="${searchCriteria.page > 0}">
                    <li><a id="prevResultsLink"  class="ajaxLink"
                       href="${ordersUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page - 1}"><< ${searchCriteria.page +1}</a></li>
                </c:if>
                <c:if test="${not empty orders && fn:length(orders) == searchCriteria.pageSize}">
                    <li><a id="moreResultsLink" class="ajaxLink"
                       href="${ordersUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page + 1}">${searchCriteria.page +1} >> ${searchCriteria.page + 2}</a></li>
                </c:if>
                </ul>
            </div>

    </div>

    <div id="addAddOrder"></div>
    <div id="addOpen"></div>
    <div id="addEditOrder"></div>
    <div id="openClient"></div>
    <div id="openClientModal"></div>
    <div id="addAdd"></div>
    <div id="addEdit"></div>

    <div id="openTour"></div>
    <div id="addAddTour"></div>
    <div id="addOpenTour"></div>
</div>
<div id="mapCity"><div class="map" id="map"></div></div>
<header-footer:footer/>