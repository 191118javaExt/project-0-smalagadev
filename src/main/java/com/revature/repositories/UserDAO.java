package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	public List<User> findAll();
	public User findById(int id);
	public boolean insert(User u);
	public boolean update(User u);
}
