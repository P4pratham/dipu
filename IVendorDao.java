package com.app.dao;

import java.util.List;
import com.app.pojos.Vendor;

public interface IVendorDao 
{
	Vendor validateVendor(String email,String password);
	List<Vendor> listVendor();
	
	Vendor getVendorDetails(int vid);
	String deleteVendorDetails(Vendor v);
	String updateVendorDetails(Vendor v);
	String registerVendorDetails(Vendor v);
}
