$(document).ready(
		function() {

			$("#tableau").accordion({
				heightStyle : "content"
			}, {
				active : false
			}, {
				collapsible : true
			});

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
									function() {
										$("#formmodif").dialog("open");
										var idcursuschoisie = $(
												"#idcursuschoisie" + indice)
												.attr("value");
										var libellecursuschoisie = $(
												"#libellecursuschoisie"
														+ indice).html();

										$("#idcursusmodif").attr("value",
												idcursuschoisie);
										$("#libellecursusmodif").attr("value",
												libellecursuschoisie);
									});
						});
			});

		});