package com.EmpRestApi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.EmpRestApi.entity.Address;
import com.EmpRestApi.entity.Employee;

@Service
public class EmpServiceImpl implements EmpService{
	
	
	List<Employee> empList;
	
	@Override
	public Employee getEmployeeById(String empId){
		System.out.println("emplist size:: "+empList.size());
		//Employee employee=empList.stream().filter(o->o.equals(empId)).collect(Collectors.toList()).get(0);
		
		for(Employee employee:empList){
			if(employee.getId()== Integer.parseInt(empId)){
				return employee;
			}
		}
		return null;
	}
	
	@Override
	public void createEmployee(Employee employee) {
		empList.add(employee);
	}
	
	public List<Employee> getEmployeeList(){
		return empList;
	}
	
	@Override
	public boolean isEmployeeExist(Employee employee){
		for(Employee emp:empList){
			if(emp.getId()==employee.getId()){
				return true;
			}
		}
		return false;
	}
	
	EmpServiceImpl(){

		empList=new ArrayList<Employee>();
		Employee employee1;
		employee1=new Employee();
		employee1.setId(10);
		employee1.setName("harry");
		Address address1=new Address();
		address1.setCity("Bangalore");
		address1.setState("KA");
		address1.setHouseNo(123);
		employee1.setAddress(address1);
		
		Employee employee2;
		employee2=new Employee();
		employee2.setId(30);
		employee2.setName("ajay");
		Address address2=new Address();
		address2.setCity("Gwalior");
		address2.setState("MP");
		address2.setHouseNo(1122);
		employee2.setAddress(address2);
		
		Employee employee3;
		employee3=new Employee();
		employee3.setId(20);
		employee3.setName("brijendra");
		Address address3=new Address();
		address3.setCity("Gurgaon");
		address3.setState("HR");
		address3.setHouseNo(1223);
		employee3.setAddress(address3);
		
		empList.add(employee1);
		empList.add(employee2);
		empList.add(employee3);	
	
	}

}
