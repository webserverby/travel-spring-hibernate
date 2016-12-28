
	$(document).ready(function () {

		$('#addTourForm').submit(function (e) {
			$("#deleteAdd").hide();
			$("#addDocuments").show();
			$.post('/tours/add-tour', $(this).serialize(), function(tour) {

				$('#addDocument').submit(function (e) {
					var formdata = new FormData(this);
					$.ajax({
						url: "/tours/add-document-" + tour.idTour,
						type: "POST",
						data: formdata,
						mimeTypes:"multipart/form-data",
						contentType: false,
						cache: false,
						processData: false,
						success: function() {

						}
					});
					e.preventDefault();
					$('#ModalAddTour').modal("hide");

					var textClient = 'Тур ';
					var typeSuccess = 'success';
					var templateAdd = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyAdd"></span><div class="noty_close"></div></div>';
					notyClient(textClient, typeSuccess, templateAdd);
					$('#clientNotyAdd').last().append(
						tour.nameTour + " добавлен! "
					);
				});

				var beginDate = new Date(tour.beginDate).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2.$1.$3');
				var endDate = new Date(tour.endDate).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2.$1.$3');

				$('#loadTable').last().prepend(
					'<tr id="tr_' + tour.idTour + '" onclick="get_openTour(' + tour.idTour + ')" style="cursor: pointer;">' +
					'<td>' + tour.nameTour + '</td>' +
					'<td>' + beginDate + ' - ' + endDate + '</td>' +
					'<td>' + tour.personNumber + '</td>' +
					'<td>' + tour.costHotel + '</td>' +
					'<td>' + tour.costAvia + '</td>' +
					'<td>' + tour.costTour + '</td>' +
					'</tr>'
				);

			});

			e.preventDefault();
		});

	});


	function get_aviaForm() {
		$("#addAviaBtn").attr('disabled',true);
		$('#removeAviaBtn').remove();
		var form = $('#addAviaForm');
		var msg = form.serialize();
		$.ajax({
			type: 'POST',
			url: '/tours/add-avia',
			data: msg,
			success: function(transport) {
				$('#transportInput').val(transport.idTransport);
			},
			error:  function(){
				alert('Ошибка!');
			}
		});
	}

	function get_aviaDelete() {
		$('#addAvia').empty();
		$('#costSummAdd').remove();
		$('#addTransport').show();
	}


	function get_hotelForm() {
		$("#addHotelBtn").attr('disabled',true);
		$('#removeHotelBtn').remove();
		var form = $('#addHotelForm');
		var msg = form.serialize();
		$.ajax({
			type: 'POST',
			url: '/tours/add-hotel',
			data: msg,
			success: function(hotel) {
				$('#hotelInput').val(hotel.idHotel);
			},
			error:  function(){
				alert('Ошибка!');
			}
		});
	}

	function get_hotelDelete() {
		$('#addHotelNew').empty();
		$('#serviceHotel').empty();
		$('#serviceHotelNumber').empty();
		$('#nameTourPackage1').empty();
		$('#nameTourPackage2').empty();
		$('#nameTourPackage3').empty();
		$('#nameTourPackage4').empty();

		$('#addHotel').show();
	}



	function get_addCity(id) {
		get_documentOpenTour();
		$("#addDocuments").hide();
		var url = '/tours/search-country-' + id;
		$('#selectCity').remove();
		remove();

		$.getJSON(url, function(citys){
			$('#addCity').append('<select id="selectCity" name="city.idCity" class="select_style" onChange="get_add_Avia_Hotel(this.options[this.selectedIndex].value)">' +
				'<option selected="selected">Город</option></select>');

			$.each(citys, function(i, city){
				$('#selectCity').last().append(
					'<option value="' + city.idCity + '">' +
					city.cityName +
					'</option>'
				);
			});
		});
	}


	function get_add_Avia_Hotel(idCity) {
		remove();
		$('#addBtnID').append(
			'<div id="addHotel" class="add-hotel"></div>' +
			'<div id="addTransport" class="add-transport"></div>'
		);

		$('#addHotel').append('<button type="button" class="btn btn-font" onclick="get_add_Hotel(' + idCity + ')" id="addBtnHotel"><span class="glyphicon glyphicon-plus"></span> Отель</button>');
		$('#addTransport').append('<button type="button" class="btn btn-font" onclick="get_add_Avia(' + idCity + ')" id="addBtnAvia"><span class="glyphicon glyphicon-plus"></span> Авиабилет</button>');

	}

	function get_add_Hotel(idCity)
	{
		var beginDate = new Date($("#beginDateInput").val()).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$3.$1.$2');
		var endDate = new Date($("#endDateInput").val()).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$3.$1.$2');
		var personNumber = $("#personNumberInput").val();
		var url = '/tours/open-hotel-' + idCity + '-' + beginDate + '-' + endDate + '-' + personNumber;

		$("#ModalAddTour").faLoading({
			"type" : "add",
			"icon" : "fa-refresh",
			"status" : 'loading',
			"text" : false,
			"title" : "Пожалуйста подождите..."
		});


		$('#setHotel').load(url, function () {
			$("#ModalAddTour").faLoading(false);
			$('.hl-ui-btn-text').text("Добавить");
			$("#ad1").remove();
			$('li[data-place="grid-subs_location"]').remove();
			$('span[data-role="btn-gate-name"]').remove();
			$(".hotels-grid-prices-more").remove();
			$(".hotels-grid-item-photos-list-counts").remove();
			$(".hotels-grid-item-photos-control").remove();
			$(".hotels-grid-prices-control").remove();
			$('.hotels-grid-item-title[href]').attr('href', null);
			$('.hl-ui-btn[href]').attr('href', null);
			$('.hotels-grid-item-location-c-typeLink[href]').attr('href', null);
			$('.hotels-grid-item-photos-item--add[href]').attr('href', null);

			$('#ModalOpenHotel').modal("show");
		});

	}

	function get_add_Avia(idCity)
	{
		var beginDate = new Date($("#beginDateInput").val()).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2$1');
		var endDate = new Date($("#endDateInput").val()).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2$1');
		var personNumber = $("#personNumberInput").val();
		var url = '/tours/open-avia-' + idCity + '-' + beginDate + '-' + endDate + '-' + personNumber;

		$("#ModalAddTour").faLoading({
			"type" : "add",
			"icon" : "fa-refresh",
			"status" : 'loading',
			"text" : false,
			"title" : "Пожалуйста подождите..."
		});


		$('#setAvia').load(url, function () {
			$("#ModalAddTour").faLoading(false);
			$(".js-share-open").remove();
			$(".ticket__baggage").remove();
			$(".ticket__seats").remove();
			$(".ticket__buy-proposal").remove();
			$(".ticket__proposals").remove();
			$(".ticket__opener").remove();
			$(".adsense-banner").remove();
			$('#ta_ad1').remove();
			$('#google_tag_id_doubleclick_big').remove();
			$('#google_tag_id_doubleclick_small').remove();

			//	$('.currency-code').css("border","3px solid red");
			var price = $('.ticket__buy-price-num').first().text();
			price = price.replace(/\s+/g,'');
			var cost = (price * 3.05) / 100;

			$('.ticket__buy-price-num').text(cost.toFixed() + " Br");
			$('.ticket__buy-text').text("Добавить");
			$('.ticket__buy-price-divider').text("Стоимость");

			$('.ticket__buy[href]').attr('href', null);
			$('.ticket__airline-logo[href]').attr('href', null);

			$('#ModalOpenAvia').modal("show");
		});

	}


	function get_documentOpenTour()
	{
		var url = "/tours/add-document";
		$('#addDocuments').load(url, function () {

		});
	}


	$( function() {
		$("#beginDateInput, #endDateInput, #startDateAviaInput, #endDateAviaInput").datepicker({
			dateFormat: 'yy-mm-dd',
			yearRange : "1930:2030",
			changeMonth: true,
			changeYear: true
		});
	} );

	function remove() {
		$('#nameHotel').remove();
		$('#nameHotel2').remove();
		$('#nameCountry2').remove();
		$('#nameCity2').remove();
		$('#costHotel').remove();

		$('#selectHotelNumber').remove();
		$('#divEnter').remove();
		$('#divEnter2').remove();

		$('#raiting').remove();
		$('#raiting2').remove();
		$('#serviceHotelExtra').remove();
		$('#costSummAdd').remove();
		$('#addBtnHotel').remove();
		$('#addBtnAvia').remove();
		$('#addTransport').remove();
		$('#addHotel').remove();

		for (var i = 0; i < 5; i++) {
			$('#serviceNumber_' + i).remove();
		}
		for (var i = 0; i < 5; i++) {
			$('#serviceHotel_' + i).remove();
		}
	};

	function remove2() {
		$('#nameHotel2').remove();
		$('#nameHotel').remove();
		$('#nameCountry2').remove();
		$('#nameCity2').remove();
		$('#costHotel').remove();
		$('#divEnter2').remove();
		$('#divEnter').remove();
		$('#raiting').remove();
		$('#raiting2').remove();
		$('#serviceHotelExtra').remove();
		$('#selectHotelNumber').remove();
		$('#costSummAdd').remove();
		for (var i = 0; i < 5; i++) {
			$('#serviceHotel_' + i).remove();
		}
		for (var i = 0; i < 5; i++) {
			$('#serviceNumber_' + i).remove();
		}
	};

