 $(document).ready(function() {


	 $( "#tableau").accordion({heightStyle:"content"},{active: false},{collapsible: false}); 

	 $("a").mousedown(function(){
			
		 var page = $(this).attr("href");
		 var param = page.split("=");
		 var indice= param[1];
		 $ ("#contenu"+indice).load(page);
		 $( "#tableau" ).accordion("refresh");
		 return false;});
});