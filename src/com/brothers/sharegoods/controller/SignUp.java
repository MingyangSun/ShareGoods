package com.brothers.sharegoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.FansBean;
import com.brothers.sharegoods.model.UserBean;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		System.out.println("SignUp!");
		/*
		int id = 0;
		if(request.getSession(false) != null && request.getSession(false).getAttribute("id") != null) {
			id = Integer.parseInt(request.getSession(false).getAttribute("id").toString());
		}
		if(id == 0) {
			response.sendRedirect("./Index.jsp");
			return ;
		}
		*/
		String email = request.getParameter("userEmail");
		String password = request.getParameter("pwd");
		String username = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		System.out.println("email:" + email);
		System.out.println("password:" + password);
		System.out.println("username:" + username);
		System.out.println("gender:" + gender);
		System.out.println("city:" + city);
		System.out.println("country:" + country);
		
		UserBean newUser = new UserBean();
		newUser.setCity(city);
		newUser.setCountry(country);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setSex(gender);
		newUser.setUserName(username);
		
		String newId = ModelApplier.createUser(newUser);
		
		
		System.out.println("newId:" + newId);
		
		FansBean newFans = new FansBean();
		newFans.setUserId(Integer.parseInt(newId));
		newFans.setFollower(0);
		newFans.setFollowing(0);
		
		ModelApplier.createFans(newFans);
		
		response.sendRedirect("./Login.jsp");
	}

}
