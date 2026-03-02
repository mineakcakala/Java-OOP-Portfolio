package com.rentacar.model;

public class Otomobil extends Arac {
	private int kapiSayisi;
	
	public Otomobil(String plaka , String marka,String model, double günlükKiralanmaBedeli, int kapiSayisi) {
	super(plaka, marka, model, günlükKiralanmaBedeli);
	 this.setKapiSayisi(kapiSayisi);
	 
	}

	@Override
	public double fiyatHesapla(int gunSayısı) {
		
		return gunSayısı*getGunlukKiralamaBedeli();
	}

	@Override
	public String toString() {
		return "Otomobil [" + getMarka() + " " + getModel() + 
				"-Plaka" + getPlaka() +
				"-Kapı" + getKapiSayisi() +
				"-Günlük" + getGunlukKiralamaBedeli() +"TL]";
	}

	public int getKapiSayisi() {
		return kapiSayisi;
	}

	public void setKapiSayisi(int kapiSayisi) {
		this.kapiSayisi = kapiSayisi;
	}
	
	

}
