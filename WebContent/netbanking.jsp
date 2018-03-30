<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="error.jsp"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Netbanking</title>
<script src="jquery/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	 $('body').bind('cut copy paste', function (e) {
	        e.preventDefault();
	    });
	   
	    //Disable mouse right click
	    $("body").on("contextmenu",function(e){
	        return false;
	    });
	    window.history.pushState(null, "", window.location.href);        
        window.onpopstate = function() {
            window.history.pushState(null, "", window.location.href);
        };
});
</script>


</head>
<body>
<%
	String cost = request.getParameter("cost");
String meth = request.getParameter("paymet");
String items = request.getParameter("items");
	String bank = request.getParameter("bnk");
	out.write("<h1>Welcome to "+bank+"</h1>");
%>
	<form method = "post" action="netauthentication">
		<input type="hidden" name="bank" value="<%=bank%>">
		<input type="hidden" name="cost" value="<%=cost%>">
		<input type="hidden" name="paymet" value="<%=meth%>">
		<input type="hidden" name="items" value="<%=items%>">
		Username: <input type = "text" name = "uname"></br>
		Password: <input type = "password" name = "pswd"></br>
		<input type = "submit" value = "login">		
	</form>
</body>
</html>