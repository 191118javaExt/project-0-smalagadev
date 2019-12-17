package com.revature.repositories;

import java.util.List;

import com.revature.models.Admin;

public interface AdminDAO {
	public List<Admin> findAll();
	public Admin findById(int id);
	public boolean insert(Admin a);
	public boolean update(Admin a);
}