
$(document).ready(function() {
	$(".div1-index-report").hide();

	$.ajax({
		url: "/orders/json-grafik-week",
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function (orders) {

			var itemsOrder = [];
			var itemsFinans = [];
			var arrOrder = {};
			var dateOrder = [];
			var numberOrder = [];

			$.each(orders , function(key, val){
				itemsOrder.push(val.orderDate);
			});

			$.each(orders , function(key, val){
				itemsFinans.push(val.costOrder);
			});


			for (var i = 0; i < itemsOrder.length; i++) {
				if (arrOrder[itemsOrder[i]]) {
					arrOrder[itemsOrder[i]] += 1;
				} else {
					arrOrder[itemsOrder[i]] = 1;
				}
			}

			$.each(arrOrder, function(key,data) {
				dateOrder.push(key);
				numberOrder.push(data);
			});


			var color = Chart.helpers.color;
			var configOrder = {
				type: 'line',
				data: {
					labels: dateOrder,
					datasets: [{
						label: "Заказы ",
						backgroundColor: color(window.chartColors.red).alpha(0.2).rgbString(),
						borderColor: window.chartColors.red,
						pointBackgroundColor: window.chartColors.red,
						data: numberOrder
					}]
				},
				options: {
					responsive: true,
					title:{
						display:true,
						text:'График заказов за неделю'
					},
				}
			};

			var configFinans = {
				type: 'line',
				data: {
					labels: dateOrder,
					datasets: [{
						label: "Выручка ",
						backgroundColor: color(window.chartColors.yellow).alpha(0.2).rgbString(),
						borderColor: window.chartColors.yellow,
						pointBackgroundColor: window.chartColors.yellow,
						data: itemsFinans
					}]
				},
				options: {
					responsive: true,
					title:{
						display:true,
						text:'Выручка турфирмы за неделю, Br'
					},
				}
			};

			var ctxZ = document.getElementById("grOrder").getContext("2d");
			window.myLine = new Chart(ctxZ, configOrder);

			var ctxF = document.getElementById("grFinans").getContext("2d");
			window.myLine = new Chart(ctxF, configFinans);

		}
	});


	$.ajax({
		url: "/orders/json-grafik-month",
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function (orders) {

			var itemsOrder = [];
			var arrOrder = {};
			var dateOrder = [];
			var numberOrder = [];
			var itemsFinans = [];

			$.each(orders , function(key, val){
				itemsOrder.push(val.orderDate);
			});
			$.each(orders , function(key, val){
				itemsFinans.push(val.costOrder);
			});

			for (var i = 0; i < itemsOrder.length; i++) {
				if (arrOrder[itemsOrder[i]]) {
					arrOrder[itemsOrder[i]] += 1;
				} else {
					arrOrder[itemsOrder[i]] = 1;
				}
			}

			$.each(arrOrder, function(key,data) {
				dateOrder.push(key);
				numberOrder.push(data);
			});

			var color = Chart.helpers.color;

			var configMonth = {
				type: 'line',
				data: {
					labels: dateOrder,
					datasets: [{
						label: "Выручка ",
						backgroundColor: color(window.chartColors.yellow).alpha(0.2).rgbString(),
						borderColor: window.chartColors.yellow,
						pointBackgroundColor: window.chartColors.yellow,
						data: itemsFinans
					}]
				},
				options: {
					responsive: true,
					title:{
						display:true,
						text:'Выручка турфирмы за месяц, Br'
					},
				}
			};

			var ctxM = document.getElementById("grFinansMonth").getContext("2d");
			window.myLine = new Chart(ctxM, configMonth);
		}
	});

	$.ajax({
		url: "/clients/json-grafik-week",
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function (clients) {

			var itemsClients = [];
			var arrClients = {};
			var dateClients = [];
			var numberClients = [];

			$.each(clients , function(key, val){
				itemsClients.push(val.regDate);
			});

			for (var i = 0; i < itemsClients.length; i++) {
				if (arrClients[itemsClients[i]]) {
					arrClients[itemsClients[i]] += 1;
				} else {
					arrClients[itemsClients[i]] = 1;
				}
			}

			$.each(arrClients, function(key,data) {
				dateClients.push(key);
				numberClients.push(data);
			});

			var color = Chart.helpers.color;

			var configClient = {
				type: 'line',
				data: {
					labels: dateClients,
					datasets: [{
						label: "Новые туристы ",
						backgroundColor: color(window.chartColors.blue).alpha(0.2).rgbString(),
						borderColor: window.chartColors.blue,
						pointBackgroundColor: window.chartColors.blue,
						data: numberClients
					}]
				},
				options: {
					responsive: true,
					title:{
						display:true,
						text:'График новых туристов за неделю'
					},
				}
			};

			var ctxC = document.getElementById("grClient").getContext("2d");
			window.myLine = new Chart(ctxC, configClient);
		}
	});


});

function get_report_order(){
	$(".div1-index-report").show();
}