
function get_add_client(id){
	var url = "/orders/add-client-" + id;
	$('#addOrderClient').load(url, function () {
		get_close_client();
		$('#addClient').hide();
	});

}

function get_client_delete(){
	$('#addOrderClient').empty();
	$('#addClient').show();
}

function get_close_client(){
	$('#ModalOpenOrderClient').modal("hide");
}


function get_search_clients() {

	var form = $('#searchClientForm');
	var msg = form.serialize();
	$.ajax({
		type: 'GET',
		url: '/orders/search-clients',
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

	$(document).ready(function () {
		$('#searchInputClient').keyup(function (e) {
			var filter = $('#searchInputClient').val();
			if (filter.length > 0) {
				loadTable(filter);
			} else {
			}
		});
	});

	function loadTable(filter) {
		var url = "/orders/search-client-" + filter;

		$('#tbodyClient').load(url, function (response, status, xhr) {
			if (status == "error") {
				var msg = "Sorry but there was an error: ";
				$("#info").html(msg + xhr.status + " " + xhr.statusText);
			}
		});

		return false;
	}


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


$( function() {
	$("#beginDateSearch, #endDateSearch").datepicker({
		dateFormat: 'yy-mm-dd',
		yearRange : "1930:2030",
		changeMonth: true,
		changeYear: true
	});
} );

$(document).ready(function() {
	$("#phoneSearch").inputmask("+375(99)999-99-99");
});