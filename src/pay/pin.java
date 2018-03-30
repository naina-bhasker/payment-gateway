package pay;
import db.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pin
 */
@WebServlet("/pin")
public class pin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter pw = response.getWriter();
		String meth = request.getParameter("paymet");
		System.out.println(meth);
		String cost = request.getParameter("cost");

		String items = request.getParameter("items");
		System.out.println("pin.java"+items);
		pw.write("<html><head>");
		pw.write("<script src='jquery/jquery-latest.js'></script>");
		pw.write("<script type='text/javascript'>");
		pw.write("$(document).ready(function () {");
		pw.write("	 $('body').bind('cut copy paste', function (e) {");
		pw.write("e.preventDefault(); });");
		pw.write("$('body').on('contextmenu',function(e){	        return false;	    });");
		pw.write("	    window.history.pushState(null, '', window.location.href);        ");
		pw.write(" window.onpopstate = function() { window.history.pushState(null, '', window.location.href);     }; }); </script></head>");
		
		pw.write("<body onkeydown='return (event.keyCode != 116)'>");
		DBHelper db= new DBHelper();
		Connection connect =db.getConnection();
		String card_no= request.getParameter("card_no");
		System.out.println("pincard:"+card_no);
		String pin = request.getParameter("pinvalue");
		System.out.println("pininpinjava:"+pin);
		try{
		PreparedStatement st = null;
		ResultSet rs = null;
		st = connect.prepareStatement("select pin from cardtype where card_no = ?;");
		st.setBigDecimal(1, new BigDecimal(card_no));
		rs = st.executeQuery();
		connect.close();
		while(rs.next()){
		int p = rs.getInt(1);
		System.out.println(p);
		
		if(p==Integer.parseInt(pin)){
			DBHelperBank dbhbank = new DBHelperBank();
			Connection con = dbhbank.getConnection();
			if(meth.equals("debitmode")){
			st = con.prepareStatement("select balance from customer where card_no= ?;");
			st.setBigDecimal(1, new BigDecimal(card_no));
			rs = st.executeQuery();
			while(rs.next()){
			int b = rs.getInt(1);
			
			b = b - Integer.parseInt(cost);
			
			st = con.prepareStatement("update customer set balance = "+b+"where card_no= ?;");
			st.setBigDecimal(1, new BigDecimal(card_no));
			int rsupdate = st.executeUpdate();
			con.close();
			if(rsupdate == 1){
			 pw.write("payment received");
			DBHelperHarmony dbhharmony = new DBHelperHarmony();
			Connection conn = dbhharmony.getConnection();
	    		 
	    		PreparedStatement stt = null;
	    		ResultSet rss = null;
	    		stt = conn.prepareStatement("insert into transactions(uid,items,amount,quantity,address,payment_mode,payment_status,order_status) values(?,?,?,?,?,?,?,?)");
	    		stt.setInt(1, 155044);
	    		stt.setString(2, items);
	    		stt.setInt(3, Integer.parseInt(cost));
	    		stt.setInt(4, 2);
	    		stt.setString(5, "Thane");
	    		stt.setString(6, meth);
	    		stt.setString(7, "received");
	    		stt.setString(8, "Not packed");
	    			
	    		int rsupdate1 = stt.executeUpdate();
	    		conn.close();
	    		System.out.println("rsupdated:"+rsupdate1);
				if(rsupdate1 == 1){
				 pw.write("Order booked");
				}
	
			}
			System.out.println(rsupdate);
			}
			}
			if(meth.equals("creditmode")){
				st = con.prepareStatement("select amt from credit where card_no= ?;");
			 st.setBigDecimal(1, new BigDecimal(card_no));
			 rs = st.executeQuery();
			 while(rs.next()){
			 int b = rs.getInt(1);
			 
			 b = b + Integer.parseInt(cost);
			 
			 st = con.prepareStatement("update credit set amt = "+b+"where card_no= ?;");
			 st.setBigDecimal(1, new BigDecimal(card_no));
			 int rsupdate = st.executeUpdate();
			 if(rsupdate == 1){
				 pw.write("payment received");

					DBHelperHarmony dbhharmony = new DBHelperHarmony();
					Connection conn = dbhharmony.getConnection();
		    		 
		    		PreparedStatement stt = null;
		    		ResultSet rss = null;
		    		stt = conn.prepareStatement("insert into transactions(uid,items,amount,quantity,address,payment_mode,payment_status,order_status) values(?,?,?,?,?,?,?,?)");
		    		stt.setInt(1, 155044);
		    		stt.setString(2, items);
		    		stt.setInt(3, Integer.parseInt(cost));
		    		stt.setInt(4, 2);
		    		stt.setString(5, "Thane");
		    		stt.setString(6, meth);
		    		stt.setString(7, "received");
		    		stt.setString(8, "Not packed");
		    			
		    		int rsupdate1 = stt.executeUpdate();
		    		conn.close();
		    		System.out.println("rsupdatec:"+rsupdate1);
					if(rsupdate1 == 1){
					 pw.write("Order booked");
					}
		    	
			 }
			 System.out.println(rsupdate);
			}
		}
		}
		
		else{
			RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
			rd.forward(request, response);
		}
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}

}
