function get_documentForm(idClient)
{
	$('#addDocument').submit(function (e) {
		var formdata = new FormData(this);
		var urla = "/clients/add-document/" + idClient;
		$.ajax({
			url: "/clients/add-document-"+idClient,
			type: "POST",
			data: formdata,
			mimeTypes:"multipart/form-data",
			contentType: false,
			cache: false,
			processData: false,
			success: function() {
				$('#addTabs').load(urla, function () {

				});
			}
		});

		e.preventDefault();
	});
}


function get_documentDelete(idClient, docId)
{
	var urld = "/clients/add-document/" + idClient;
	$.ajax({
		url: "/delete-document-" + docId,
		type: "DELETE",
		success: function() {
			$('#addTabs').load(urld, function () {

			});
		}
	});
}
