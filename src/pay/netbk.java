package pay;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class netbk
 */
@WebServlet("/netbk")
public class netbk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public netbk() {
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
		PrintWriter pw = response.getWriter();
		HttpSession hs = request.getSession();
		String cost = (String)hs.getAttribute("cost");
		String meth = (String)hs.getAttribute("paymet");
		String items = (String)hs.getAttribute("items");
		pw.write("<html><head>");
		pw.write("<script src='jquery/jquery-latest.js'></script>");
		pw.write("<script type='text/javascript'>");
		pw.write("$(document).ready(function () {");
		pw.write("	 $('body').bind('cut copy paste', function (e) {");
		pw.write("e.preventDefault(); });");
		pw.write("$('body').on('contextmenu',function(e){	        return false;	    });");
		pw.write("	    window.history.pushState(null, '', window.location.href);        ");
		pw.write(" window.onpopstate = function() { window.history.pushState(null, '', window.location.href);     }; }); </script></head>");
		
		pw.write("<body onkeydown='return (event.keyCode != 116)'><form method='post' action='netbanking.jsp'>");
		pw.write("");
		pw.write("<h5>Pay using Net Banking</h5> ");
		pw.write("<input type='hidden' name='cost' value="+cost+" >");
		pw.write("<input type='hidden' name='paymet' value="+meth+" >");
		pw.write("<input type='hidden' name='items' value="+items+" >");
		pw.write("Select your bank: <select name='bnk'><option value='bnk'>Choose your bank");
		pw.write("<option value='SBI'>SBI");
		pw.write("<option value='HDFC'>HDFC");
		pw.write("<option value='Canara'>Canara");
		pw.write("</select></br>");
		pw.write("<input type='submit' value='Pay Now'");
		pw.write("</body></html>");
		
	}

}
