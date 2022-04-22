package com.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Setter {
	private static Connection conn;
	private static ResultSet resultData = null;
	private static int a = 0;
	
	// test, jika koneksi database tidak berhasil maka langsung exit (Aplikasi berhenti).
	static {
		a = 0;
		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			if(!conn.isClosed()){
				System.out.println("Koneksi Database Berhasil : Setter.class");
			}
			
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			a = 505;
			System.out.println(a);
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	
//	method menambah member
	public static int sendRegistrasi(String username,String password,String nama,String noHp) {
		a = 0;
//		checking sudah ada?
		String [][] daftarUser = NULL // getAllMember();
		for (String[] user : daftarUser) {
			if(user[1].toLowerCase().equals(username.toLowerCase())) {
				a = 2;
				return a; 
			}
		}
		
		try {	
		// jalankan koneksi ke database
		conn = Config.connection();
												// update data (INSERT)
		 a = conn.createStatement().executeUpdate("INSERT INTO `member` (`id`, `username`, `password`, `nama`, `noHp`, `foto`) VALUES (NULL, '"+username+"', '"+password+"', '"+nama+"', '"+noHp+"', 'sadf')");
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.toLowerCase().equals("you".toLowerCase())? -1 :
				subMsg.toLowerCase().equals("tab".toLowerCase())? -2 : 
				subMsg.toLowerCase().equals("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}
	
	
	
	
	
//	Method menambah pertanyaan
	public static int sendQuestion(String judul,String body,int kodeUser,int kodeKategori) {
		a = 0;
		try {					   
		 // jalankan koneksi ke database
		 conn = Config.connection();
		 
		 // update data (INSERT)
		 a = conn.createStatement().executeUpdate("INSERT INTO `pertanyaan` (`id`, `author`, `judul`, `body`, `like`) VALUES (NULL, '"+kodeUser+"', '"+judul+"', '"+body+"', '8')");
		 kontrakKategori(kodeKategori);
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.toLowerCase().equals("you".toLowerCase())? -1 :
				subMsg.toLowerCase().equals("tab".toLowerCase())? -2 : 
				subMsg.toLowerCase().equals("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
			e.printStackTrace();
		}
		return a;
	}
	
	
//	Method menambah kategori
	public static int sendKategori(String kategori,int idAdmin) {
		 a = 0;
//		checking sudah ada?
		String [][] daftarKategori = Getter.getAllKategori();
		for (String[] kategories : daftarKategori) {
			if(kategories[1].toLowerCase().equals(kategori.toLowerCase())) {
				a = 2;
				return a; 
			}
		}
		try {					   
		 // jalankan koneksi ke database
		 conn = Config.connection();
		 //update data (INSERT)
		 a = conn.createStatement().executeUpdate("INSERT INTO `kategori` (`id`, `name`, `admin`) VALUES (NULL, '"+kategori.toLowerCase()+"', '"+idAdmin+"')");
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.toLowerCase().equals("you".toLowerCase())? -1 :
				subMsg.toLowerCase().equals("tab".toLowerCase())? -2 : 
				subMsg.toLowerCase().equals("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}
	
	public static int sendLike(int idP) {
//		UPDATE `pertanyaan` SET `like` = '90' WHERE `pertanyaan`.`id` = 55
		 a = 0;
		 int like = 0;
//		checking sudah ada?
		String [][] questions = Getter.getAllQuestion();
		for (String[] question : questions) {
			if(question[6].toLowerCase().equals(String.valueOf(idP))) {
				like = Integer.parseInt(question[3]);
				break;
			}
		}
		like++;
		
		try {					   
		 // jalankan koneksi ke database
	 	 conn = Config.connection();
	 	 //update data (INSERT)
		 a = conn.createStatement().executeUpdate("UPDATE `pertanyaan` SET `like` = '"+like+"' WHERE `pertanyaan`.`id` = "+idP);
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.toLowerCase().equals("you".toLowerCase())? -1 :
				subMsg.toLowerCase().equals("tab".toLowerCase())? -2 : 
				subMsg.toLowerCase().equals("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}

		return a;
	}
	
	
	
	// 	=========================================== Helper =========================================
		private static int getLastIdPertanyaan() throws SQLException {
			resultData = conn.createStatement().executeQuery("SELECT `pertanyaan`.`id` FROM `pertanyaan`");
			ArrayList<Integer> id = new ArrayList<>(); 
			
			while (resultData.next()) {
				id.add(Integer.valueOf(resultData.getString("id")));
			}
			
			int result = 3;
			int index = 0;
			
			while (index < id.size()) {
				result = id.get(index);
				index++;
			}
			
			System.out.println(result);
			return result;
		}
		
		//method untuk menambahkan kontrak kategori (Antara pertanyaan dengan kategori)
		private static void kontrakKategori(int kodeKategori) throws SQLException {
			int id = getLastIdPertanyaan();

			conn.createStatement().executeUpdate("INSERT INTO `kontrakKategori` (`id`, `pertanyaan`, `kategori`) VALUES (NULL, '"+id+"', '"+kodeKategori+"')");
			
		}      
}
