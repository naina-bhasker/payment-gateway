package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ey
 */
@WebServlet("/ey")
public class ey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ey() {
        super();
        // TODO Auto-generated constructor stub
    }

  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String meth = request.getParameter("paymet");
		String items = request.getParameter("items");
		String cost = request.getParameter("cost");
		System.out.println(cost);
		System.out.println(meth);
		//response.getWriter().write("True");//data returning to jquery
		RequestDispatcher rd = null;
			if(meth.equals("creditmode")){
				rd = request.getRequestDispatcher("credit");
				System.out.println("inside hs");
				HttpSession hs = request.getSession();
				hs.setAttribute("cost", cost);
				hs.setAttribute("paymet", meth);
				hs.setAttribute("items", items);
				rd.forward(request, response);
			}
			if(meth.equals("debitmode")){
				rd = request.getRequestDispatcher("debit");
				HttpSession hs = request.getSession();
				hs.setAttribute("cost", cost);
				hs.setAttribute("paymet", meth);
				hs.setAttribute("items", items);
				rd.forward(request, response);
			}
			if(meth.equals("giftcard")){
				rd = request.getRequestDispatcher("gift");
				HttpSession hs = request.getSession();
				hs.setAttribute("cost", cost);
				hs.setAttribute("paymet", meth);
				hs.setAttribute("items", items);
				rd.forward(request, response);
			}
			if(meth.equals("netbank")){
				rd = request.getRequestDispatcher("netbk");
				HttpSession hs = request.getSession();
				hs.setAttribute("paymet", meth);
				hs.setAttribute("items", items);
				hs.setAttribute("cost", cost);
				rd.forward(request, response);
			}
			if(meth.equals("cod")){
				rd = request.getRequestDispatcher("trans");
				HttpSession hs = request.getSession();
				hs.setAttribute("paymet", meth);
				hs.setAttribute("items", items);
				hs.setAttribute("cost", cost);
				rd.forward(request, response);
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
