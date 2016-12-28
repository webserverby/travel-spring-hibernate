
	function get_editOrder(id)
	{
		var url = "/orders/edit-order/" + id;

		$('#addEditOrder').load(url, function () {
			$('#ModalEditOrder').modal("show");
		});
		return false;
	}

	function get_openOrder(id)
	{
		var url = "/orders/open-order/" + id;

		$('#addOpen').load(url, function () {
			$('#ModalOpenOrder').modal("show");
		});
	}

	function get_search_orders() {

		var form = $('#searchOrderForm');
		var msg = form.serialize();
		$.ajax({
			type: 'GET',
			url: '/orders/search-orders',
			data: msg,
			success: function(data) {
				var id = $(data).attr("id");
				$("#" + id).replaceWith(data);
			},
			error:  function(){
				alert('Ошибка!');
			}
		});
	}

	function get_addOrder()
	{
		var url = "/orders/add-order-get" ;

		$('#addAddOrder').load(url, function () {
			$('#ModalAddOrder').modal("show");
		});

	}

	function get_removeOrder(id)
	{

		$.ajax({
			url: "/orders/remove-order/" + id,
			type: "DELETE",
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},

			success: function(order) {
				var del ="tr[id*=tr_" + order.idOrder + "]";
				$(del).remove();

				$('#ModalOpenOrder').modal("hide");

				var textClient = 'Заказ ';
				var typeError = 'error';
				var templateRemove = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyRemove"></span><div class="noty_close"></div></div>';
				notyClient(textClient, typeError, templateRemove);
				$('#clientNotyRemove').last().append(
					order.orderNumber + " удален!"
				);
			}
		});
	}


	function get_open_client(id){
		var url = "/clients/open-client/" + id;
		$('#openClientModal').load(url, function () {
			$('#ModalOpen').modal("show");
		});
	}

	function get_documentOpen(id)
	{
		var url = "/clients/add-document/" + id;
		$('#addTabs').load(url, function () {

		});
		return false;
	}

	function get_edit(id)
	{
		var url = "/clients/edit-client/" + id;

		$('#addEdit').load(url, function () {
			$('#ModalEdit').modal("show");
		});
		return false;
	}

	function get_add()
	{
		var url = "/clients/add-client-get" ;

		$('#addAdd').load(url, function () {
			$('#ModalAdd').modal("show");
		});
		return false;
	}

	function get_remove(id)
	{
		var idClient = id;
		$('#addOrderClient').empty();
		$('#addClient').show();
		$.ajax({
			url: "/clients/remove-client/" + idClient,
			type: "DELETE",
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},

			success: function(client) {
				var del ="tr[id*=tr_" + client.idClient + "]";
				$(del).remove();

				$('#ModalOpen').modal("hide");

				var textClient = 'Турист ';
				var typeError = 'error';
				var templateRemove = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyRemove"></span><div class="noty_close"></div></div>';
				notyClient(textClient, typeError, templateRemove);
				$('#clientNotyRemove').last().append(
					client.nameRu + " с id " + client.idClient + " удален! "
				);
			}
		});
	}


	$(document).ready(function() {
		var ajaxify = function() {
			$(".ajaxForm").submit(function() {
				var data = $(this).serialize() + "&htmlFormat=nolayout";
				$.ajax({
					type : $(this).attr("method"),
					url : $(this).attr("action"),
					data : data,
					success : function(data) {
						var id = $(data).attr("id");
						$("#" + id).replaceWith(data);
						ajaxify();
					}
				});
				return false;
			});
			$(".ajaxLink").click(function() {
				$.ajax({
					type : 'GET',
					url : $(this).attr("href"),
					data : "htmlFormat=nolayout",
					success : function(data) {
						var id = $(data).attr("id");
						$("#" + id).replaceWith(data);
						ajaxify();
					}
				});
				return false;
			});
		}
		ajaxify();
	});

	function notyClient(text, type, template) {
		noty({
			text: text,
			textAlign:"center",
			layout: 'top',
			closeWith: ['click', 'hover'],
			template: template, // шаблон сообщения
			type: type,
			animation: { // анимация
				open: {height: 'toggle'},
				close: {height: 'toggle'},
				easing: 'swing',
				speed: 500 // скорость открытия и закрытия сообщения
			},
			timeout: '1700', // задержка перед закрытием сообщения
			force: false, // добавляет уведомление о добавлении в очередь
			modal: false, // создает эффект модального окна, затемняет фон страницы
			maxVisible: 1, // максимальное кол-во отображаемых сообщений на экране, если вызвано больше то они будут добавлены в очередь
			killer: false, // если включено то закрывает все открытые сообщения кроме себя

			callback: { // обратные функции
				onShow: function() {}, // срабатывает перед показом сообщения
				afterShow: function() {}, // после показа
				onClose: function() {}, // перед закрытием
				afterClose: function() {} // после закрытия
			},
			buttons: false // добавляет кнопки в сообщение и к каждой кнопке можно добавить свою функцию!
		})
	}


	$( function() {
		$("#beginDateSearchOrder, #endDateSearchOrder, #beginDateSearchAvia, #endDateSearchAvia").datepicker({
			dateFormat: 'yy-mm-dd',
			yearRange : "1930:2030",
			changeMonth: true,
			changeYear: true
		});

	} );
