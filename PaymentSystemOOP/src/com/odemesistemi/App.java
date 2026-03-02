package com.odemesistemi;

import java.net.SocketTimeoutException;
import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		//Polymorphism Listesi: İçine hem Ürün hem de Çalışan atacağız
		ArrayList<Payable> odemeListesi= new ArrayList<Payable>();
		
		//Nesneleri oluşturup listeye ekliyoruz
		 odemeListesi.add(new HourlyEmployee("Ahmet", "EMP001", "IT", 750.0, 40));
		 odemeListesi.add(new SalariedEmployee("Mehmet", "EMP002", "HR", 15000.0));
		 odemeListesi.add(new PhysicalProduct("Gaming Laptop", 45000.0, 150.0));//fiyat+kargo
		 odemeListesi.add(new DigitalProduct("Antivirüs Lisansı", 1200.0, 200.0));//fiyat-indirim
		 
		 System.out.println("===========AYLIK ÖDEME VE GİDER RAPORU=======");
		 
		 double toplamMaliyet=0;
		 
		 for(Payable p: odemeListesi) {
			 //Hangi sınıf olduğunu java kendi anlar (polymorphism)
			 System.out.println(p.toString());
			 
			 //Herkesin hesaplaması farklıdır ama metod ismi aynıdır
			 
			 double tutar =p.calculatePayment();
			 System.out.println("Ödenecek Tutar: " + tutar +"TL");
              System.out.println("---------------------------------");

              toplamMaliyet += tutar;
              
             
		 }
		 
		 
		 System.out.println("ŞİRKETİN TOPLAM GİDERİ: "+ toplamMaliyet + "TL");
		 
	}

}
