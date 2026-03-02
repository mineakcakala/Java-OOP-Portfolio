package com.odemesistemi;

public class DigitalProduct  extends Product{
	private double discount;
	
	public DigitalProduct (String name,double price,double discount) {
		super(name, price);
		this.setDiscount(discount);
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public double calculatePayment() {
		
		return getPrice() - getDiscount();//Total cost;
	}

	@Override
	public String toString() {
		
		return super.toString() +"discount:" + getDiscount();
	}
	
	

	
}
