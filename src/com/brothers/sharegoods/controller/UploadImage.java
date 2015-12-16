package com.brothers.sharegoods.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.brothers.sharegoods.config.GetFilePath;
import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.PhotoBean;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
@MultipartConfig
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
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
		System.out.println("Upload!");
		String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
		Part filePart = request.getPart("goods"); // Retrieves <input type="file" name="file">
//		String fileName = filePart.getSubmittedFileName();
		Date date = new Date();
		String name = ""+date.getYear()+date.getMonth()+date.getDay()+ date.getHours() + date.getMinutes();
		try {
			InputStream fileContent = filePart.getInputStream();
			File uploads = new File(GetFilePath.getPicPath() + name +".jpg");
			Files.copy(fileContent, uploads.toPath());
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Desc:" + description);
		PhotoBean newPhoto = new PhotoBean();
		newPhoto.setImageUri(name);
		newPhoto.setLikePhoto(0);
		newPhoto.setUserId(Integer.parseInt(request.getSession(false).getAttribute("id").toString()));
		newPhoto.setDescription(description);
		
		ModelApplier.createPhoto(newPhoto);
		
		System.out.println("Done");
		response.sendRedirect("./HomePage?home=0");
	}

}
