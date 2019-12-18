package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.User;

public interface EmployeeDAO {
	public List<Employee> findAll();
	public Employee findById(int id);
	public boolean insert(Employee e, String password);
	public boolean update(Employee e);
}
