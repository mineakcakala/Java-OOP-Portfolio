package com.odemesistemi;

import java.security.PrivateKey;

public  abstract class  Employee implements Payable{
	private String name;
    private String employeeID;
    private String department;
     
    
    //Constructor 
    public Employee (String name,String employeeID, String department) {
    	this.setName(name);
    	this.setEmployeeID(employeeID);
    	this.setDepartment(department);
    
    }

    //Getter and Setter methods for encapsulation

	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	//method to be overriden in each child class
	//abstract yaparak alt sınıftaki metotoların bu metodu kullanmasını zorunlu kılıyoruz böylece
	// her sınıf kendisi için bir calculate pay metodu tanımlamak zorunda 
	public abstract double calculatePay();


	@Override
	public String toString() {
	return "Employee: " + name + ",Department: " + department;
	}
	
	
	//we are implementing the interface at Employee level
	public double calculatePayment() {
		return calculatePay();
	}
	
	
 
}
