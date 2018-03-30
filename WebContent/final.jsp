<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="db.*,java.sql.*,java.math.BigDecimal" errorPage="error.jsp"
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
<%DBHelper db= new DBHelper();
Connection con =db.getConnection();
String card_no = (request.getParameter("cardno"));
int mm = Integer.parseInt(request.getParameter("MM"));
int yy = Integer.parseInt(request.getParameter("YY"));
int cvv = Integer.parseInt(request.getParameter("cvv"));
String cost = request.getParameter("cost");
String name = request.getParameter("name");
String items = (request.getParameter("items"));
String meth = (request.getParameter("paymet"));
System.out.println("fmeth"+meth);

System.out.println("mm"+mm);
System.out.println("yy"+yy);
System.out.println("cvv"+cvv);
System.out.println("cost"+cost);

PreparedStatement st = null;
ResultSet rs = null;
st = con.prepareStatement("select mm,yy,cvv,type from cardtype where card_no = ?;");
st.setBigDecimal(1, new BigDecimal(card_no));
rs = st.executeQuery();
int mon=0;
int year=0;
int cv=0;
String type="";
RequestDispatcher rd = null;
while(rs.next()){
	mon = rs.getInt(1);
	year = rs.getInt(2);
	cv = rs.getInt(3);
	type = rs.getString(4);
	
	System.out.println("mon"+mon);
	System.out.println("year"+year);
	System.out.println("cv"+cv);
	HttpSession hs = request.getSession();


if((mon == mm) && (yy == year) && (cvv == cv)){
	hs.setAttribute("cardno", card_no);
	hs.setAttribute("cost", cost);
	hs.setAttribute("paymet", meth);
	hs.setAttribute("items", items);
	System.out.print("hfmeth: "+meth);
	rd =  request.getRequestDispatcher("pin.jsp");
	rd.forward(request, response);
}

else{
	rd = request.getRequestDispatcher("failure.jsp");
	rd.forward(request, response);
}
}


%>


</body>
</html>