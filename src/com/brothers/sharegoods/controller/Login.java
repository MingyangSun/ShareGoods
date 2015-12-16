package com.brothers.sharegoods.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.brothers.sharegoods.function.ModelApplier;
import com.brothers.sharegoods.model.UserBean;
import com.brothers.sharegoods.config.URLMapper;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession(false) == null || !request.getSession(false).getAttribute("isLogin").equals("yes")) {
			response.sendRedirect(URLMapper.HOME_JSP);
		} else {
			response.sendRedirect(URLMapper.LOGIN_JSP);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get the Username And Password
		RequestDispatcher rd = null;
		
		String username = request.getParameter("userEmail");
		String password = request.getParameter("pwd");
		System.out.println("name:"+ username);
		System.out.println("pwd:" + password);
		String errMessage = "";
		if(!username.equals("") && !username.equals("")) {
			UserBean temp = ModelApplier.getUserByUsernameAndPassword(username, password);
			if(temp.getUserId() < 1) {
				errMessage = "The Username or Password is Wrong!"; 
			} else {
				HttpSession session = request.getSession(true);
				session.setAttribute("id", temp.getUserId());
				session.setAttribute("isLogin", "yes");
				session.setAttribute("username", temp.getUserName());
				session.setMaxInactiveInterval(10000);
			}
		} else {
			errMessage = "The Username or Password can't be empty!";
		}
		if(errMessage.equals("")) {
			response.sendRedirect("./HomePage");
		} else {
			request.setAttribute("message", errMessage);
			rd = request.getRequestDispatcher(URLMapper.LOGIN_JSP);
			rd.forward(request, response);
		}
	}

}
