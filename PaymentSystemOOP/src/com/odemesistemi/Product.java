package com.odemesistemi;

public  abstract class Product extends Object implements Payable  {
	private String name;
	private double price;
	
	//constructor
	public Product (String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		
		return "Product name=" + getName() +",Price=" + getPrice();
	}
	
	

}
