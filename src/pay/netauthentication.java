package pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBHelperBank;
import db.DBHelperHarmony;

/**
 * Servlet implementation class netauthentication
 */
@WebServlet("/netauthentication")
public class netauthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public netauthentication() {
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
		String bank = request.getParameter("bank");
		String cost = request.getParameter("cost");
		String uname = request.getParameter("uname");
		String pswd = request.getParameter("pswd");

		String items = request.getParameter("items");
		String meth = request.getParameter("paymet");
		
		try{

			DBHelperBank dbhbank = new DBHelperBank();
			Connection con = dbhbank.getConnection();
			
			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			String sql = ("select pswd from "+bank+" where uname = '"+uname+"';");
			System.out.println(sql);
			rs = st.executeQuery(sql);
			con.close();
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
			if(rs != null){
				while(rs.next()){
					String pwd = rs.getString(1);
						if(pwd.equals(pswd)){
							pw.write("Pay a sum of Rs."+cost+". Click 'Proceed' to confirm");
							pw.write("<form method='post' action = 'netfinal.jsp'>");
							pw.write("<input type='hidden' name='bank' value='"+bank+"'>");
							pw.write("<input type='hidden' name='cost' value='"+cost+"'>");
							pw.write("<input type='hidden' name='paymet' value='"+meth+"'>");
							pw.write("<input type='hidden' name='items' value='"+items+"'>");
							pw.write("<input type='hidden' name='uname' value='"+uname+"'>");
							pw.write("<input type = 'submit' value = 'Proceed'");
							pw.write("</form>");
						}
					else{
						pw.write("Wrong password");
					}
					
				}
			}
			else{
				pw.write("Please check your username");
			}
			pw.write("</body></html>");
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
