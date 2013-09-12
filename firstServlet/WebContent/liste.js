$(document).ready(
		function() {
			$("#tableau").accordion({
				heightStyle : "content"
			}, {
				active : false
			}, {
				collapsible : true
			});

			$("a").mousedown(function() {

				var page = $(this).attr("href");
				var param = page.split("=");
				var indice = param[1];
				$("#contenu" + indice).load(page);
				/*$("#tableau").accordion("refresh");
				return false;*/
			});

			
			/* Modale ajout */
			$(function() {
				$("#ajouterdialog").dialog({
					autoOpen : false,
					width : 800,
					show : {
						effect : "blind",
						duration : 1000
					},
					hide : {
						effect : "fade",
						duration : 1000
					}
				});
				$("#openerajout").click(
						function() {
							$("#ajouterdialog").dialog("open");

							$.datepicker
									.setDefaults($.datepicker.regional["fr"]);
							$("#date_naissance").datepicker(
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
		});