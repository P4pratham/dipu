package com.app.dao;

import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.pojos.Vendor;

@Repository
public class VendorDaoImpl implements IVendorDao {

	//dependency
	@Autowired //byType
	private SessionFactory sf;
	
	public VendorDaoImpl() 
	{
		System.out.println("In constr of "+getClass().getName());
	}
	
	@Override
	public Vendor validateVendor(String email, String password) 
	{
		String jpql="select v from Vendor v where v.email=:em and v.password=:pass";
		return sf.getCurrentSession().createQuery(jpql,Vendor.class).
				setParameter("em", email).setParameter("pass", password).getSingleResult();
	}

	@Override
	public List<Vendor> listVendor() 
	{	
		String jpql="select v from Vendor v where v.role=:role";
		return sf.getCurrentSession().createQuery(jpql,Vendor.class).setParameter("role", "vendor").getResultList();
	}

	@Override
	public String deleteVendorDetails(Vendor v) //detached pojo reference in argument 
	{
		//Vendor v=sf.getCurrentSession().get(Vendor.class,id)
		sf.getCurrentSession().delete(v);
		return "Vendor with id "+v.getId()+" deleted....";
	}

	@Override
	public String registerVendorDetails(Vendor v) 
	{
		//V-transient 
		
		//sf.getCurrentSession().save(v); //save() returns serializable id generated for your persistent entity
		//void persist(object obj)   transient to persistent
		sf.getCurrentSession().persist(v); // persistent return void
		return "Vendor details with id "+v.getId()+" inserted....";
	}

	@Override
	public Vendor getVendorDetails(int vid) {
		return sf.getCurrentSession().get(Vendor.class,vid);
	}

	@Override
	public String updateVendorDetails(Vendor v) {
		// v- detached Vendor POJO containing
		//updated vendor dtls
		//detached -----> persistent
		sf.getCurrentSession().update(v); //V - persistent
		//hib will perform auto dirty checking (update query)
		//update querywill be fired upon commit
		return "Vendor with id "+v.getId()+" updated....";
	}

}
