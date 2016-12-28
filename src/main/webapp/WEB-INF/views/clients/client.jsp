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
<script src="<c:url value="/resources/js/myScript/my.client.js"/>"></script>
<script src="<c:url value="/resources/js/myScript/my.client.tour.js"/>"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBSNjRlNcL4mtTsgNrJyLKf-JQDP8nKoo&callback=initMap"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/my.avia.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/my.hotel.css"/>">
<div class="contain">

    <div class="divButton">
        <div class="form-inline clientButton">
            <button type="button" class="btn btn-success btn-font" onclick="get_add()" id="addBtn"><span class="glyphicon glyphicon-plus"></span> <spring:message code="btn.addTourist"/></button>
        </div>
    </div>
    <form:form id="searchClientForm">
        <div class="div-search-tour-client">
            <div class="div-search-tour-header">Поиск туристов по заданным критериям</div>
            <div class="div-search-tour-content-client">

                <div class="float-search">
                    <div class="div1-search-date-client">
                        <div class="div2-search-bp">Быстрый поиск по ФИО</div>
                        <div class="div3-search-client-bp">
                            <div class="input-group search-padding">
                                <label class="input-group-addon" for="searchInput"><i class="fa fa-user fa-lg"></i></label>
                                <input type="text" class="css-input-search-client" id="searchInput" name="searchInput" placeholder="Введите ФИО">
                            </div>
                        </div>
                        <div class="div4-search-bp"></div>
                    </div>
                </div>

                <div class="float-search">
                    <div class="div1-search-date-client">
                        <div class="div2-search">Введите ФИО или телефон</div>
                        <div class="div3-search-client">
                              <div class="input-group search-padding">
                                  <label class="input-group-addon" for="nameSearch"><i class="fa fa-user fa-lg"></i></label>
                                  <input type="text" class="css-input-search-client" id="nameSearch" name="nameSearch" placeholder="Введите ФИО">
                              </div>
                              <div class="input-group search-padding">
                                  <label class="input-group-addon" for="phoneSearch"><i class="fa fa-phone fa-lg"></i></label>
                                  <input type="text" class="css-input-search-client" id="phoneSearch" name="phoneSearch" placeholder="Введите номер телефона">
                              </div>
                        </div>
                        <div class="div4-search">
                            <input type="checkbox" name="sortABC" id="sortABC" value="true" class="css-checkbox" />
                            <label for="sortABC" class="css-label">Сортировка по алфавиту</label>
                        </div>
                    </div>
                </div>

                <div class="float-search">
                    <div class="div1-search-date-client">
                        <div class="div2-search">Дата добавления туристов</div>
                        <div class="div3-search-client">
                            <div class="col-md-6 ser"><input name="beginDateSearch" id="beginDateSearch" class="css-input-search" placeholder="Дата начала"></div>
                            <div class="col-md-6 ser"><input name="endDateSearch" id="endDateSearch" class="css-input-search" placeholder="Дата окончания"></div>
                        </div>
                        <div class="div4-search">
                            <input type="checkbox" name="sortDate" id="sortDate" value="true" class="css-checkbox" />
                            <label for="sortDate" class="css-label">Сортировка по дате</label>
                        </div>
                    </div>
                </div>

            </div>
            <div class="div-search-tour-footer">
                <button type="button" class="btn btn-font" onclick="get_search_clients()" id="searchBtnTour"><span class="glyphicon glyphicon-search"></span> Поиск</button>
            </div>
        </div>
    </form:form>

    <div id="hotelResults">
            <div class="numbertyr">
                <spring:message code="menu.tourist"/> <span class="badge">${numbers.size()}</span>
            </div>

            <c:url var="clientsUrl" value="/clients"/>
            <table id="loadTable" class="table table-hover">
                <thead>
                <tr>
                    <th><spring:message code="page.fullname"/></th>
                    <th><spring:message code="page.phone"/></th>
                    <th>E-mail</th>
                    <th><spring:message code="page.added"/></th>
                </tr>
                </thead>
                <tbody id="tbody">
                <c:forEach items="${clients}" var="client">
                    <tr id="tr_${client.idClient}" onclick="get_open(${client.idClient})" style="cursor: pointer;" >
                        <td>${client.nameRu}</td>
                        <td>${client.phoneMobile}</td>
                        <td>${client.mail}</td>
                        <td><fmt:formatDate  value="${client.regDate}" pattern="dd.MM.yyyy"/></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty clients}">
                    <tr>
                        <td colspan="5">Клиенты не найдены</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

            <div class="buttonGroup">
                <ul class="pager">
                <c:if test="${searchCriteria.page > 0}">
                    <li><a id="prevResultsLink"  class="ajaxLinkClient"
                       href="${clientsUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page - 1}"><< ${searchCriteria.page +1}</a></li>
                </c:if>
                <c:if test="${not empty clients && fn:length(clients) == searchCriteria.pageSize}">
                    <li><a id="moreResultsLink" class="ajaxLinkClient"
                       href="${clientsUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page + 1}">${searchCriteria.page +1} >> ${searchCriteria.page + 2}</a></li>
                </c:if>
                </ul>
            </div>
    </div>

<div id="addAdd"></div>
<div id="addOpen"></div>
<div id="addEdit"></div>
<div id="addOpenOrderTabs"></div>
<div id="openTour"></div>
<div id="addOpenTour"></div>

</div>
<div id="mapCity"><div class="map" id="map"></div></div>
<!-- Присвоить имя форме name='form1'

$(function () {
var form = $('form[name=\'form1\']');
form.submit(function () {
$('#ModalAdd').modal("hide");
notyClientAdd();
});

});

-->

<header-footer:footer/>

