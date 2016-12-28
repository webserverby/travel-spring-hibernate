<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="<c:url value="/resources/js/myScript/my.order.add.tour.js"/>"></script>
<div class="modal fade" id="ModalOpenOrderTour"  role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">


			<form:form id="searchTourForm">
				<div class="div-search-tour">
					<div class="div-search-tour-header">Поиск туров по заданным критериям</div>
					<div class="div-search-tour-content">

						<div class="float-search-tour-order">
							<div class="div1-search-tour-order">
								<div class="div2-search">Выберите "Страну" и "Город"</div>
								<div class="div3-search-tour-order">
									<div class="col-md-6 ser">
										<select name="countrySearch" class="select_style css-input-search-tour-order" onChange="get_addCitySearch(this.options[this.selectedIndex].value)">
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
											<select id="selectCitySearch" name="citySearch" class="select_style css-input-search-tour-order">
												<option selected="selected">Город</option>
											</select>
										</div>
									</div>
								</div>
								<div class="div4-search"></div>
							</div>
						</div>

						<div class="float-search-tour-order">
							<div class="div1-search-tour-order">
								<div class="div2-search">Выберите диапазон дат</div>
								<div class="div3-search-tour-order">
									<div class="col-md-6 ser"><input name="beginDateSearch" id="beginDateSearch" class="css-input-search-tour-order" placeholder="Дата начала"></div>
									<div class="col-md-6 ser"><input name="endDateSearch" id="endDateSearch" class="css-input-search-tour-order" placeholder="Дата окончания"></div>
								</div>
								<div class="div4-search">
									<input type="checkbox" name="sortDate" id="sortDate" value="true" class="css-checkbox" />
									<label for="sortDate" class="css-label">Сортировка по дате</label>
								</div>
							</div>
						</div>

						<div class="float-search-tour-order">
							<div class="div1-search-tour-order">
								<div class="div2-search">Выберите диапазон стоимости</div>
								<div class="div3-search-tour-order">
									<div class="col-md-6 ser">
										<select name="beginCostSearch" class="select_style css-input-search-tour-order">
											<option value="0.00" >от 0</option>
											<option value="500.00" >от 500 Br</option>
											<option value="1000.00" >от 1000 Br</option>
											<option value="2000.00" >от 2000 Br</option>
											<option value="3000.00" >от 3000 Br</option>
										</select>
									</div>
									<div class="col-md-6 ser">
										<select name="endCostSearch" class="select_style css-input-search-tour-order">
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
									<label for="sortCost" class="css-label">Сортировка по цене</label>
								</div>
							</div>
						</div>

					</div>
					<div class="div-search-tour-footer">
						<button type="button" class="btn btn-font" onclick="get_search_tours()" id="searchBtnTourOrder"><span class="glyphicon glyphicon-search"></span> Поиск</button>
					</div>
				</div>
			</form:form>

			<div id="tourResults">
				<div class="numbertyr">
					<spring:message code="menu.tour"/> <span class="badge">${tours.size()}</span>
				</div>

				<c:url var="toursUrl" value="/orders/tours"/>
				<table id="loadTable" class="table table-hover">
					<thead>
					<tr>
						<th><spring:message code="page.nameTour"/></th>
						<th><spring:message code="page.beginDate"/></th>
						<th><spring:message code="page.costTour"/>, Br</th>
					</tr>
					</thead>
					<tbody id="tbody">
					<c:forEach items="${tours}" var="tour">
						<tr id="tr_${tour.idTour}" onclick="get_add_tour(${tour.idTour})" style="cursor: pointer;" >
							<td>${tour.nameTour}</td>
							<td><fmt:formatDate  value="${tour.beginDate}" pattern="dd.MM.yyyy"/> - <fmt:formatDate  value="${tour.endDate}" pattern="dd.MM.yyyy"/></td>
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
							<li><a id="prevResultsLink"  class="ajaxLinkTour"
								   href="${toursUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page - 1}"><< ${searchCriteria.page +1}</a></li>
						</c:if>
						<c:if test="${not empty tours && fn:length(tours) == searchCriteria.pageSize}">
							<li><a id="moreResultsLink" class="ajaxLinkTour"
								   href="${toursUrl}?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page + 1}">${searchCriteria.page +1} >> ${searchCriteria.page + 2}</a></li>
						</c:if>
					</ul>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-font" onclick="get_close_tour()"><span class="glyphicon glyphicon-remove"></span></button>
			</div>
		</div>
	</div>
</div>