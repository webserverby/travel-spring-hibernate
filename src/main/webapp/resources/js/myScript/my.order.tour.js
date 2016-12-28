
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

