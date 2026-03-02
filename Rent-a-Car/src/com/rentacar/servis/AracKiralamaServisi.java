package com.rentacar.servis;

import java.util.ArrayList;
import java.util.Iterator;
import com.rentacar.model.Arac;
import com.rentacar.model.Musteri;
import com.rentacar.exception.AracBulunamadıException; // 'ı' yerine 'i' yapıldı (Standartlara uygun)
import com.rentacar.exception.AracMusaitDegilException;

// Interface'i implemente ederek kurumsal bir yapı kuruyoruz
public class AracKiralamaServisi implements IAracService {

    // Polymorphism: Listemiz 'Arac' tipinde, yani hem SUV hem Otomobil alabilir.
    private ArrayList<Arac> aracListesi;

    public AracKiralamaServisi() {
        this.aracListesi = new ArrayList<>();
    }

    @Override
    public void aracEkle(Arac arac) {
        aracListesi.add(arac);
        System.out.println(arac.getPlaka() + " plakalı araç sisteme eklendi.");
    }

    @Override
    public void aracSil(String plaka) throws AracBulunamadıException {
        boolean silindiMi = false;
        
        // Listeyi gezerken eleman silmek için Iterator kullanmak en güvenlisidir
        Iterator<Arac> iterator = aracListesi.iterator();
        while (iterator.hasNext()) {
            Arac arac = iterator.next();
            if (arac.getPlaka().equals(plaka)) {
                iterator.remove();
                silindiMi = true;
                System.out.println(plaka + " plakalı araç silindi.");
                break;
            }
        }

        if (!silindiMi) {
            throw new AracBulunamadıException("Silinecek araç bulunamadı: " + plaka);
        }
    }

    @Override
    public void musaitAraclariListele() {
        System.out.println("\n--- MÜSAİT ARAÇLAR LİSTESİ ---");
        boolean aracVarMi = false;
        
        for (Arac arac : aracListesi) {
            // DÜZELTME: İsmusaitMi() yerine isMusaitMi() yazıldı
            if (arac.isMusaitMi()) {
                System.out.println(arac.toString());
                aracVarMi = true;
            }
        }
        
        if (!aracVarMi) {
            System.out.println("Şu an kiralanabilir araç yok.");
        }
    }

    @Override
    public void aracKirala(String plaka, int gunSayisi, Musteri musteri) 
            throws AracMusaitDegilException, AracBulunamadıException {
        
        Arac bulunacakArac = null;

        // ADIM 1: Aracı Bul
        for (Arac arac : aracListesi) {
            if (arac.getPlaka().equals(plaka)) {
                bulunacakArac = arac;
                break;
            }
        }

        // Araç yoksa hata fırlat
        if (bulunacakArac == null) {
            throw new AracBulunamadıException("Kiralanmak istenen araç sistemde yok: " + plaka);
        }

        // ADIM 2: Müsaitlik Kontrolü
        // DÜZELTME: İsmusaitMi() yerine isMusaitMi() yazıldı
        if (!bulunacakArac.isMusaitMi()) {
            throw new AracMusaitDegilException("Bu araç şu an başkasında: " + plaka);
        }

        // ADIM 3: Fiyat Hesapla ve Durumu Güncelle
        double fiyat = bulunacakArac.fiyatHesapla(gunSayisi);
        // DÜZELTME: setKiralandiMi() yerine setMusaitMi() yazıldı
        bulunacakArac.setMusaitMi(false); // Artık müsait değil

        System.out.println("\n*** KİRALAMA BAŞARILI ***");
        System.out.println("Müşteri: " + musteri.getIsim());
        System.out.println("Araç: " + bulunacakArac.getMarka() + " " + bulunacakArac.getModel());
        System.out.println("Toplam Tutar: " + fiyat + " TL");
    }

    @Override
    public void aracTeslimAl(String plaka) throws AracBulunamadıException {
        Arac teslimAlinacakArac = null;

        for (Arac arac : aracListesi) {
            if (arac.getPlaka().equals(plaka)) {
                teslimAlinacakArac = arac;
                break;
            }
        }

        if (teslimAlinacakArac == null) {
            throw new AracBulunamadıException("Teslim alınacak araç bulunamadı: " + plaka);
        }

        // Aracı tekrar müsait yapıyoruz
        // DÜZELTME: setKiralandiMi() yerine setMusaitMi() yazıldı
        teslimAlinacakArac.setMusaitMi(true);
        System.out.println("\nAraç teslim alındı ve tekrar kiralanabilir: " + plaka);
    }
}