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

/**
 * Servlet implementation class TopGoods
 */
@WebServlet("/TopGoods")
public class TopGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopGoods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Here is Top Goods!");
		RequestDispatcher rd = null;
		int id = 0;
		if(request.getSession(false) != null&&request.getSession(false).getAttribute("id") != null) {
			id = Integer.parseInt(request.getSession(false).getAttribute("id").toString());
		}
		if(id == 0) {
			response.sendRedirect("./Index.jsp");
			return ;
		}
		String number = request.getParameter("top");
		
		List<PhotoBean> list = ModelApplier.getTopPhoto(Integer.parseInt(number));
		
		request.setAttribute("top", list);
		
		rd = request.getRequestDispatcher("./TopGoods.jsp?top=" + number);
		
		rd.forward(request, response);
		
		return ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
