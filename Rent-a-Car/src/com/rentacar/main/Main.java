package com.rentacar.main;

import java.util.Scanner;
import com.rentacar.model.*;
import com.rentacar.servis.*;
import com.rentacar.exception.*;




public class Main {

	public static void main(String[] args) {
		//Interface kullanarak servisi çağırıyoruz (Kurumsal Mimari)
		
		IAracService service  =new AracKiralamaServisi();
		Scanner scanner =new Scanner(System.in);
		
		
		//Test etmek  kolay olsun diye baştan birkaç araç ekleyelim 
		
	demoVerisiYukle(service);
	
	System.out.println("----------------------------");
	System.out.println(" RENT-A-CAR SİSTEMİNE HOŞGELDİNİZ ");
	
	while(true) {
		System.out.println("/lütfen işleminizi seçin:");
		
	     System.out.println("1-Müsait araçları listele");
	     System.out.println("2-Araç kirala");
	     System.out.println("3-Araç teslim al(iade)");
	     System.out.println("4-Araç sil");
	     System.out.println("0-Çıkış");
	     System.out.println("Seçiminiz:");
	     
	     
	     int secim=scanner.nextInt();
	      scanner.nextLine();
	      if(secim==0) {
	    	System.out.println("Sistemden çıkılıyor... İyi günler!");
	    	break;
	      }
	    	  //Hata yönetimi :Tüm işlemleri try- catch içine alıyoruz
	    	  try {
	    		  switch(secim) {
	    		  case 1:
	    			  service.musaitAraclariListele();
	    			  break;
	    			  
	    		  case 2:
	    			  System.out.println("kiralanacak araç plakası:");
	    			  String kiraPlaka=scanner.nextLine();
	    			  
	    			  System.out.println("Kaç gün kiralanacak? :");
	    			  int gun= scanner.nextInt();
	    			  scanner.nextLine();//buffer temizleme
	    			  
	    			  System.out.println("Müsteri adı:");
	    			  String musteriAd = scanner.nextLine();
	                     
	    			  System.out.println("Müsteri Telefon:");
	    			  String tel=scanner.nextLine();
	    			  
	    			  System.out.println("Müsteri ehliyet no:");
	    			  String ehliyetno = scanner.nextLine();
 	    			  
	    			Musteri musteri =new Musteri(musteriAd, tel,ehliyetno );
	    			
	    			//servis modunu çağırıyoruz
	    			
	    			service.aracKirala(kiraPlaka, gun, musteri);
	    			break;
	    			
	    		  case 3:
	    			  System.out.println("Teslim alınacak araç plakası:");
	    			  String iadePlaka= scanner.nextLine();
	    			  service.aracTeslimAl(iadePlaka);
	    			  break;
	    			  
	    		  case 4:
	    			  System.out.println("Silinecek araç plakası:");
	    			  String silPlaka= scanner.nextLine();
	    			  service.aracSil(silPlaka);
	    			  break;
	    			  
	    			  default:
	    				  System.out.println("Hatalı seçim yaptınız!");
	    			 
	    		  }
	    		  
	    	  }
	    	  catch (AracBulunamadıException e) {
	    		  
				System.out.println("Hata :" + e.getMessage());
			}catch (AracMusaitDegilException e) {
				
				System.out.println("Hata :"+ e.getMessage());
			}catch (Exception e) {
				System.out.println("Beklenmedik bir hata oluştu:" +e.getMessage());	
			}
	      }   
	scanner.close();

	}
	
	
	//Sistema hazır araçlar yükleyen yardımcı metot
	
	 private static void demoVerisiYukle(IAracService service) {
		 //Polymorphism : Otomobil ve SUV nesnelrini aynı metoda gönderiyoruz
		 Arac a1 =new Otomobil("34AB123","Toyota", "Corolla", 1200.0, 4);
		 Arac a2 =new Suv("06CD1456", "Range Rover", "Sport", 5000.0,true);
		 Arac a3 = new Otomobil("35EF789","Renault", "Clio", 900.0, 4);
		 Arac a4 =new Suv("01GH012", "Dacia", "Duster", 2000.0,false);//4 çeker değil
		 
		 service.aracEkle(a1);
		 service.aracEkle(a2);
		 service.aracEkle(a3);
		 service.aracEkle(a4);
		 
		 System.out.println("Demo verileri yüklendi..../n");
	 }
	
	

}
