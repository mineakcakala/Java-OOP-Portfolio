package com.rentacar.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
//SQL Server bağlantı adresi(URL)
//Not: encrypt=true ve trustServerCertificate=true kısımları Microsoft'un yeni güvenlik standartları  için zorunludur.

	// Adresin içine ;instanceName=SQLLABKOD; kısmını ekledik!
    private static final String URL = "jdbc:sqlserver://localhost;instanceName=SQLLABKOD;databaseName=RentACarDB;encrypt=true;trustServerCertificate=true;";
    
    private static final String USER = "sa"; 
    private static final String PASSWORD = "RentACar123!"; // Bir önceki adımda belirlediğimiz şifre
    
    
	
	//Veritabanı bağlantısını veren metod (Bütün projede kiralama ve listeleme yaparken çağıracağız)
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	//Bağlantıyı test etmek için küçük bir main metodu 
	
	public static void main (String[] arg) {
		try {
			Connection conn = getConnection();
			System.out.println("Harika ! SQL Server Veritabanına Başarıyla Bağlanıldı!");
			
			//İşimiz bitince kapıyı kapatıyoruz
			conn.close();
			
		}catch (SQLException e) {
			System.out.println("Bağlantı Hatası Alındı:"+ e.getMessage());
		}
	}
	
	
	
}
