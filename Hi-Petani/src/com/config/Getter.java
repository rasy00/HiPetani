package com.config;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.collections.Question;
import com.collections.User;

public class Getter {
	private static Connection conn = null;
	private static ResultSet resultData = null;
	
	
	// test, jika koneksi database tidak berhasil maka langsung exit (Aplikasi berhenti).
	static {
		
		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			if(!conn.isClosed()){
				System.out.println("Koneksi Database Berhasil : Getter.class");
			}
			
			conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
				System.exit(0);
			}
		}
	
	// method untuk mengambil data semua member
	public static ArrayList<User> getAllMember(){
		// buat instance untuk penyimpanan sementara
		ArrayList<User> member = new ArrayList<User>();
	
		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			// eksekusi query return nya disimpan ke resultData
			resultData = conn.createStatement().executeQuery("SELECT * FROM `member`");
			while (resultData.next()) {
				User data = new User();
				data.username = resultData.getString("username");
				data.namaLengkap = resultData.getString("nama");
				data.noHP = resultData.getString("noHp") ;
				data.setId(Integer.parseInt(resultData.getString("id")));
				member.add(data);
			}
			// tutup conn
			conn.close();
			
			// kirim nilai
			return member;
		}catch(Exception e) {
			e.printStackTrace();	
		}	
		return null;
	}
	
	
	// method untuk mengambil data semua pertanyaan
	public static ArrayList<Question> getAllQuestion(){
				
		// buat instance untuk penyimpanan sementara
		ArrayList<Question> pertanyaan = new ArrayList<Question>();
		ArrayList<Question> pertanyaanFix = new ArrayList<Question>();
	
	
 				
		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			// eksekusi query return nya disimpan ke resultData
			resultData = conn.createStatement().executeQuery("SELECT `pertanyaan`.`judul` AS `judul`, `pertanyaan`.`body` AS `body`, `pertanyaan`.`like` AS `like`,"+
																	"`pertanyaan`.`id` AS `id`,"+
																	"`member`.`username` AS `namaAuthor`, `member`.`foto` AS `foto` , `kategori`.`name` AS `kategori`"+
																	"FROM `pertanyaan` JOIN `member` ON `pertanyaan`.`author` = `member`.`id` "+
																	"JOIN `kontrakKategori` ON `kontrakKategori`.`pertanyaan` = `pertanyaan`.`id` "+
			 /*pemindahan data*/									"JOIN `kategori` ON `kategori`.`id` = `kontrakKategori`.`kategori`");
			int index = 0;
			while (resultData.next()){
				String judul =resultData.getString("judul");
				String body = resultData.getString("body");
				String nama = resultData.getString("namaAuthor");
				String foto = resultData.getString("foto");
				int like = Integer.parseInt(resultData.getString("like")) ;	
				String kategori = resultData.getString("kategori");	
				int id = Integer.parseInt(resultData.getString("id"));
				
				Question data = new Question(like, body, judul, nama, foto, id);
				data.addKategori(kategori);
				
				// menghapus redudance dalam menampilkan data
				if(pertanyaan.size() == 0){
					pertanyaan.add(data);
				}else{
					if(pertanyaan.get(index).id != data.id){
						pertanyaan.add(data);
						index++;
					}else if(pertanyaan.get(index) == data.id){
						pertanyaan.get(index).addKategori(data.kategori[0]);
					}
				}
			}
			
			// tutup conn
			conn.close();
				
			// kirim nilai
			return pertanyaan;
		}catch(Exception e) {
			e.printStackTrace();	
		}
				
		return null;
	}
	
	
//	method untuk filterin pertanyaan berdasarkan kategori
//	public static String[][] searchKategori(String kategori){
//		// query untuk pecarian
//		String query = "SELECT `pertanyaan`.`judul` AS `judul`, `pertanyaan`.`body` AS `body`, `pertanyaan`.`like` AS `like`,`pertanyaan`.`id` AS `id`,\n"
//			    + "		`member`.`username` AS `namaAuthor`, `member`.`foto` AS `foto` , `kategori`.`name` AS `kategori`\n"
//			    + "							FROM `kategori`  JOIN `kontrakKategori` ON `kategori`.`id` = `kontrakKategori`.`kategori` \n"
//			    + "							JOIN `pertanyaan` ON `kontrakKategori`.`pertanyaan` = `pertanyaan`.`id` \n"
//			    + "						  	JOIN  `member` ON `pertanyaan`.`author` = `member`.`id` WHERE `kategori`.`name` = \'cemara\'";
//		
//		String [][] result = new String [0][0];
//		
//		int count = 0;
////		hitung ada berapa data yang ditemukan
//		for (String[] data : datas) {
//			if(data[5].toLowerCase().equals(kategori.toLowerCase())) {
//				count++;
//			}
//		}
//		
////		membuat array dengan panjang data yang ditemukan
//		result = new String [count][datas[0].length];
//		int index = 0;
//		for (String[] data : datas) {
//			if(data[5].toLowerCase().equals(kategori.toLowerCase())) {
//				result[index][0] = data[0];
//				result[index][1] = data[1];
//				result[index][2] = data[2];
//				result[index][3] = data[3];
//				result[index][4] = data[4];
//				result[index][5] = data[5];
//				result[index][6] = data[6];
//				index++;
//			}
//		}
//		return result;
//	}
	
	
	// method untuk mengambil data semua Kategori
		public static String[][] getAllKategori(){
				
			// buat instance untuk penyimpanan sementara
			ArrayList<String> id = new ArrayList<>();
			ArrayList<String> nama = new ArrayList<>();
			
				
			try {
				// jalankan koneksi ke database
				conn = Config.connection();
				// eksekusi query return nya disimpan ke resultData
				resultData = conn.createStatement().executeQuery("SELECT * FROM `kategori`");
				while (resultData.next()) {
					id.add(resultData.getString("id"));
					nama.add(resultData.getString("name"));
				}
					
				// membuat array dan menginisiasi untuk return value
				String [][] result = new String[id.size()][3];
				for(int a = 0; a < id.size();a++) {
					result[a][0] = id.get(a);
					result[a][1] = nama.get(a);
				}
						
				// tutup conn
				conn.close();
					
				// kirim nilai
				return result;
			}catch(Exception e) {
				e.printStackTrace();	
			}
					
			return null;
		}

}
