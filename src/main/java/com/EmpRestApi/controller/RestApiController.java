package com.EmpRestApi.controller;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.EmpRestApi.entity.Address;
import com.EmpRestApi.entity.CustomError;
import com.EmpRestApi.entity.Employee;
import com.EmpRestApi.services.EmpService;

@RestController
@RequestMapping(value="/api")
@ComponentScan(value="com.EmpRestApi")
@XmlRootElement(name="RestApiController")
public class RestApiController {
	
	@Autowired
	EmpService empService;
	
	public static final Logger logger=LoggerFactory.getLogger(RestApiController.class);
	
	@RequestMapping(value="/employee/{empId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeeById(@PathVariable String empId){
		System.out.println("111111 http status::OK "+HttpStatus.OK+", Accepted:"+HttpStatus.ACCEPTED+", Not Fuound: "+HttpStatus.NOT_FOUND+", Found: "+HttpStatus.FOUND);
		logger.debug("fetching Employee with id {}:"+empId);
		Employee employee=empService.getEmployeeById(empId);
		if(employee==null){
			logger.info("Ëmployee with id {} not found:",empId);
			return new ResponseEntity(new CustomError("Employee is not found for id: "+empId), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	}
	
	@RequestMapping(value="/employee", method=RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee,UriComponentsBuilder ucBuilder){
		logger.debug("creating EMployee for: "+employee.getName());
		
		if(empService.isEmployeeExist(employee)){
			logger.error("Unable to create Employee for the id "+employee.getId()+" beacause already id exist!!!");
			return new ResponseEntity(new CustomError("Unable to create Employee for the name "
					+employee.getName()+" beacause already name exist!!!"), HttpStatus.CONFLICT);
		}
		
		empService.createEmployee(employee);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/employee/{empId}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/employee", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEployeeList(){
		logger.debug("Fetching employee list!!!");
		
		List<Employee> eList=empService.getEmployeeList();
		if(eList==null){
			logger.error("No employee in data!!!");
			return new ResponseEntity(new CustomError("No employee in database"),HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Employee>>(eList,HttpStatus.OK);
	}
	
	

}
