
	$(document).ready(function () {

		$('#editClient').submit(function (e) {

			$.post('/clients/edit-client', $(this).serialize(), function(client) {
				$('#tr_' + client.idClient).replaceWith(

					'<tr id="tr_' + client.idClient + '" onclick="get_open(' + client.idClient + ')" style="cursor: pointer;">' +
					'<td>' + client.idClient + '</td>' +
					'<td>' + client.nameRu + '</td>' +
					'<td>' + client.phoneMobile + '</td>' +
					'<td>' + client.mail + '</td>' +
					'<td>'+ (new Date(client.regDate).toLocaleString('en-us', {year: 'numeric', month: '2-digit', day: '2-digit'}).replace(/(\d+)\/(\d+)\/(\d+)/, '$2.$1.$3')) + '</td>' +
					'</tr>'
				);

				$('#ModalEdit').modal("hide");
				$('#ModalOpen').modal("hide");
				var textClient = 'Турист ';
				var typeWarning = 'warning';
				var templateEdit = '<div class="noty_message">&nbsp;<span class="noty_text notycolor" id="clientNotyEdit"></span><div class="noty_close"></div></div>';
				notyClient(textClient, typeWarning, templateEdit);
				$('#clientNotyEdit').last().append(
					client.nameRu + " с id " + client.idClient + " обновлен! "
				);
			});

			e.preventDefault();
		});

	});


	$( function() {
		$("#birthDateEdit, #regDateEdit").datepicker({
			dateFormat: 'yy-mm-dd',
			yearRange : "1930:2030",
			changeMonth: true,
			changeYear: true
		});
	} );

	$(document).ready(function() {
		$("#phoneMobileEdit").inputmask("+375(99)999-99-99");
	});

	$(document).ready(function() {
		$("#issueDateEdit, #expiryDateEdit").inputmask("99-99-9999", {placeholder: "__-__-____" });
	});
