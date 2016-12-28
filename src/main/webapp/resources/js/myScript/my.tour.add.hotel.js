
$('.hl-ui-btn').click(function(){
	// var index = $(this).attr('data-hotelid');
	var blok = $(this).parents(".hotels-grid-item_visible").html();
	$('#addHotelNew').append(
		'<div class="btnTourAviaHotel">' +
		'<button type="button" id="addHotelBtn" onclick="get_hotelForm()" class="btn btn-success btn-avia"><span class="glyphicon glyphicon-ok"></span></button>' +
		'<button type="button" id="removeHotelBtn" onclick="get_hotelDelete()" class="btn btn-danger btn-avia"><span class="glyphicon glyphicon-trash"></span></button>' +
		'</div>'
	);
	$('#addHotelNew').append(blok);

	$('.hotels-grid-list').remove(); // удаление списка отелей
	$('#ModalOpenHotel').modal("hide");
	$('#addHotelNew').hide();

	var href = $('.hotels-grid-item-more-link').attr('href');
	var hrefNew = "https://search.hotellook.com" + href;
	var url = '/tours/open-hotelNumber';
	$('.hotels-grid-item-more').remove();

	//--- Запись ссылок фото из элементов <a> ---//
	var urlClean = [];
	var htmlUrl = [];
	var itemNames = [];
	var targetStart = "https"; // начальное слово поиска
	var targetEnd = "auto"; // цель поиска

	for (var i = 1; i < 6; i++) {
		htmlUrl.push($.trim($('.hotels-grid-item-photos-item[data-number="'+ i +'"]').html()));
	}
	for (var i = 0; i < 5; i++) {
		var posOne = -1;
		while ((posOne = htmlUrl[i].indexOf(targetStart, posOne + 1)) != -1) {
			itemNames.push(posOne);
		}
		var posTwo = -1;
		while ((posTwo = htmlUrl[i].indexOf(targetEnd, posTwo + 1)) != -1) {
			itemNames.push(posTwo);
		}
		var str = htmlUrl[i];
		var urlDo = str.substring(itemNames[0], itemNames[1]);
		urlDo = urlDo.replace("300/210", "760/490");
		urlClean.push(urlDo);
		itemNames.length = 0;
	}
	for (var i = 2; i < 26; i++) {
		htmlUrl.push($.trim($('.hotels-grid-item-photos-item[data-number="'+ i +'"]').remove()));
	}

	$("#ModalAddTour").faLoading({
		"type" : "add",
		"icon" : "fa-refresh",
		"status" : 'loading',
		"text" : false,
		"title" : "Пожалуйста подождите..."
	});


	$.ajax({
		url : url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		mimeType: 'application/json',
		data : ({
			text: hrefNew,
			textOne: urlClean[0],
			textTwo: urlClean[1],
			textThree: urlClean[2],
			textFour: urlClean[3],
			textFive: urlClean[4]

		}),
		success: function (data) {
			var result = data.text;
			$('#setHotelNumber').html(result);

			$('.hotels-grid-item-inner').addClass('innerNew').removeClass('hotels-grid-item-inner');

			var description = $('.hotels-one-info-c').filter('.hl-text--default').filter('.state-stashed').text();
			description = $.trim(description);

			var servicesHotelNumber = $('.hotels-one-info-c:contains("Телевизор")').text();
			servicesHotelNumber = $.trim(servicesHotelNumber);
			servicesHotelNumber = servicesHotelNumber.replace(/\.$/gm, '');

			var servicesHotel = $('.hotels-one-info-c:contains("Бар")').text();
			servicesHotel = $.trim(servicesHotel);
			servicesHotel = servicesHotel.replace(/\.$/gm, '');

			$('#descriptionHotelInput').val(description);
			$('#servicesHotelNumberInput').val(servicesHotelNumber);
			$('#servicesHotelInput').val(servicesHotel);

			var costNumber = $(".hl-price-value").first().text();
			costNumber = parseFloat($.trim(costNumber));
			costNumber = costNumber.toFixed(2);
			$('#costHotelNumberInput').val(costNumber);
			$('#costHotelInput').val(costNumber);

			var htmlHotel = $('.hotels-grid-item-block--info').html();
			htmlHotel = $.trim(htmlHotel);
			$('#htmlHotelInput').val(htmlHotel);
			$('.hotels-grid-item-block--prices').remove();

			var idCity = $("#selectCity").val();
			$('#cityHotelInput').val(idCity);

			var date1 = new Date($("#beginDateInput").val());
			var date2 = new Date($("#endDateInput").val());
			var timeDiff = Math.abs(date2.getTime() - date1.getTime());
			var numberDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
			var dayHotel;
			if (numberDays == 1) {
				dayHotel = "день";
			} else if (numberDays > 1 && numberDays < 5){
				dayHotel = "дня";
			}else {
				dayHotel = "дней";
			}

			$('#serviceHotel').append(
				'<div id="serviceHotelExtra"><samp class="sampopen3">ОПИСАНИЕ</samp><br>'+ description +'</div>'
			);
			$('#serviceHotel').append(
				'<samp class="sampopen3">В ОТЕЛЕ</samp><br>'
			);
			var arr1 = servicesHotel.split(', ');
			for (var i = 0; i < arr1.length; i++) {
				$('#serviceHotel').append(
					'<a id="serviceHotel_'+i+'" rel="tag">'+ arr1[i] +'</a>'
				);
			};

			$('#serviceHotelNumber').append(
				'<samp class="sampopen3">В НОМЕРЕ</samp><br>'
			);
			var arr2 = servicesHotelNumber.split(', ');
			for (var i = 0; i < arr2.length; i++) {
				$('#serviceHotelNumber').append(
					'<a id="serviceNumber_'+i+'" rel="tag">'+ arr2[i] +'</a>'
				);
			};

			$('#serviceHotelNumber').append(
				'<div id="costHotel" class="costHotel">Стоимость за ' + numberDays + ' ' + dayHotel + ': <samp id="costHotelBr" class="sampopen2">' + costNumber + '</samp> Br</div>'
			);


			var hotelName = $("#addHotelNew").find(".hotels-grid-item-title").text();
			hotelName = hotelName.replace(/(^|\s+)Отель(\s+|$)/g, ' ');
			$('#hotelNameInput').val(hotelName);
			$('#nameTourPackage1').append(
				'<div id="nameHotel2">'+ hotelName +'</div>'
			);
			var nameCountry = $("#selectCountry option:selected").text();
			nameCountry = $.trim(nameCountry);
			$('#nameTourPackage3').append(
				'<div id="nameCountry2">'+ nameCountry + ',' +'</div>'
			);
			var nameCity = $("#selectCity option:selected").text();
			nameCity = $.trim(nameCity);
			$('#nameTourPackage4').append(
				'<div id="nameCity2">'+ nameCity +'</div>'
			);


			$('.hotels-grid-item-title').append(
				'<div id="raiting">' +
				'<div id="raiting_votes"></div>' +
				'</div>'
			);
			$('#nameTourPackage2').append(
				'<div id="raiting2">' +
				'<div id="raiting_votes2"></div>' +
				'</div>'
			);
			var stars = $("#addHotelNew").find(".hotels-grid-item-stars").html();
			stars = $.trim(stars.replace(/[^0-9]/g,''));
			stars = parseInt(stars);
			$('#hotelStarInput').val(stars);
			var star_widht = stars * 17 ;
			$('#raiting_votes, #raiting_votes2').width(star_widht);
			$('.hotels-grid-item-stars').remove();
			$('.hotels-grid-item-type').remove();

			$('#nameTourInput').val(hotelName +' '+ stars + '*' + '  ' + nameCountry + ', ' + nameCity);

			$('#addHotelNew').show();
			$('#setHotelNumber').empty();
			$('#addHotel').hide();

			$("#ModalAddTour").faLoading(false);
		}
	});

	/*
	 $('.hotels-one-info-c:contains("Парковка")').css('border', '1px solid red');
	 var itemNames = [];//объявим переменную выше цикла
	 $('.hotels-one-info-c').each(function(){
	 itemNames.push($.trim($(this).text()));
	 });

	 var mm1 = "Wi-Fi в лобби, Парковка, Ресепшн 24 часа.";
	 var hotelNg = mm1.replace(/\./g, ' ');
	 var hotelNg = mm1.replace(/,/g, ' ');
	 var arr1 = mm1.split(' ');
	 alert(hotelNg);
	 */
});

$(document).ready(function () {
	// Only one of these modals should show at a time.
	$('#ModalOpenHotel').on('show.bs.modal', function (e) {

			$('body').css("overflow","hidden");
			$(this).css("overflow-y", "auto");
		})
		.on('hide.bs.modal', function (e) {
			// @todo reload the job
			$('#ModalAddTour').modal('show').css("overflow-y", "auto");
		});
	$('#ModalAddTour').on('show.bs.modal', function (e) {
			// @todo reload the job
			$('body').css("overflow","hidden");
		})
		.on('hide.bs.modal', function (e) {
			// @todo reload the job
			$('body').css("overflow","visible");
		});
});