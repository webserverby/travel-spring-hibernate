
$('.ticket__buy').click(function(){
	var index = $(this).attr('data-ticket-index');
	var blok = $('.ticket[data-index="'+ index +'"]').html();
	$('#addAvia').append(
		'<div class="btnTourAviaHotel">' +
		'<button type="button" id="addAviaBtn" onclick="get_aviaForm()" class="btn btn-success btn-avia"><span class="glyphicon glyphicon-ok"></span></button>' +
		'<button type="button" id="removeAviaBtn" onclick="get_aviaDelete()" class="btn btn-danger btn-avia"><span class="glyphicon glyphicon-trash"></span></button>' +
		'</div>'
	);
	$('#addAvia').append(blok);
	$('.ticket').addClass('ticketNew').removeClass('ticket');
	$('.ticket__content').addClass('ticket__contentNew').removeClass('ticket__content');
	$('#addTransport').hide();
	$('#results_add_container').remove();
	$('#ModalOpenAvia').modal("hide");

	var costTransport = $('.ticket__buy-price-num').first().text();
	costTransport = costTransport.replace(/[^0-9]/g,'');
	costTransport = parseFloat($.trim(costTransport));
	costTransport = costTransport.toFixed(2);
	$('.ticket__buy-col').remove();
	var humans = $("#personNumberInput").val();
	var humanBilet;
	if (humans == 1) {
		humanBilet = "одного";
	} else if (humans == 2){
		humanBilet = "двух";
	} else if (humans == 3){
		humanBilet = "трех";
	} else if (humans == 4){
		humanBilet = "четырех";
	}else {
		humanBilet = "пятерых";
	}
	$('#addAvia').append('<div id="addAviaCost"></div>');
	$('#addAviaCost').append('<div id="divTransport" class="divTransport">Стоимость за ' + humanBilet + ':  <samp id="costTransport" class="sampopen2">' + costTransport + '</samp> Br</div>');

	var costHotelBr = $('#costHotelBr').text();
	costHotelBr = $.trim(costHotelBr);
	var summ = parseFloat(costTransport) + parseFloat(costHotelBr);
	summ = summ.toFixed(2);

	$('#costTourInput').val(summ);

	$('#costSumm').append(
		'<div id="costSummAdd" class="costSummTour"><i>Стоимость тура составляет:</i> ' + '<samp class="sampopen2">' + summ + ' (' +
		sum_propis(summ.split('.')[0]||'0') + ' рублей ' + sum_propis(summ.split('.')[1]||'0') +' копеек)</samp></div>'
	);

	var cityDeparture = $(".fly-segment__origin").find(".fly-segment__city").first().text();
	cityDeparture = $.trim(cityDeparture);
	$('#cityDepartureInput').val(cityDeparture);
	var cityArrival = $(".fly-segment__destination").find(".fly-segment__city").first().text();
	cityArrival = $.trim(cityArrival);
	$('#cityArrivalInput').val(cityArrival);

	var timeStart = $(".fly-segment__origin").find(".fly-segment__time").first().text();
	timeStart = $.trim(timeStart);
	$('#timeStartInput').val(timeStart);
	var timeEnd = $(".fly-segment__destination").find(".fly-segment__time").first().text();
	timeEnd = $.trim(timeEnd);
	$('#timeEndInput').val(timeEnd);

	var beginDate = $("#beginDateInput").val();
	$('#startDateAviaInput').val(beginDate);
	var endDate = $("#endDateInput").val();
	$('#endDateAviaInput').val(endDate);


	var idCity = $("#selectCity").val();
	$('#cityAviaInput').val(idCity);

	$('#costTransportInput').val(costTransport);
	$('#costAviaInput').val(costTransport);

	$('.ticket__warning-text').remove();
	$('.fly-segment__baggage').remove();
	$('.fly-segment__flights').remove();

	var htmlAvia = $('.ticket__wrapper').html();
	htmlAvia = $.trim(htmlAvia);
	$('#htmlAviaInput').val(htmlAvia);

});

$(document).ready(function () {
	// Only one of these modals should show at a time.
	$('#ModalOpenAvia').on('show.bs.modal', function (e) {

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