package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	public List<User> findAll();
	public User findById(int id);
	public User findByUsername(String username);
	public boolean insert(User u, String password);
	public boolean update(User u);
}
