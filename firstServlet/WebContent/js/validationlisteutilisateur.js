jQuery(document).ready(function() {

	jQuery("#formajout").validate({
		rules: {
			nom: {
				required: true,
				lettre: true
			},
			prenom: {
				required: true,
				lettre: true
			},
			adresse_mail: {
				required: true,
				email: true
			},
			identifiant: {
				required: true,
				exa: true
			},
			pwd: {
				required: true,
				exa: true
			},
			libelle: {
				required: true,
//				exa: true
			},
			ville: {
				required: true,
//				lettre: true
			},
			code_postal: {
				required: true,
				nombre: true
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

