package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Vendor;
import com.app.service.IvendorService;

@Controller
@RequestMapping("/user")
public class UserController 
{
	//dependency
	@Autowired
	private IvendorService services;
	
	public UserController() 
	{
		System.out.println("In constr of "+getClass().getName());
	}
	
	//req handling method to show login form
	@GetMapping("/login") //@RequestMapping + method = get
	public String showLoginForm()
	{
		System.out.println("In show login form(get)");
		return "/user/login";
	}
	
	//req handling method to show login form
	@PostMapping("/login") //@RequestMapping + method = post
	public String processLoginForm(Model map,@RequestParam String email,@RequestParam String password,HttpSession hs)
	{
		System.out.println("In Process login form(post)");
		try 
		{
			//invoke service layer method
			Vendor v = services.validateVendor(email, password);
			//login success
			map.addAttribute("status", "successfully login .....");
			hs.setAttribute("user_dtls",v);
			//check role
			if(v.getRole().equals("admin"))  //admin login
			{
				return "redirect:/admin/list"; //replace by redirect to validate only once
			}
			//vendor login
			return "/vendor/details";
		} 
		catch (RuntimeException e) 
		{
			System.out.println("err in user controller"+e);
			//invalid login
			map.addAttribute("status", "Invalid login,please try again....");
			return "/user/login";
		}
	}
	
	//req handling method to show login form
	@GetMapping("/logout") //@RequestMapping + method = get
	public String userLogout(Model map,HttpSession hs,HttpServletResponse resp,HttpServletRequest req)
	{
		System.out.println("In logout(get)");
		//save user dtls from session scope --> request scope
		map.addAttribute("dtls",hs.getAttribute("user_dtls"));
		//invalidate user's session
		hs.invalidate();
		//navigate the client to index page (root page) after slight delay
		resp.setHeader("refresh", "5;url="+req.getContextPath());
		return "/user/logout";
	}
}
