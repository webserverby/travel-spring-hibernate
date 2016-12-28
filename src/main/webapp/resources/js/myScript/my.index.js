
	/* Часы и дата добавления */
	function show(){
		if (!document.all&&!document.getElementById)
			return;
		thelement=document.getElementById? document.getElementById("tick2"): document.all.tick2;
		thelement2=document.getElementById? document.getElementById("tick3"): document.all.tick3;
		var Digital=new Date();
		var hours=Digital.getHours();
		var minutes=Digital.getMinutes();
		var seconds=Digital.getSeconds();
		var day=new Array("Вc","Пн","Вт","Ср","Чт","Пт","Сб");
		var month=new Array("01","02","03","04","05","06","07","08","09","10","11","12");
		var month2 = Digital.getMonth()+1;
		var day2 = Digital.getDate();
		var year2 = Digital.getFullYear();
		if (day2<=9)
			day2="0"+day2;
		if (month2<=9)
			month2="0"+month2;
		if (hours>24)
			hours=hours-24;
		if (hours==0)
			hours=24;
		if (minutes<=9)
			minutes="0"+minutes;
		if (seconds<=9)
			seconds="0"+seconds;
		var ctime=hours+":"+minutes;
		thelement.innerHTML=""+ctime;
		thelement2.innerHTML=Digital.getDate()+ "." + month[Digital.getMonth()] + ", " + day[Digital.getDay()];
		setTimeout("show()",1000)
	}

	window.onload=show;


	/* Кнопка прокрутки сайта  */
	$(function() {
		// при нажатии на кнопку scrollup
		$('.scrollup').click(function() {
			// переместиться в верхнюю часть страницы
			$("html, body").animate({
				scrollTop:0
			},1000);
		});
	});
	// при прокрутке окна (window)
	$(window).scroll(function() {
		// если пользователь прокрутил страницу более чем на 50px
		if ($(this).scrollTop()>50) {
			// то сделать кнопку scrollup видимой
			$('.scrollup').fadeIn();
		}
		// иначе скрыть кнопку scrollup
		else {
			$('.scrollup').fadeOut();
		}
	});

