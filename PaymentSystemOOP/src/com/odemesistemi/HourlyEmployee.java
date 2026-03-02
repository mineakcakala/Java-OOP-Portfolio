package com.odemesistemi;

public class HourlyEmployee extends Employee {
	
	private double hourlyRate;
	private double hoursWorked;
	
	//Constructor
	public HourlyEmployee(String name, String employeeID, String department, double hourlyRate,double hoursWorked) {
		super(name, employeeID, department);
		this.setHourlyRate(hourlyRate);
		this.setHoursWorked(hoursWorked);
		
	}

	
//Getter and Setter method 
	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}


//	@Override calculatePay and toString
	public double calculatePay() {
		
		return hourlyRate * hoursWorked ;
	}


	@Override
	public String toString() {
		
		return super.toString() + ", Pay (Hourly): $" + calculatePay();
	}
	
	

}
