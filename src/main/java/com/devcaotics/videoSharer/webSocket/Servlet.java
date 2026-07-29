package com.devcaotics.videoSharer.webSocket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Servlet implementation class Servlet
 */

@WebServlet(urlPatterns = "/api/rest/ids", name = "Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> ids = ServiceHolder.getIds();
		
		String responseList = "[";
		
		while(ids.iterator().hasNext()) {
			responseList += "'"+ids.iterator().next()+"',";
		}
		
		responseList = responseList.substring(0, responseList.length()-1)+"]";
		
		response.setContentType("application/json");
		response.getOutputStream().write(responseList.getBytes());
		response.getOutputStream().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}