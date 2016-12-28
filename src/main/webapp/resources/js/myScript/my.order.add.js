
	$(document).ready(function () {

		$('#addOrder').submit(function (e) {

			$.post('/orders/add-order', $(this).serialize(), function(order) {
				var orderDate = new Date(order.orderDate).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2.$1.$3');

				$('#loadTable').last().prepend(
					'<tr id="tr_' + order.idOrder + '" onclick="get_openOrder(' + order.idOrder + ')" style="cursor: pointer;">' +
					'<td>' + order.orderNumber + '</td>' +
					'<td>' + orderDate + '</td>' +
					'<td></td>' +
					'<td></td>' +
					'<td></td>' +
					'<td>' + order.costOrder + '</td>' +
					'</tr>'
				);

				$('#ModalAddOrder').modal("hide");

				var textClient = 'Заказ ';
				var typeSuccess = 'success';
				var templateAdd = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyAdd"></span><div class="noty_close"></div></div>';
				notyClient(textClient, typeSuccess, templateAdd);
				$('#clientNotyAdd').last().append(
					order.orderNumber + " добавлен!"
				);
			});

			e.preventDefault();
		});

	});



	function get_search_client()
	{
		var url = '/orders/search-client';
		$('#openClient').load(url, function () {
			$('#ModalOpenOrderClient').modal("show");
		});

	}

	function get_search_tour()
	{
		var url = '/orders/search-tour';
		$('#openTour').load(url, function () {
			$('#ModalOpenOrderTour').modal("show");
		});

	}


	$(document).ready(function () {
		// Only one of these modals should show at a time.
		$('#ModalOpenOrderTour').on('show.bs.modal', function (e) {

				$('body').css("overflow","hidden");
				$(this).css("overflow-y", "auto");
			})
			.on('hide.bs.modal', function (e) {
				// @todo reload the job
				$('#ModalAddOrder').modal('show').css("overflow-y", "auto");
			});
		$('#ModalAddOrder').on('show.bs.modal', function (e) {
				// @todo reload the job
				$('body').css("overflow","hidden");
			})
			.on('hide.bs.modal', function (e) {
				// @todo reload the job
				$('body').css("overflow","visible");
			});
	});

	$(document).ready(function () {
		// Only one of these modals should show at a time.
		$('#ModalOpenOrderClient').on('show.bs.modal', function (e) {

				$('body').css("overflow","hidden");
				$(this).css("overflow-y", "auto");
			})
			.on('hide.bs.modal', function (e) {
				// @todo reload the job
				$('#ModalAddOrder').modal('show').css("overflow-y", "auto");
			});
		$('#ModalAddOrder').on('show.bs.modal', function (e) {
				// @todo reload the job
				$('body').css("overflow","hidden");
			})
			.on('hide.bs.modal', function (e) {
				// @todo reload the job
				$('body').css("overflow","visible");
			});
	});
