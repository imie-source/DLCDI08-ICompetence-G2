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
					
				});
				
			/* Modale ajout */
			$(function() {
		$("#formajout").dialog({
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
				$("#openerajout").click(function() {
			$("#formajout").dialog("open");
				});
			});
			/* Modale modif */
			$(function() {
		$("#formmodif").dialog({
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
				$("button").mousedown(
						function() {
							var button = $(this).attr("id");
							var param = button.split("f");
							var indice = param[1];
							
							$("#openermodif" + indice).click(
				$("#formmodif").dialog("open"));
										$("#formmodif").dialog("open");
//										

										$("#idgdtmodif").attr("value",
												indice);
//										
									});
						});
			});

			/* Modale supprimer */
			$(function() {
				$("#formsuppr").dialog({
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
				$("button").mousedown(
						function() {
							var button = $(this).attr("id");
							var param = button.split("r");
							var indice = param[2];

							$("#openersuppr" + indice).click(
									function() {
										$("#formsuppr").dialog("open");
										var idcursuschoisie = $(
												"#idcursuschoisie" + indice)
												.attr("value");
										$("#idcursussuppr").attr("value",
												idcursuschoisie);
									});
						});
			});

		;