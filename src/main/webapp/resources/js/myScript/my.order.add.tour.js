
function get_add_tour(id){
	var url = "/orders/add-tour-" + id;
	$('#addOrderTour').load(url, function () {
		get_close_tour();
		$('#addTourOrder').hide();
		var costBr = $('#costSummOpenTour').text();
		costBr = $.trim(costBr);
		$('#costSummOpenTour').empty();
		$('#costSummOpenTour').append(
			'<i>Стоимость тура:</i> <samp class="sampopen2">' + costBr + ' (' +
			sum_propis(costBr.split('.')[0]||'0') + ' рублей ' + sum_propis(costBr.split('.')[1]||'0') +' копеек)</samp>'
		);
	});

}

function get_tour_delete(){
	$('#addOrderTour').empty();
	$('#addTourOrder').show();
}

function get_close_tour(){
	$('#ModalOpenOrderTour').modal("hide");
}

function get_search_tours() {

	var form = $('#searchTourForm');
	var msg = form.serialize();
	$.ajax({
		type: 'GET',
		url: '/orders/search-tours',
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
		$('#addSelectCity').append('<select id="selectCitySearch" name="citySearch" class="select_style css-input-search-tour-order">' +
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
});

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
		$(".ajaxLinkTour").click(function() {
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


$( function() {
	$("#beginDateSearch, #endDateSearch").datepicker({
		dateFormat: 'yy-mm-dd',
		yearRange : "1930:2030",
		changeMonth: true,
		changeYear: true
	});

} );



