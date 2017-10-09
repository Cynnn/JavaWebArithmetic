

$('#numberBtn').click(function() {
	var number = document.getElementById("numberText");
	var url = "http://localhost:8080/number?Action=getnumber&Number="+number.value;
	top.location = url;
});