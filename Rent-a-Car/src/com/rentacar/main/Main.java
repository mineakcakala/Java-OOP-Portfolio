package com.rentacar.main;



import java.sql.Connection;//Connection: Az önce yazdığımız bağlantı köprüsü
import java.sql.PreparedStatement;  //PreparedStatement: SQL sorgumuzu (SELECT...) veritabanına taşıyan kurye.
import java.sql.SQLException; 
import java.sql.ResultSet; //ResultSet: Veritabanından dönen tabloyu satır satır okumamızı sağlayan sonuç kutusu.

import com.rentacar.model.*;
import com.rentacar.util.DatabaseConnection;


public class Main {

    // 1. Metodumuz main'in DIŞINDA ama sınıfın İÇİNDE yer almalı (Ve static olmalı)
    public static void veritabanindanAraclariListele() {
    	
        String sql = "SELECT * FROM Araclar WHERE MusaitMi = 1";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {                               //executeQuery(): Bu metod sadece SELECT sorgularında kullanılır ve bize bir sonuç tablosu (ResultSet) döndürür.
                                                                 
            System.out.println("--- SQL SERVER'DAN GELEN MÜSAİT ARAÇLAR ---");
            
            while (rs.next()) {                                                  //rs.next(): Tablodaki bir sonraki satıra geçer. Okuyacak satır varsa true, bittiyse false döner.
                String plaka = rs.getString("Plaka");
                String marka = rs.getString("Marka");     //getString("Marka"): Bulunduğu satırdaki "Marka" kolonunun altındaki veriyi Java'daki bir String değişkene aktarır.
                String model = rs.getString("Model");
                double fiyat = rs.getDouble("GunlukKiralamaBedeli");
                String tip = rs.getString("AracTipi");
                
                System.out.println("🚘 " + tip + " | " + marka + " " + model + " | Plaka: " + plaka + " | Günlük: " + fiyat + " TL");
            }
            System.out.println("-------------------------------------------");
            
        } catch (SQLException e) {
            System.out.println("Veritabanından araçlar çekilirken hata oluştu: " + e.getMessage());
        }
    }
    

    
    /*public static void yeniAracEkle() {
        // Değerlerin yerine ? koyuyoruz. Güvenliği Java'ya bırakıyoruz.
        String sql = "INSERT INTO Araclar (Plaka, Marka, Model, GunlukKiralamaBedeli, MusaitMi, AracTipi, KapiSayisi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Soru işaretlerinin yerine gelecek değerleri sırasıyla (1'den başlayarak) dolduruyoruz:
            pstmt.setString(1, "34TEST99");      // 1. ? -> Plaka
            pstmt.setString(2, "Honda");         // 2. ? -> Marka
            pstmt.setString(3, "Civic");         // 3. ? -> Model
            pstmt.setDouble(4, 1500.00);         // 4. ? -> GunlukKiralamaBedeli
            pstmt.setBoolean(5, true);           // 5. ? -> MusaitMi (1 olarak veritabanına gider)
            pstmt.setString(6, "Otomobil");      // 6. ? -> AracTipi
            pstmt.setInt(7, 4);                  // 7. ? -> KapiSayisi
            
            // executeQuery() sadece okumak içindi. Yazma, silme, güncelleme için executeUpdate() kullanılır.
            int etkilenenSatir = pstmt.executeUpdate();
            
            if (etkilenenSatir > 0) {
                System.out.println("✅ Yeni araç veritabanına başarıyla kaydedildi!");
            }
            
        } catch (SQLException e) {
            System.out.println("Araç eklenirken hata oluştu: " + e.getMessage());
        }
    }
    */
    
    
    
    //bu işlemleri Arac, Otomobil ve Suv nesnelerinle (OOP mimarinle) nasıl tam entegre edilmiş (Polymorphism Sahnede!) şekil
    public  static void araciVeritabaninaEkle(Arac arac) {
        // Tüm kolonları kapsayan tek bir INSERT sorgusu
        String sql = "INSERT INTO Araclar (Plaka, Marka, Model, GunlukKiralamaBedeli, MusaitMi, AracTipi, KapiSayisi, DortCekerMi) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // 1. Ortak Özellikler (Arac üst sınıfından gelenler)
            pstmt.setString(1, arac.getPlaka());
            pstmt.setString(2, arac.getMarka());
            pstmt.setString(3, arac.getModel());
            pstmt.setDouble(4, arac.getGunlukKiralamaBedeli());
            pstmt.setBoolean(5, arac.isMusaitMi());
            
            // 2. Çok Biçimlilik (Polymorphism) Devrede! 
            // Gelen araç bir Otomobil mi, yoksa SUV mu? Veritabanındaki ayrımı burada yapıyoruz.
            if (arac instanceof Otomobil) {
                Otomobil oto = (Otomobil) arac; // Aracı güvenle Otomobil'e dönüştür (Casting)
                
                pstmt.setString(6, "Otomobil");
                pstmt.setInt(7, oto.getKapiSayisi()); // Sadece otomobile özel veri
                pstmt.setNull(8, java.sql.Types.BOOLEAN); // SUV'nin DörtÇeker özelliği boş (NULL) kalır
                
            } else if (arac instanceof Suv) {
                Suv suv = (Suv) arac; // Aracı güvenle SUV'a dönüştür
                
                pstmt.setString(6, "SUV");
                pstmt.setNull(7, java.sql.Types.INTEGER); // Otomobilin KapıSayısı özelliği boş (NULL) kalır
                pstmt.setBoolean(8, suv.isDortCekerMi()); // Sadece SUV'a özel veri
            }
            
            // Kuryeyi veritabanına yolla!
            pstmt.executeUpdate();
            System.out.println("✅ " + arac.getMarka() + " " + arac.getModel() + " nesnesi veritabanına başarıyla kaydedildi!");
            
        } catch (SQLException e) {
            System.out.println("Araç eklenirken veritabanı hatası oluştu: " + e.getMessage());
        }
    }
    
    
    //sadece plakasını vererek aracı kiralıyoruz 
    public static void araciKirala(String plaka) {
    	//SQL komutu :plakası verilen aracın MüsaitMi kolonunu 0 (false) yap!
    	
    	String sql ="UPDATE Araclar SET MusaitMi = 0 WHERE plaka=?";
    	
    	try(Connection connection = DatabaseConnection.getConnection();
    			PreparedStatement pstmt = connection.prepareStatement(sql)){
    	
    		//Soru işaretinin (?) yerine dışarıdan gelen plakayı koyuyoruz
    		pstmt.setString(1,plaka);
    		
    		// Veritabanına emri gönderiyoruz(Kaç satırın değiştiğini bize döndürür
    		int etkilenenSatır= pstmt.executeUpdate();
    		
    		if(etkilenenSatır > 0) {
    			System.out.println("Başarılı!" + plaka + "plakalı araç müşteriye kiralandı .(Garajdan çıktı)");
    			
    		}else {
    			System.out.println("Hata ! Veritabanında "+ plaka+"plakalı bir araç bulunamadı.");
    			
    		}
    		
    	}catch (SQLException e) {
			System.out.println("Kiralama işlemi sırasında hata oluştu !" + e.getMessage());
			
		}
    }
    
    //Müşteri aracı geri getirdiğinde çalışacak metod
    public static void araciIadeEt(String plaka) {
    	//SQL Komutu:Plakası verilen aracın MusaitMi kolonunu tekrar 1 (true) yap!
    	String sql ="UPDATE Araclar SET MusaitMi = 1  WHERE plaka=?" ;
    
    			try(Connection conn =DatabaseConnection.getConnection();
    					PreparedStatement pstmt =conn.prepareStatement(sql)){
    				pstmt.setString(1, plaka);
    				
    				int etkilenenSatır=pstmt.executeUpdate();
    				
    				if(etkilenenSatır > 0) {
    					System.out.println("İşlem tamam! " + plaka +"plakalı araç filoya geri döndü ve tekrar müsait.");
    				}else {
    					System.out.println("Hata ! Veritabanında "+ plaka +"plakalı bir araç bulunamadı");
    				}
    				
    			}catch (SQLException e) {
					System.out.println("İade işlemi sırasında hata oluştu:" + e.getMessage());
				}
    
    
    }
    
    
    //Aracı filodan ve veritabanından tamamen silen metod
    public static void araciFilodanSil(String plaka) {
    	//SQL Komutu:Plakası verilen aracı tablodan tamamen yok et!
    	String sql="DELETE FROM Araclar WHERE plaka=?";
    	
    	try(Connection conn =DatabaseConnection.getConnection();
    			PreparedStatement pstmt = conn.prepareStatement(sql)){
    		
    		pstmt.setString(1, plaka);
    		
    		//Komutu çalıştır ve kaş satırın silindiğini öğren
    		
    		int etkilenenSatır = pstmt.executeUpdate();
    		
    		if(etkilenenSatır > 0) {
    			System.out.println("Başarılı! " + plaka + "plakalı araç filodan tamamen silindi.");
    		}else {
				System.out.println("Hata! Veritabanında " + plaka + "plakalı bir araç bulunamadı.");
			}
    	}catch (SQLException e) {
			System.out.println("Silme işlemi sırasında hata oluştu!" +e.getMessage());
    }
    }
    	
    
    
    
    
    
 // 2. Programın çalışma noktası olan main metodu
public static void main(String[] args) {
        
       /* System.out.println("🔧 Yeni nesneler üretiliyor ve veritabanına gönderiliyor...\n");

        // 1. Yeni bir Otomobil nesnesi üretiyoruz
        // (Sıralama: Plaka, Marka, Model, Fiyat, MüsaitMi, Kapı Sayısı)
        Otomobil yeniOtomobil = new Otomobil("34OOP01", "Ford", "Focus", 1300.00, 5);
        
        // 2. Yeni bir SUV nesnesi üretiyoruz
        // (Sıralama: Plaka, Marka, Model, Fiyat, MüsaitMi, Dört Çeker Mi)
        Suv yeniSuv = new Suv("06OOP99", "Jeep", "Cherokee", 3500.00, true);
        
        // 3. Bu nesneleri veritabanına kaydetmesi için akıllı metodumuza gönderiyoruz
        araciVeritabaninaEkle(yeniOtomobil);
        araciVeritabaninaEkle(yeniSuv);
        
        System.out.println("\n-------------------------------------------");
        
        // 4. Son olarak listenin güncel halini SQL'den çekip ekrana yazdırıyoruz
        veritabanindanAraclariListele();
        */
	
	
	/*
	System.out.println("--- KİRALAMA ÖNCESİ DURUM ---");
    veritabanindanAraclariListele();
    
    System.out.println("\nMüşteri geldi ve 34OOP01 plakalı aracı kiralamak istiyor...");
    // Kiralama metodunu çağırıyoruz
    araciKirala("34OOP01");
    
    System.out.println("\n--- KİRALAMA SONRASI DURUM ---");
    // Focus artık "MüsaitMi = 0" olduğu için bu listede çıkmayacak!
    veritabanindanAraclariListele();
    }
   */
	
	
	/*
	System.out.println("1️) Müşteri geldi, aracı kiralıyor...");
    araciKirala("34OOP01"); 
    veritabanindanAraclariListele(); // Focus listede YOK
    
    System.out.println("\n 2) Aradan 3 gün geçti, müşteri aracı geri getirdi...");
    araciIadeEt("34OOP01"); 
    
    System.out.println("\n3️) İADE SONRASI GÜNCEL LİSTE:");
    veritabanindanAraclariListele(); // Focus listeye GERİ DÖNDÜ!
    */
	
	
	System.out.println("--- SİLME İŞLEMİ ÖNCESİ ---");
    veritabanindanAraclariListele(); // Focus listede var
    
    System.out.println("\n🚨 34OOP01 plakalı Ford Focus satıldı, filodan çıkarılıyor...");
    araciFilodanSil("34OOP01"); 
    
    System.out.println("\n--- SİLME İŞLEMİ SONRASI ---");
    veritabanindanAraclariListele(); // Focus artık yok!
}
}













/*
 //VERİTABANINI KULLANMADAN ÖNCE
 
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
*/