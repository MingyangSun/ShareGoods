package com.brothers.sharegoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.LikedPhotoBean;
import com.brothers.sharegoods.model.PhotoBean;

/**
 * Servlet implementation class LikePhoto
 */
@WebServlet("/LikePhoto")
public class LikePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LikePhoto");
		
		int id = 0;
		if(request.getSession(false) != null && request.getSession(false).getAttribute("id") != null) {
			id = Integer.parseInt(request.getSession(false).getAttribute("id").toString());
		}
		if(id == 0) {
			response.sendRedirect("./Index.jsp");
			return ;
		}
		String photoId = request.getParameter("photo");
		if(photoId != null) {
			LikedPhotoBean likedPhoto = new LikedPhotoBean();
			likedPhoto.setUserId(id);
			likedPhoto.setPhotoId(Integer.parseInt(photoId));
			
			//Get the PhotoBean
			PhotoBean photo = ModelApplier.getPhotoByPhotoId(Integer.parseInt(photoId));
			
			//Determine whether the user has liked the photo
			LikedPhotoBean temp = ModelApplier.getLikedPhotoByUserAndPhoto(likedPhoto.getUserId(), likedPhoto.getPhotoId());
			if(temp.getUserId() == 0) {
				photo.setLikePhoto(true);
				ModelApplier.createLikedPhotot(likedPhoto);
				ModelApplier.updateLikedPhoto(photo.getPhotoId(), photo.getLikePhoto());
			} else {
				photo.setLikePhoto(false);
				ModelApplier.deleteLikedPhoto(likedPhoto);
				ModelApplier.updateLikedPhoto(photo.getPhotoId(), photo.getLikePhoto());
			}
			response.getWriter().write(""+ photo.getLikePhoto());
			return ;
		} 
		response.getWriter().write("no");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
