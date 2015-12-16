package com.brothers.sharegoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.PhotoCommentBean;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
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
		if(id == 0) {
			response.sendRedirect("./Index.jsp");
			return ;
		}
		String userId = request.getParameter("user");
		String comment = request.getParameter("comment");
		String photoId = request.getParameter("photo");
		String owner = request.getParameter("owner");
		
		System.out.println("userId:" + userId);
		System.out.println("comment:" + comment);
		System.out.println("photo:" + photoId);
		System.out.println("owner:" + owner);

		PhotoCommentBean cb = new PhotoCommentBean();
		cb.setComments(comment);
		cb.setPhotoId(Integer.parseInt(photoId));
		cb.setUser1Id(id);
		if(!userId.equals("")) {
			cb.setUser2Id(Integer.parseInt(userId));
		} else {
			cb.setUser2Id(Integer.parseInt(owner));
		}
		
		String result = ModelApplier.createPhotoComment(cb);
		if(result.equals("0"))  System.out.println("Do not work!");
		response.getWriter().write("Got");
	}

}
