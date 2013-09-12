$(document).ready(
		function() {

			/* Modale modif */
			$(function() {
				$("#modifdialog").dialog({
					autoOpen : false,
					show : {
						effect : "blind",
						duration : 1000
					},
					hide : {
						effect : "fade",
						duration : 1000
					}
				});
				$("#openermodif").click(
						function() {
							$("#modifdialog").dialog("open");

							$.datepicker
									.setDefaults($.datepicker.regional["fr"]);
							$("#datenaissancemodif").datepicker(
									{
										changeMonth : true,
										changeYear : true,
										dateFormat : "dd/mm/yy",
										dayNamesMin : [ "Di", "Lu", "Ma", "Me",
												"Je", "Ve", "Sa" ],
										monthNamesShort : [ "Jan", "Fev",
												"Mar", "Avr", "Mai", "Juin",
												"Jui", "Aou", "Sep", "Oct",
												"Nov", "Dec" ],
										firstDay : 1,
										yearRange : "c-60:c"
									});

						});
			});

			/* Modale supprimer */
			$(function() {
				$("#supprdialog").dialog({
					autoOpen : false,
					show : {
						effect : "blind",
						duration : 1000
					},
					hide : {
						effect : "fade",
						duration : 1000
					}
				});
				$("#openersuppr").click(function() {
					$("#supprdialog").dialog("open");
				});
			});

		});