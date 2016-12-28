
	$(document).ready(function () {

		$('#addClient').submit(function (e) {

			$.post('/clients/add-client', $(this).serialize(), function(client) {

				$('#loadTable').last().prepend(
					'<tr id="tr_' + client.idClient + '" onclick="get_open(' + client.idClient + ')" style="cursor: pointer;">' +
					'<td>' + client.nameRu + '</td>' +
					'<td>' + client.phoneMobile + '</td>' +
					'<td>' + client.mail + '</td>' +
					'<td>'+ (new Date(client.regDate).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2.$1.$3')) + '</td>' +
					'</tr>'
				);

				$('#ModalAdd').modal("hide");

				var textClient = 'Турист ';
				var typeSuccess = 'success';
				var templateAdd = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyAdd"></span><div class="noty_close"></div></div>';
				notyClient(textClient, typeSuccess, templateAdd);
				$('#clientNotyAdd').last().append(
					client.nameRu + " с id " + client.idClient + " добавлен! "
				);
			});
			clearInputs();
			e.preventDefault();
		});

	});

	function clearInputs() {
		$('input[id*="Input"]').each(function () {
			$(this).val('');
		});
	}


	$( function() {
		$("#birthDateInput, #regDateInput").datepicker({
			dateFormat: 'yy-mm-dd',
			yearRange : "1930:2030",
			changeMonth: true,
			changeYear: true
		});

	} );


	$(document).ready(function() {
		$("#phoneMobileInput").inputmask("+375(99)999-99-99");
	});

	$(document).ready(function() {
		$("#issueDateInput, #expiryDateInput").inputmask("99-99-9999", {placeholder: "__-__-____" });
	});