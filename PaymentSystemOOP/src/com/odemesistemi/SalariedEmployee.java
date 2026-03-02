package com.odemesistemi;

public class SalariedEmployee extends Employee {
	private double weeklySalary;
	
	public SalariedEmployee (String name,String EmployeeID, String department, double weeklySalary) {
		super(name, EmployeeID, department);
		this.setWeeklySalary(weeklySalary);
	}

	public double getWeeklySalary() {
		return weeklySalary;
	}

	public void setWeeklySalary(double weeklySalary) {
		this.weeklySalary = weeklySalary;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() +",Weekly Salary:" + weeklySalary;
	}

	@Override//Abstract sınıfın zorunlu kıldığı metodu dolduruyoruz
	public double calculatePayment() {
		return weeklySalary;
	}

	@Override
	public double calculatePay() {
		
		return 0;
	}
	

}
