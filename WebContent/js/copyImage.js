
document.getElementById("urlImage").onclick = function() {
  this.select();
  execCommand('copy');
  alert('This is a test...');
}