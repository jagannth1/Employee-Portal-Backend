package com.vcube.empportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.empportal.model.Employee;
import com.vcube.empportal.repo.EmployeeRepo;

@RestController
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "https://employee-portal-frontend-i8k6s2z12-jagannath-portal.vercel.app",
	    "https://employee-portal-frontend-kp8a3hu9g-jagannath-portal.vercel.app"
	})
public class HelloController {
	@Autowired
	EmployeeRepo employeeRepo;

	// http://localhost:9999/hello
	@GetMapping("/hello")
	String hello() {
		return "Spring boot is Majedar";
	}

	// http://localhost:9999/getEmpList
	@GetMapping("/getEmpList")
	List<Employee> getAllEmployeedetails() {

		return employeeRepo.findAll();
	}

//    http://localhost:9999/getEmp/{1,2,3,4} only one e id you can give
	@GetMapping("/getEmp/{eid}")
	Employee getEmployee(@PathVariable Integer eid) {
		return employeeRepo.findById(eid).orElseThrow();

	}

	@PostMapping("/createEmp")
	Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@PutMapping("/updateEmp/{eid}")
	Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer eid) {
		
		Employee empFromDb = getEmployee(eid);
		
		empFromDb.setAge(employee.getAge());
		empFromDb.setCity(employee.getCity());
		empFromDb.setEname(employee.getEname());
		empFromDb.setSalary(employee.getSalary());
		empFromDb.setState(employee.getState());
	
		return employeeRepo.save(empFromDb);
		
		
	}
	
	@DeleteMapping("/delEmp/{eid}")
	String deleteEmployee(@PathVariable Integer eid){
		 employeeRepo.deleteById(eid);
		return "Employee Deleted Successfully whom eid is" + eid;
	}
	
	
}
