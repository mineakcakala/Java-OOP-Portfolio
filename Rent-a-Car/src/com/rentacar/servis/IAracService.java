package com.rentacar.servis;

import com.rentacar.model.Arac;
import com.rentacar.model.Musteri;
import com.rentacar.exception.AracMusaitDegilException;
import com.rentacar.exception.AracBulunamadıException;

public interface IAracService {
	void aracEkle(Arac arac);
	void aracSil(String plaka) throws AracBulunamadıException;
	void musaitAraclariListele();
	void aracKirala(String plaka , int gunSayisi, Musteri musteri) throws AracMusaitDegilException,AracBulunamadıException;
	void aracTeslimAl(String plaka) throws AracBulunamadıException;
	
	

}
