
document.getElementById("urlImage").onclick = function() {
	this.select();
	document.execCommand('copy');
	alert("I copied url");
}