
	$(document).ready(function () {

		$('#editOrder').submit(function (e) {

			$.post('/orders/edit-order', $(this).serialize(), function(order) {
				var orderDate = new Date(order.orderDate).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2.$1.$3');

				$('#tr_' + order.idOrder).replaceWith(
					'<tr id="tr_' + order.idOrder + '" onclick="get_open(' + order.idOrder + ')" style="cursor: pointer;">' +
					'<td>' + order.idOrder + '</td>' +
					'<td>' + order.orderNumber + '</td>' +
					'<td>' + orderDate + '</td>' +
					'<td>' + order.client.nameRu + '</td>' +
					'<td>' + order.tour.nameTour + '</td>' +
					'</tr>'
				);

				$('#ModalEditOrder').modal("hide");
				$('#ModalOpenOrder').modal("hide");
				var textClient = 'Заказ ';
				var typeWarning = 'warning';
				var templateEdit = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyEdit"></span><div class="noty_close"></div></div>';
				notyClient(textClient, typeWarning, templateEdit);
				$('#clientNotyEdit').last().append(
					order.orderNumber + " обновлен!"
				);
			});

			e.preventDefault();
		});

	});

	$(document).ready(function() {

		$('#client-searchEdit').autocomplete({
			serviceUrl: '/orders/search-client',
			paramName: "nameRu",
			delimiter: ",",
			transformResult: function(response) {

				return {
					suggestions: $.map($.parseJSON(response), function(client) {
						$('#idClientEdit').val(client.idClient);
						return { value: client.nameRu, data: client.idClient };
					})
				};
			}
		});

		$('#tour-searchEdit').autocomplete({
			serviceUrl: '/orders/search-tour',
			paramName: "nameTour",
			delimiter: ",",
			transformResult: function(response) {

				return {
					suggestions: $.map($.parseJSON(response), function(tour) {
						$('#idTourEdit').val(tour.idTour);
						return { value: tour.nameTour, data: tour.idTour };
					})
				};
			}
		});

	});

	$(function() {
		$("#orderDateEdit").datepicker({
			dateFormat: 'yy-mm-dd',
			yearRange : "1930:2030",
			changeMonth: true,
			changeYear: true
		});

		var DateOrder=new Date();
		var month = DateOrder.getMonth()+1;
		var year = DateOrder.getFullYear();
		if (month<=9)
			month="0"+month;

		$("#orderNumberEdit").inputmask(year + "/" + month + "-999");

	});

