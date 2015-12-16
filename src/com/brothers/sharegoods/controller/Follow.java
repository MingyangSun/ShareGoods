package com.brothers.sharegoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.FansBean;
import com.brothers.sharegoods.model.RelationBean;

/**
 * Servlet implementation class Follow
 */
@WebServlet("/Follow")
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Follow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("./Index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		if(request.getSession(false) != null && request.getSession(false).getAttribute("id") != null) {
			id = Integer.parseInt(request.getSession(false).getAttribute("id").toString());
		}
		String userId = request.getParameter("user");
		
		FansBean fb1 = ModelApplier.getFansByUserId(id);
		FansBean fb2 = ModelApplier.getFansByUserId(Integer.parseInt(userId));
		
		//Determine whether current user is following the user
		RelationBean rb = ModelApplier.getRelationByAllUser(id, Integer.parseInt(userId));
		
		if(rb != null && rb.getUser1Id() != 0) {
			//not follow
			ModelApplier.deleteRelation(rb);
			fb1.setFollowing(false);
			fb2.setFollower(false);
			ModelApplier.updateFans(fb1);
			ModelApplier.updateFans(fb2);
		} else {
			//follow
			rb = new RelationBean();
			rb.setUser1Id(id);
			rb.setUser2Id(Integer.parseInt(userId));
			ModelApplier.createRelation(rb);
			fb1.setFollowing(true);
			fb2.setFollower(true);
			ModelApplier.updateFans(fb1);
			ModelApplier.updateFans(fb2);
		}
		response.getWriter().write("Done");
	}

}
