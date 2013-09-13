$(document).ready(
		function() {
	
			$("#tableau").accordion({
				heightStyle : "content"
			}, {
				active : false
			}, {
				collapsible : true
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
									function(){
				$("#formmodif").dialog("open");
										$("#formmodif").dialog("open");
										var idmotclefchoice = $(
												"#idmotclefchoice" + indice)
												.attr("value");
																				var libellemotclefchoice = $(
												"#libellemotclefchoice"
														+ indice).html();
										
										
										$("#idmotclefmodif").attr("value",
												idmotclefchoice);
										$("#libellemotclefmodif").attr("value",
												libellemotclefchoice);
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
										var idmotclefsuppr = $(
												"#idmotclefchoice" + indice)
												.attr("value");
										$("#idmotclefsuppr").attr("value",
												idmotclefsuppr);
									});
						});
			});
			
			
		});

		