package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.Vendor;
import com.app.service.IvendorService;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
	//dependency
	@Autowired
	private IvendorService service;
	public AdminController() 
	{
		System.out.println("in constr of "+getClass().getName());
	}
	
	@GetMapping("list") 
	public String showVendorList(Model map)
	{
		System.out.println("in vendor list");
		//save vendor under model map
		map.addAttribute("vendor_list",service.listVendor());
		return "/admin/list"; //forward view name
	}
	
	@GetMapping("/add")
	public String showRegForm(Vendor v) {
		
		//vendor v = new vendor()
		//invoke all getters to bind pojo data to form
		//map.addAttribute("vendor",v); //derived name
		System.out.println("in show reg vendor "+v);
		return "/admin/register"; //forward view name
	}

	
	@PostMapping("/add")
	public String processVendorRegForm(Vendor v,RedirectAttributes flashMap)
	{
		//vendor v = new vendor()
		//invoke all matching setters
		//map.addAttribute("vendor",v); //derived name
		System.out.println("in process reg form of vendor "+v);
		//V -- transient (not in L1 cache and also not in database so in trancieant pojo)
		flashMap.addFlashAttribute("status", service.registerVendor(v));
		return "redirect:/admin/list";
	}
	
	/*@PostMapping("/add")
	public String addVendorRegForm(Model map,@RequestParam String email,
			@RequestParam String password,@RequestParam String name,@RequestParam String city,@RequestParam String phn,
			@RequestParam double regAmount)
	{
		Vendor v = new Vendor(name,email,password,"vednor",city, phn,regAmount,null);
		map.addAttribute("vendor_reg",service.registerVendor(v));
		return "redirect:/admin/list";
	}*/
	
	//rq handling method for showing updating form
	@GetMapping("/update")
	public String showUpdateForm(@RequestParam int id, Model map) {
		
		//vendor v = new vendor()
		//invoke all getters to bind pojo data to form
		//map.addAttribute("vendor",v); //derived name
		System.out.println("in show update form ");
		//load vendor details from DB & attach it to 
		map.addAttribute("vendor", service.getVendorDetails(id));
		return "/admin/update"; //forward view name
	}


	@PostMapping("/update")
	public String processUpdateForm(Vendor v,RedirectAttributes flashMap)
	{
		System.out.println("in process reg form of vendor "+v);
		//V -- transient (not in L1 cache and also not in database so in trancieant pojo)
		flashMap.addFlashAttribute("status", service.updateVendor(v));
		return "redirect:/admin/list";
	}
	
	
	@GetMapping("/delete")
	public String deleteVendor(@RequestParam int id,RedirectAttributes flashMap)
	{
		System.out.println("in delete vendor "+id);
		//hs.setAttribute("status",id);
		flashMap.addFlashAttribute("status", service.deleteVendor(id)); //lifetime = till the next request
		//hs.setAttribute("status", "deleted successfully");
		return "redirect:/admin/list"; // double submit gaurd
	}
	
}
