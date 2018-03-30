<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="error.jsp"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
HttpSession hs = request.getSession();
String meth = (String)hs.getAttribute("paymet");
String items = (String)hs.getAttribute("items");
String cost = (String)hs.getAttribute("cost");
String card_no= (String)hs.getAttribute("cardno");
System.out.print("pincno:"+card_no);
System.out.print("pincost:"+cost);
System.out.print("pinmeth:"+meth);

%>
<form method='post' action='pinnum'>
<input type="hidden" name="card_no" value="<%=card_no%>">
<input type="hidden" name="paymet" value="<%=meth%>">
<input type="hidden" name="items" value="<%=items%>">
<input type="hidden" name="cost" value="<%=cost%>">
<input type='text' name='pinvalue' placeholder='PIN Number'>
<input type= "submit" value="submit pin">
</form>
</body>
</html>