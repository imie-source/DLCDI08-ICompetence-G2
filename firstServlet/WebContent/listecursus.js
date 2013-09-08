$(document).ready(function() {

	$("#tableaucursus").accordion({
		heightStyle : "content"
	}, {
		active : false
	}, {
		collapsible : true
	});

	$(function() {
		$("#formajoutcursus").dialog({
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
			$("#formajoutcursus").dialog("open");
		});
	});

	$(function() {
		$("#formmodifcursus").dialog({
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

		$("button").mousedown(function() {
			var button = $(this).attr("id");
			var param = button.split("f");
			var indice = param[1];
			$("#openermodif"+indice).click(function() {
				$("#formmodifcursus").dialog("open");
			});
		});
	});

});