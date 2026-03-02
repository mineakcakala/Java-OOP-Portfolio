package com.rentacar.exception;

//Exception sınıfından miras alıyoruz ki java bunu "Hata" olarak tanısın .
public class AracMusaitDegilException extends Exception {

	//Hatayı fırlatırken içine bir mesaj(örn:"bu araç zaten kirada!")yazabilmek için.
	public AracMusaitDegilException(String mesaj) {
		super(mesaj);
	}
	
}
