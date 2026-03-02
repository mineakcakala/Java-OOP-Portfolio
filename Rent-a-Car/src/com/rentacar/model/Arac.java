package com.rentacar.model;

// Abstract olması, bu sınıftan doğrudan nesne üretilemeyeceği anlamına gelir.
public abstract class Arac {
    
    // Encapsulation: Değişkenler private
    private String plaka;
    private String marka;
    private String model;
    private double gunlukKiralamaBedeli; // Tüm projeyle uyumlu olması için düzeltildi
    private boolean musaitMi;

    // Constructor (Kurucu Metot)
    public Arac(String plaka, String marka, String model, double gunlukKiralamaBedeli) {
        this.plaka = plaka;
        this.marka = marka;
        this.model = model;
        this.gunlukKiralamaBedeli = gunlukKiralamaBedeli;
        this.musaitMi = true; // Varsayılan olarak araç galeride başlar
    }

    // Polymorphism (Çok biçimlilik) için gövdesiz metot
    // Otomobil ve Suv sınıfları bu kurala uymak ZORUNDA
    public abstract double fiyatHesapla(int gunSayisi);

    // Ortak bilgi yazdırma metodu
    public void bilgileriGoster() {
        System.out.println(marka + " " + model + " [" + plaka + "] - Günlük Fiyat: " + gunlukKiralamaBedeli + " TL");
    }

    // ==========================================
    // GETTER VE SETTER METOTLARI (Encapsulation)
    // ==========================================

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getGunlukKiralamaBedeli() {
        return gunlukKiralamaBedeli;
    }

    // İstenirse buraya fiyata eksi değer girilememesi için bir if şartı eklenebilir
    public void setGunlukKiralamaBedeli(double gunlukKiralamaBedeli) {
        this.gunlukKiralamaBedeli = gunlukKiralamaBedeli;
    }

    public boolean isMusaitMi() {
        return musaitMi;
    }

    public void setMusaitMi(boolean musaitMi) {
        this.musaitMi = musaitMi;
    }

    // Konsolda "Araçları Listele" dediğimizde bellekteki anlamsız adresler yerine 
    // aracın özelliklerini okunaklı yazdırmak için toString() metodunu eziyoruz.
    @Override
    public String toString() {
        String durum = musaitMi ? "Müsait" : "Kirada";
        return marka + " " + model + " [" + plaka + "] - Fiyat: " + gunlukKiralamaBedeli + " TL | Durum: " + durum;
    }
}