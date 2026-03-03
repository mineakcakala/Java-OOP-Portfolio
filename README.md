# Java OOP Portfolio
Bu repository, Java ile Nesne Yönelimli Programlama (OOP) yeteneklerimi geliştirmek için yazdığım projeleri barındırır.

## Projeler
1.# 🚗 Rent a Car System (Java OOP & SQL Server)

Bu proje, Nesne Yönelimli Programlama (OOP) prensipleri kullanılarak geliştirilmiş ve veri kalıcılığı için **Microsoft SQL Server** ile entegre edilmiş bir Araç Kiralama Sistemidir. 

Proje içerisinde kalıtım (inheritance) ve çok biçimlilik (polymorphism) gibi OOP kavramları aktif olarak kullanılmış olup, veritabanı iletişimi `PreparedStatement` yapısı ile güvenli (SQL Injection korumalı) bir şekilde sağlanmıştır.

## 🛠️ Kullanılan Teknolojiler
* **Dil:** Java (JDK)
* **Veritabanı:** Microsoft SQL Server
* **Bağlantı Mimarisi:** JDBC (Java Database Connectivity)
* **Kavramlar:** Object-Oriented Programming (OOP), CRUD Operasyonları

## 📊 Veritabanı Operasyonları (CRUD)

Proje, SQL Server üzerinde eksiksiz bir CRUD (Create, Read, Update, Delete) yaşam döngüsü sunmaktadır:

* **CREATE (Araç Ekleme):** Dinamik yapı sayesinde parametre olarak gönderilen `Otomobil` veya `Suv` nesneleri, `instanceof` ile tiplerine göre ayrıştırılarak SQL Server `Araclar` tablosuna güvenle eklenir (`INSERT INTO`). Araç tipine göre sadece ilgili kolonlar doldurulur.
* **READ (Araç Listeleme):** Veritabanında `MusaitMi = 1` olan araçlar filtrelenerek konsola listelenir (`SELECT * FROM`). Geçici listeler yerine veriler her defasında doğrudan veritabanından güncel olarak çekilir.
* **UPDATE (Kiralama ve İade İşlemleri):** * **Kiralama:** Müşteri bir araç kiraladığında aracın veritabanındaki durumu güncellenerek `MusaitMi = 0` (False) olarak işaretlenir ve garajdan çıkışı yapılır (`UPDATE ... SET MusaitMi = 0`).
  * **İade:** Araç geri getirildiğinde veritabanında durumu tekrar güncellenerek `MusaitMi = 1` (True) yapılır ve araç tekrar listelere düşer.
* **DELETE (Filodan Çıkarma):** Satılan veya hurdaya ayrılan araçlar, plakası üzerinden sorgulanarak veritabanından kalıcı olarak silinir (`DELETE FROM`).

## ⚙️ Kurulum ve Çalıştırma
1. Projeyi bilgisayarınıza klonlayın.
2. SQL Server üzerinde `RentACarDB` adında bir veritabanı oluşturun ve içerisine `Araclar` tablosunu ekleyin.
3. SQL Server Configuration Manager üzerinden TCP/IP portunu (1433) aktif hale getirin.
4. `DatabaseConnection.java` sınıfı içerisindeki `URL`, `USER` ve `PASSWORD` alanlarını kendi SQL Server yapılandırmanıza göre güncelleyin.
5. `Main.java` sınıfını çalıştırarak sistemi test edebilirsiniz.


2. **Ödeme Sistemi (Payment System):** Abstract Class, Interface ve Inheritance kullanılarak geliştirilmiş çok biçimli ödeme ve maaş hesaplama sistemi.