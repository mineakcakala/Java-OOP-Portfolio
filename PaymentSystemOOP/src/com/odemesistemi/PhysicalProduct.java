package com.odemesistemi;


public class PhysicalProduct extends Product {

	private double shippingCost;//nakliye maliyeti
	
	public PhysicalProduct(String name,double price,double shippingCost) {
		super(name, price);
		this.setShippingCost(shippingCost);
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	@Override
	public double calculatePayment() {
		
		return getPrice() + getShippingCost();
	}

	@Override
	public String toString() {
		
		return super.toString() + "shippingCost=" + getShippingCost();
	}
	
	
	
}
