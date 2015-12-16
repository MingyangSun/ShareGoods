package com.brothers.sharegoods.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.RelationBean;
import com.brothers.sharegoods.model.UserBean;
import com.brothers.sharegoods.config.URLMapper;

/**
 * Servlet implementation class Browse
 */
@WebServlet("/Browse")
public class Browse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Browse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		
		String follower = request.getParameter("follower");
		String following = request.getParameter("following");
		String user = request.getParameter("user");

		int id = 0;
		if(request.getSession(false) != null&&request.getSession(false).getAttribute("id") != null) {
			id = (int)request.getSession(false).getAttribute("id");
		}
		if(id == 0) {
			response.sendRedirect("./Index.jsp");
			return ;
		}
		if(follower != null) {
			System.out.println("follower is not null!");
			List<UserBean> list = new ArrayList<UserBean>();
			if(follower.equals("0")) {
				List<RelationBean> l = ModelApplier.getRelationByUser2Id(id);
				for(RelationBean rb : l) {
					list.add(ModelApplier.getUserById(rb.getUser1Id()));
				}
			}
			request.setAttribute("follower", list);
			System.out.println("l:" + list.size());
			rd = request.getRequestDispatcher("./Browse.jsp?follower");
			rd.forward(request, response);
			//getServletConfig().getServletContext().getRequestDispatcher("Browse.jsp?follower").forward(request, response);
			return ;
		}
		if(following != null) {
			System.out.println("following is not null!");
			List<UserBean> list = new ArrayList<UserBean>();
			if(following.equals("0")) {
				List<RelationBean> l = ModelApplier.getRelationByUser1Id(id);
				for(RelationBean rb : l) {
					list.add(ModelApplier.getUserById(rb.getUser2Id()));
				}
			}
			request.setAttribute("following", list);
			System.out.println("l:" + list.size());
			rd = request.getRequestDispatcher("./Browse.jsp?following");
			rd.forward(request, response);
			return ;
		}
		if(user != null) {
			request.setAttribute("user", user);
			rd = request.getRequestDispatcher("./Browse.jsp?user");
			rd.forward(request, response);
			return ;
		}
		response.sendRedirect("./Browse.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
