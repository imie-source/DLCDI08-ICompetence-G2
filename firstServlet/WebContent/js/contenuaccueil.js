$(document).ready(function() {

	$("#zonesquaretopleft").hover(
	        function () {
	          $("#squaretopleft2").hide();
	        }, 
	        function () {
	          $("#squaretopleft2").show();
	        }
	    );
	
	
	$("#zone1").hover(
	        function () {
	          $("#zone3").hide();
	        }, 
	        function () {
	          $("#zone3").show();
	        }
	       
	    );
	$("#zone2").hover(
	        function () {
	          $("#zone1").hide();
	        }, 
	        function () {
	          $("#zone1").show();
	        }
	    );
	$("#zone2").hover(
	        function () {
	          $("#zone3").hide();
	        }, 
	        function () {
	          $("#zone3").show();
	        }
	    );
	
	$("#zone3").hover(
	        function () {
	          $("#zone1").hide();
	        }, 
	        function () {
	          $("#zone1").show();
	        }
	    );
	
	$("#zonesquarebottomleft").hover(
	        function () {
	          $("#squarebottomleft2").hide();
	        }, 
	        function () {
	          $("#squarebottomleft2").show();
	        }
	    );
	$("#zonesquaretopright").hover(
	        function () {
	          $("#squaretopright2").hide();
	        }, 
	        function () {
	          $("#squaretopright2").show();
	        }
	    );
	$("#zonesquarebottomright").hover(
	        function () {
	          $("#squarebottomright2").hide();
	        }, 
	        function () {
	          $("#squarebottomright2").show();
	        }
	    );
	$("#zonesmenurightbottom").hover(
	        function () {
	          $("#menurightbottom2").hide();
	        }, 
	        function () {
	          $("#menurightbottom2").show();
	        }
	    );

});
