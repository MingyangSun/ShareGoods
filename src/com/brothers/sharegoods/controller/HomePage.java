package com.brothers.sharegoods.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brothers.sharegoods.model.LikedPhotoBean;
import com.brothers.sharegoods.model.PhotoBean;
import com.brothers.sharegoods.model.PhotoCommentBean;
import com.brothers.sharegoods.model.RelationBean;
import com.brothers.sharegoods.function.ModelApplier;



/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		RequestDispatcher rd = null;
		
		int id = 0;
		if(request.getSession(false) != null&&request.getSession(false).getAttribute("id") != null) {
			id = Integer.parseInt(request.getSession(false).getAttribute("id").toString());
		}
		if(id == 0) {
			response.sendRedirect("./Index.jsp");
			return ;
		}
		
		String photo_id = request.getParameter("photo");
		String home = request.getParameter("home");
		String liked = request.getParameter("liked");
		
		if(liked != null) {
			List<PhotoBean> likedphoto = new ArrayList<PhotoBean>();
			List<LikedPhotoBean> l = ModelApplier.getLikedPhotoByUserId(id);
			for(LikedPhotoBean lp : l) {
				likedphoto.add(ModelApplier.getPhotoByPhotoId(lp.getPhotoId()));
			}
			System.out.println("size:" + likedphoto.size());
			request.setAttribute("liked", likedphoto);
			rd = request.getRequestDispatcher("./HomePage.jsp?liked");
			rd.forward(request, response);
			return ;
		}
		
		//Display all the photoes
		if(photo_id ==  null && home == null) {
			List<PhotoBean> list = new ArrayList<PhotoBean>();
			List<RelationBean> followingList = new ArrayList<RelationBean>();
			
			//Get Following User Id
			followingList = ModelApplier.getRelationByUser1Id(id);
			
			//Get Photo of Followings
			for(RelationBean rb : followingList) {
				List<PhotoBean> tempList = ModelApplier.getPhotoByUserId(rb.getUser2Id());
				list.addAll(tempList);
			}
			
			if(list.size() > 0) {
				request.setAttribute("photoes", list);
			}
			rd = request.getRequestDispatcher("./HomePage.jsp");
			rd.forward(request, response);
			return ;
		}
		//Display Photoes belongs to the login user
		else if(home != null) {
			List<PhotoBean> homePhotoList = ModelApplier.getPhotoByUserId(id);
			request.setAttribute("myPhoto", homePhotoList);
			rd = request.getRequestDispatcher("./HomePage.jsp?home");
			rd.forward(request, response);
			return ;
		}
		//Display only one photo
		else {
			int photoId = Integer.parseInt(photo_id);
			PhotoBean photo = ModelApplier.getPhotoByPhotoId(photoId);
			List<PhotoCommentBean> comments = ModelApplier.getCommentByPhotoId(photoId);
			request.setAttribute("photo", photo);
			request.setAttribute("comment", comments);
			rd = request.getRequestDispatcher("HomePage.jsp?photo");
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
