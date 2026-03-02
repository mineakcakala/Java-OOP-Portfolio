package com.rentacar.model;

public class Suv extends Arac {
 private boolean dortCekerMi;
 
 public Suv(String plaka,String marka,String model, double günlükKiralamaBedeli ,boolean dortCekerMi ) {
	 super(plaka, marka, model, günlükKiralamaBedeli);
 }

public boolean isDortCekerMi() {
	return dortCekerMi;
}

public void setDortCekerMi(boolean dortCekerMi) {
	this.dortCekerMi = dortCekerMi;
}

@Override
public double fiyatHesapla(int gunSayısı) {
	
	if(dortCekerMi=true) {
		return (gunSayısı * getGunlukKiralamaBedeli()) * 1.10;
	}

	return gunSayısı * getGunlukKiralamaBedeli();
	
}
 
 
}
