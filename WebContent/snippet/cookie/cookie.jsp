
<link rel="stylesheet" href="snippet/cookie/cookie.css">
<!--//BLOQUE COOKIES-->
<div id="barraaceptacion" class="col-lg-12" style="display: block;">
    <div class="inner">
        We request your permission to obtain statistical data of your browsing on this website,
  		in compliance with Royal Decree-Law 13/2012. 
        If you continue browsing we consider that you accept the use of cookies.
        <br>
        <a href="javascript:void(0);" class="ok" onclick="PonerCookie();"><b>OK</b></a> | 
        <a href="http://politicadecookies.com" target="_blank" class="info">More info</a>
    </div>
</div>
 
<script>
function getCookie(c_name){
    var c_value = document.cookie;
    var c_start = c_value.indexOf(" " + c_name + "=");
    if (c_start == -1){
        c_start = c_value.indexOf(c_name + "=");
    }
    if (c_start == -1){
        c_value = null;
    }else{
        c_start = c_value.indexOf("=", c_start) + 1;
        var c_end = c_value.indexOf(";", c_start);
        if (c_end == -1){
            c_end = c_value.length;
        }
        c_value = unescape(c_value.substring(c_start,c_end));
    }
    return c_value;
}
 
function setCookie(c_name,value,exdays){
    var exdate=new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
    document.cookie=c_name + "=" + c_value;
}
 
if(getCookie('tiendaaviso')!="1"){
    document.getElementById("barraaceptacion").style.display="block";
}
function PonerCookie(){
    setCookie('tiendaaviso','1',365);
    document.getElementById("barraaceptacion").style.display="none";
}
</script>
<!--//FIN BLOQUE COOKIES-->