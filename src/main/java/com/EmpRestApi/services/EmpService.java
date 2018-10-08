package com.EmpRestApi.services;

import java.util.List;

import com.EmpRestApi.entity.Employee;

public interface EmpService {
	
	public Employee getEmployeeById(String empId);
	
	public void createEmployee(Employee employee);
	
	public boolean isEmployeeExist(Employee employee);
	
	public List<Employee> getEmployeeList();

}
