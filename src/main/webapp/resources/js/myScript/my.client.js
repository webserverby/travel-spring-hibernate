
	function get_edit(id)
	{
		var url = "/clients/edit-client/" + id;

		$('#addEdit').load(url, function () {
			$('#ModalEdit').modal("show");
		});
		return false;
	}


	function get_documentOpen(id)
	{
	var url = "/clients/add-document/" + id;
	$('#addTabs').load(url, function () {
	});

	}

	function get_basicOpen(id)
	{
		var url = "/clients/add-basic/" + id;
		$('#addTabs').load(url, function () {
		});
	}

	function get_tourOpen(id)
	{
		var url = "/clients/add-tourOpen/" + id;
		$('#addTabs').load(url, function () {
		});
	}

	function get_orderOpen(id)
	{
		var url = "/clients/add-orderOpen/" + id;
		$('#addTabs').load(url, function () {
		});
	}

	function get_openOrder(id)
	{
		var url = "/orders/open-order/" + id;

		$('#addOpenOrderTabs').load(url, function () {
			$('#ModalOpenOrder').modal("show");
		});
	}

	function get_open(id)
	{
	var url = "/clients/open-client/" + id;

	$('#addOpen').load(url, function () {
		$('#ModalOpen').modal("show");
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


	function get_search_clients() {

		var form = $('#searchClientForm');
		var msg = form.serialize();
		$.ajax({
			type: 'GET',
			url: '/clients/search-clients',
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



	function get_remove(id)
	{
	var idClient = id;

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


	$(document).ready(function () {
		$('#searchInput').keyup(function (e) {
			var filter = $('#searchInput').val();
			if (filter.length) {
				loadTable(filter);
			} else {
			}
		});
	});

	function loadTable(filter) {
		var url = "/clients/list/" + filter;

		$('#tbody').load(url, function (response, status, xhr) {
			if (status == "error") {
				var msg = "Sorry but there was an error: ";
				$("#info").html(msg + xhr.status + " " + xhr.statusText);
			}
		});

		return false;
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
			$(".ajaxLinkClient").click(function() {
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
		};
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
			timeout: '1500', // задержка перед закрытием сообщения
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
		$("#beginDateSearch, #endDateSearch").datepicker({
			dateFormat: 'yy-mm-dd',
			yearRange : "1930:2030",
			changeMonth: true,
			changeYear: true,

		});
	} );

	$(document).ready(function() {
		$("#phoneSearch").inputmask("+375(99)999-99-99");
	});
