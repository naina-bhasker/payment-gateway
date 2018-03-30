<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.sql.*" errorPage="error.jsp"
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
Class.forName("org.postgresql.Driver");
Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank","postgres","test");
System.out.println("Successful!!");

String bank = request.getParameter("bank");
String meth = request.getParameter("paymet");
String items = request.getParameter("items");
int cost = Integer.parseInt(request.getParameter("cost"));
String uname = request.getParameter("uname");
System.out.println("netfinal.jsp :"+items);


Statement st = null;
ResultSet rs = null;
st = con.createStatement();
String sql = ("select balance from "+bank+" where uname = '"+uname+"';");
System.out.println(sql);
rs = st.executeQuery(sql);
while(rs.next()){
	float bal = rs.getFloat(1);
	System.out.print("bal: "+bal);
	if(bal >= cost){
		bal = bal - cost;
		Statement pst = con.createStatement();
		String usql = ("update "+bank+" set balance = "+bal+" where uname = '"+uname+"';");
		int rsupdate = pst.executeUpdate(usql);
		if(rsupdate == 1){
			out.print("Payment received");
			//Enter code here
			Class.forName("org.postgresql.Driver");
    		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/harmony","postgres","test");
    		System.out.println("Successful!!");
    		 
    		PreparedStatement stt = null;
    		ResultSet rss = null;
    		stt = conn.prepareStatement("insert into transactions(uid,items,amount,quantity,address,payment_mode,payment_status,order_status) values(?,?,?,?,?,?,?,?)");
    		stt.setInt(1, 155044);
    		stt.setString(2, items);
    		stt.setInt(3, cost);
    		stt.setInt(4, 2);
    		stt.setString(5, "Thane");
    		stt.setString(6, meth);
    		stt.setString(7, "received");
    		stt.setString(8, "Not packed");
    			
    		int rsupdate1 = stt.executeUpdate();
			if(rsupdate1 == 1){
			 out.write("Order booked");
			}

		}
		else{
			out.print("Insufficient balance");
		}
		
	}
	else{
		out.print("Not enough balance. Transaction failed!");
	}


	}


%>
</body>
</html>