package com.brothers.sharegoods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.PhotoBean;
import com.brothers.sharegoods.model.UserBean;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		if(request.getSession(false) != null && request.getSession(false).getAttribute("id") != null) {
			id = Integer.parseInt(request.getSession(false).getAttribute("id").toString());
		}
		if(id == 0) {
			response.sendRedirect("./Index.jsp");
			return ;
		}
		RequestDispatcher rd = null;
		
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		System.out.println("Search:" + search);
		
		if(category.equals("goods")) {
			List<PhotoBean> list = ModelApplier.getSearchPhoto(search);
			request.setAttribute("search", search);
			request.setAttribute("SearchResultPhoto", list);
			rd = request.getRequestDispatcher("./Browse.jsp?goods=0");
			rd.forward(request, response);
			return ;
		} else {
			List<UserBean> list = ModelApplier.getSearchUser(search);
			request.setAttribute("search", search);
			request.setAttribute("SearchResultUser", list);
			rd = request.getRequestDispatcher("./Browse.jsp?goods=0");
			rd.forward(request, response);
			return ;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
