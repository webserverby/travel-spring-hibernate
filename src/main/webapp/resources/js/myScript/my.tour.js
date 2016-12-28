
	function get_openTour(idTour)
	{
		var url = "/tours/open-tour/" + idTour;

		$("body").faLoading({
			"type" : "add",
			"icon" : "fa-refresh",
			"status" : 'loading',
			"text" : false,
			"title" : "Пожалуйста подождите..."
		});

		$('#addOpenTour').load(url, function () {
			$('#nameToutPackage7').append(
				'<div id="raiting3">' +
				'<div id="raiting_votes3"></div>' +
				'</div>'
			);
			var stars = $('#nameToutPackage6').text();
			var star_widht = stars * 17 ;
			$('#raiting_votes3').width(star_widht);
			$('#nameToutPackage6').remove();

			var summBr = $("#costSummRemove").text();
			summBr = $.trim(summBr);
			$('#costSummRemove').empty();

			$('#costSummOpen').append(
				'<div id="costSummOpen" class="costSummTour"><i>Стоимость тура составляет:</i> ' + '<i class="sampopen2">' + summBr + ' (' +
				sum_propis(summBr.split('.')[0]||'0') + ' рублей ' + sum_propis(summBr.split('.')[1]||'0') +' копеек)</i></div>'

			);

			var servicesHotelOpen = $("#servicesHotelOpen").text();
			$('#servicesHotelOpen').empty();
			var arr1 = servicesHotelOpen.split(', ');
			for (var i = 0; i < arr1.length; i++) {
				$('#servicesHotelOpen').append(
					'<a id="servicesHotelOpen_'+i+'" rel="tag">'+ arr1[i] +'</a>'
				);
			};

			var servicesHotelNumberOpen = $("#servicesHotelNumberOpen").text();
			$('#servicesHotelNumberOpen').empty();
			var arr2 = servicesHotelNumberOpen.split(', ');
			for (var i = 0; i < arr2.length; i++) {
				$('#servicesHotelNumberOpen').append(
					'<a id="servicesHotelNumberOpen_'+i+'" rel="tag">'+ arr2[i] +'</a>'
				);
			};

			$('.hotels-grid-item-head').remove();
			$('.hotels-grid-item-location-c-typeLink').removeClass();
			$('.hotels-grid-item-location-icon').remove();

			$('.hotels-grid-item-rate-wrap').addClass('hotels-grid-item-rate-wrapNew').removeClass('hotels-grid-item-rate-wrap');
			$('.ticket__segments').addClass('ticket__segmentsNew').removeClass('ticket__segments');
			$('.fly-segment').addClass('fly-segmentNew').removeClass('fly-segment');

			$("body").faLoading(false);
			$('#ModalOpenTour').modal("show");
		});

	}


	function get_addTour()
	{
		var url = "/tours/add-tour-get" ;
		$('#addAddTour').load(url, function () {
			$('#ModalAddTour').modal("show");

		});
		return false;
	}

	function get_search_tours() {

		var form = $('#searchTourForm');
		var msg = form.serialize();
		$.ajax({
			type: 'GET',
			url: '/tours/search-tours',
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

	function get_addCitySearch(id) {
		var url = '/tours/search-country-' + id;
		$('#selectCitySearch').remove();
		$.getJSON(url, function(citys){
			$('#addSelectCity').append('<select id="selectCitySearch" name="citySearch" class="select_style css-input-search">' +
				'<option selected="selected">Город</option></select>');

			$.each(citys, function(i, city){
				$('#selectCitySearch').last().append(
					'<option value="' + city.cityName + '">' +
					city.cityName +
					'</option>'
				);
			});
		});
	}

	function get_removeTour(id)
	{
		$.ajax({
			url: "/tours/remove-tour/" + id,
			type: "DELETE",
			dataType:'json',
			success: function() {
			}
		});
		var del ="tr[id*=tr_" + id + "]";
		$(del).remove();

		$('#ModalOpenTour').modal("hide");

		var textClient = 'Тур ';
		var typeError = 'error';
		var templateRemove = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyRemove"></span><div class="noty_close"></div></div>';
		notyClient(textClient, typeError, templateRemove);
		$('#clientNotyRemove').last().append(
			" удален! "
		);
	}


	$(document).ready(function() {
		$('#map').remove();
	});

	// функция инициализации карты
	function initMap(cX,cY) {

		// Координаты центра на карте. Широта: 56.2928515, Долгота: 43.7866641 6.4374697,101.813029,
		var centerLatLng = new google.maps.LatLng(cX, cY);

		// Обязательные опции с которыми будет проинициализированна карта
		var mapOptions = {
			center: centerLatLng, // Координаты центра мы берем из переменной centerLatLng
			zoom: 13               // Зум по умолчанию. Возможные значения от 0 до 21
		};

		// Создаем карту внутри элемента #map
		var map = new google.maps.Map(document.getElementById("map"), mapOptions);
		// Создаем маркер на карте
		var marker = new google.maps.Marker({

			// Определяем позицию маркера
			position: {lat: cX, lng: cY},

			// Указываем на какой карте он должен появится. (На странице ведь может быть больше одной карты)
			map: map,

			// Пишем название маркера - появится если навести на него курсор и немного подождать
			title: 'Гостиница'
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
		$("#beginDateSearch, #endDateSearch").datepicker({
			dateFormat: 'yy-mm-dd',
			yearRange : "1930:2030",
			changeMonth: true,
			changeYear: true
		});

	} );
