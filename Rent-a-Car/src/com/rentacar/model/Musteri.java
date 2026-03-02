package com.rentacar.model;

public class Musteri {
	private String isim;
	private String telefon;
	private String ehliyetNo;
	
	public Musteri(String isim, String telefon,String ehliyetNo) {
		this.setIsim(isim);
		this.setTelefon(telefon);
		this.setEhliyetNo(ehliyetNo);
	}

	public String getEhliyetNo() {
		return ehliyetNo;
	}

	public void setEhliyetNo(String ehliyetNo) {
		this.ehliyetNo = ehliyetNo;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}
	
	

}
