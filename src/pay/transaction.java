package pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBHelperHarmony;

/**
 * Servlet implementation class transaction
 */
@WebServlet("/transaction")
public class transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		HttpSession hs = request.getSession();
		String meth = (String)hs.getAttribute("paymet");
		String items = (String)hs.getAttribute("items");
		String cost = (String)hs.getAttribute("cost");
		PrintWriter pw = response.getWriter();
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
		
		
		try{

			DBHelperHarmony dbhharmony = new DBHelperHarmony();
			Connection con = dbhharmony.getConnection(); 
    		PreparedStatement st = null;
    		ResultSet rs = null;
    		st = con.prepareStatement("insert into transactions(uid,items,amount,quantity,address,payment_mode,payment_status,order_status) values(?,?,?,?,?,?,?,?)");
    		st.setInt(1, 155044);
    		st.setString(2, items);
    		st.setInt(3, Integer.parseInt(cost));
    		st.setInt(4, 2);
    		st.setString(5, "Thane");
    		st.setString(6, meth);
    		st.setString(7, "Not received");
    		st.setString(8, "Not packed");
    			
    		int rsupdate = st.executeUpdate();
    		con.close();
			if(rsupdate == 1){
			 pw.write("Order booked");
			}
    		 
    		 
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}

}
