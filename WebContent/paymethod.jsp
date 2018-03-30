<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="error.jsp"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose payment method</title>

 <script src="jquery/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	//Disable cut copy and paste
	 $('body').bind('cut copy paste', function (e) {
	        e.preventDefault();
	    });
	   
	    //Disable mouse right click
	    $("body").on("contextmenu",function(e){
	        return false;
	    });
	    
	    //Disable back button
	    window.history.pushState(null, "", window.location.href);        
        window.onpopstate = function() {
            window.history.pushState(null, "", window.location.href);
        };

});
</script>

 <script>
 function myFunction()
 {
     alert("Click 'OK' and the page will be refreshed");
     location.reload();
 }
        </script>
        
   

 </head>
<body onkeydown="return (event.keyCode != 116)">


<h2>Select payment method</h2>
<form method="post" action="e">
<!-- Can extract the amount, items from session  -->
	<input type="hidden" name="cost" value="5000" >
	<input type="hidden" name="items" value="abc">
	<input type='radio' name='paymet' value='creditmode'> Credit card<br>
	<input type='radio' name='paymet' value='debitmode'> Debit card<br>
	<input type='radio' name='paymet' value='netbank'> Net Banking<br>
	<input type='radio' name='paymet' value='cod'> Cash on Delivery<br>
	<input type='radio' name='paymet' value='giftcard'> Gift card<br><br>
	<input type='submit' value='pay' id='submit'>
	</form>
</body>
</html>