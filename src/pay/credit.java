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
 * Servlet implementation class credit
 */
@WebServlet(name = "creditcard", urlPatterns = { "/creditcard" })
public class credit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public credit() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		HttpSession hs = request.getSession();
		String meth = (String)hs.getAttribute("paymet");
		String items = (String)hs.getAttribute("items");
		String cost = (String)hs.getAttribute("cost");
		System.out.println(meth);
		pw.write("<html><head>");
		pw.write("<script src='jquery/jquery-latest.js'></script>");
		pw.write("<script type='text/javascript'>");
		pw.write("$(document).ready(function () {");
		pw.write("	 $('body').bind('cut copy paste', function (e) {");
		pw.write("e.preventDefault(); });");
		pw.write("$('body').on('contextmenu',function(e){	        return false;	    });");
		pw.write("	    window.history.pushState(null, '', window.location.href);        ");
		pw.write(" window.onpopstate = function() { window.history.pushState(null, '', window.location.href);     }; }); </script></head>");
		
		pw.write("<body onkeydown='return (event.keyCode != 116)'><form method='post' action='final.jsp'>");
		pw.write("");
		pw.write("<h5>Pay using Credit Card</h5>  Visa MasterCard Rupay</br>");
		pw.write("<input type='hidden' name='cost' value="+cost+" >");
		pw.write("<input type='hidden' name='paymet' value="+meth+" >");
		pw.write("<input type='hidden' name='items' value="+items+" >");
		pw.write("<input type='text' name='cardno' placeholder='Card Number'></br><select name='MM'><option value='mm'>MM");
		for(int i = 1; i < 13; i++){
			pw.write("<option value='"+i+"'>"+i);
		}
		pw.write("</select>");
		pw.write("<select name='YY'><option value='yy'>YY");
		for(int i = 2018; i < 2028; i++){
			pw.write("<option value='"+i+"'>"+i);
		}
		pw.write("</select></br>");
		pw.write("<input type='text' name='cvv' placeholder='CVV'></br>");
		pw.write("<input type='text' name='name' placeholder='Name on Card'></br>");
		pw.write("<input type='submit' value='Pay Now'");
		pw.write("</body></html>");
	}

}
