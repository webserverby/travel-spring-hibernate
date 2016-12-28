<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.order.add.client.js"/>"></script>
<div class="modal fade" id="ModalOpenOrderClient"  role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" >

			<form:form id="searchClientForm">
				<div class="div-search-tour-client">
					<div class="div-search-tour-header">Поиск туристов по заданным критериям</div>
					<div class="div-search-tour-content-client">

						<div class="float-search-tour-order">
							<div class="div1-search-client-order">
								<div class="div2-search-bp">Быстрый поиск по ФИО</div>
								<div class="div3-search-client-order-bp">
									<div class="input-group search-padding">
										<label class="input-group-addon" for="searchInputClient"><i class="fa fa-user fa-lg"></i></label>
										<input type="text" class="css-input-search-client-order" id="searchInputClient" name="searchInputClient" placeholder="Введите ФИО">
									</div>
								</div>
								<div class="div4-search-bp"></div>
							</div>
						</div>

						<div class="float-search-tour-order">
							<div class="div1-search-client-order">
								<div class="div2-search">Введите ФИО или телефон</div>
								<div class="div3-search-client-order">
									<div class="input-group search-padding">
										<label class="input-group-addon" for="nameSearch"><i class="fa fa-user fa-lg"></i></label>
										<input type="text" class="css-input-search-client-order" id="nameSearch" name="nameSearch" placeholder="Введите ФИО">
									</div>
									<div class="input-group search-padding">
										<label class="input-group-addon" for="phoneSearch"><i class="fa fa-phone fa-lg"></i></label>
										<input type="text" class="css-input-search-client-order" id="phoneSearch" name="phoneSearch" placeholder="Введите номер телефона">
									</div>
								</div>
								<div class="div4-search">
									<input type="checkbox" name="sortABC" id="sortABC" value="true" class="css-checkbox" />
									<label for="sortABC" class="css-label">Сортировка по алфавиту</label>
								</div>
							</div>
						</div>

						<div class="float-search-tour-order">
							<div class="div1-search-client-order">
								<div class="div2-search">Диапазон добавления туристов</div>
								<div class="div3-search-client-order">
									<div class="col-md-6 ser"><input name="beginDateSearch" id="beginDateSearch" class="css-input-search-tour-order" placeholder="Дата начала"></div>
									<div class="col-md-6 ser"><input name="endDateSearch" id="endDateSearch" class="css-input-search-tour-order" placeholder="Дата окончания"></div>
								</div>
								<div class="div4-search">
									<input type="checkbox" name="sortDate" id="sortDate" value="true" class="css-checkbox" />
									<label for="sortDate" class="css-label">Сортировка по дате</label>
								</div>
							</div>
						</div>

					</div>
					<div class="div-search-tour-footer">
						<button type="button" class="btn btn-font" onclick="get_search_clients()" id="searchBtnTourOrder"><span class="glyphicon glyphicon-search"></span> Поиск</button>
					</div>
				</div>
			</form:form>

			<div id="clientResults">
				<div class="numbertyr">
					<spring:message code="menu.tourist"/> <span class="badge">${clients.size()}</span>
				</div>

				<c:url var="clientsUrl" value="/orders/clients"/>
				<table id="loadTable" class="table table-hover">
					<thead>
					<tr>
						<th><spring:message code="page.fullname"/></th>
						<th><spring:message code="page.phone"/></th>
						<th>E-mail</th>
					</tr>
					</thead>
					<tbody id="tbodyClient">
					<c:forEach items="${clients}" var="client">
						<tr id="tr_${client.idClient}" onclick="get_add_client(${client.idClient})" style="cursor: pointer;" >
							<td>${client.nameRu}</td>
							<td>${client.phoneMobile}</td>
							<td>${client.mail}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty clients}">
						<tr>
							<td colspan="3">Клиенты не найдены</td>
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

			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-font" onclick="get_close_client()"><span class="glyphicon glyphicon-remove"></span></button>
			</div>
		</div>
	</div>
</div>