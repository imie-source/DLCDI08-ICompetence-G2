jQuery(document).ready(function() {

	jQuery("#formajout").validate({
		rules: {
			libelle: {
				required: true,
				exa: true
			},
		}
	});
	
	jQuery("#formmodif").validate({
		rules: {
			libelle: {
				required: true,
				exa: true
			},
		}
	});
});

