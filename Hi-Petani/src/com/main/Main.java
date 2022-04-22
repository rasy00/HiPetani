package com.main;

import java.util.ArrayList;
import java.util.Iterator;

import com.collections.Question;
import com.config.Getter;
import com.config.Setter;
import com.mysql.cj.x.protobuf.MysqlxExpr.Identifier;

public class Main {
	public static void main(String[] args) {
		// SETTER CLASS METHOD = RETURN -1 ? KESALAHAN SQL SYNTAX : RETURN -2 ? TABEL DATABASE TIDAK DIKETAHUI  : RETURN = 1 ? BERHASIL :
//										  RETURN -3 ? COLUMN TABEL TIDAK DIKETAHUI : RETURN 2 ? DATA SUDAH ADA : RETURN 0 ? EROR BELUM DIKETAHUI;\
		
		
//											   "Username","Password"   ,"Nama Lengkap","No Hp"
//		int notifRegistrasi = Setter.sendRegistrasi("Rasy_01","Rasyad Amhar","Rasyad we","08123395622");
		
//												  "judu","body" ,"kodeUser","kodeKategori"
//		int notifBertanya = Setter.sendQuestion("Bagaimana cara memasak","semafkjbasdfbasjkfdbajsbfjbaskjdfsdgsdgsdfgsdgsdgsdgsdgsfsdfaasdfasfasfasf",43,11);
								 // idPertanyaan
//		int notifLike = Setter.sendLike(66);
//		  												("kategori",idAdmin)
//		int notifKategori = Setter.sendKategori("alpukat",1);	
		
//		String a[][] = Getter.getAllQuestion();
		
	
		
		
		
//		System.out.println(notifLike);
//		System.out.println(notifKategori);
//		System.out.println(notifRegistrasi);
		
//		ArrayList<Question> pertanyaan = new ArrayList<Question>();
//		ArrayList<Question> pertanyaanFix = new ArrayList<Question>();
//		
//		Question a = new Question(0, "ini body[1]", "ini judul", "ini Nama", "Ini foto", 43);
//		a.addKategori("Rasyad");
//		a.addKategori("Amhar");
//		pertanyaan.add(a);
//		
//	
//		Question b = new Question(0, "ini body[2]", "ini judul", "ini Nama", "Ini foto", 43);
//		b.addKategori("Macan");
//		pertanyaanFix.add(b);
//		
//		
//		for(int i = 0;i<pertanyaan.size();i++) {
//			for(int j = 0;j<pertanyaanFix.size();j++) {
//				if (pertanyaanFix.get(j).equalsElement(pertanyaan.get(i))){
//					Question result = pertanyaanFix.get(j);
//					result.addKategori(pertanyaan.get(i).kategori[0]);
//					pertanyaanFix.set(j, result);
//					System.out.println("iya");
//					break;
//				}else {
//					pertanyaanFix.add(pertanyaan.get(i));
//					System.out.println("tidak");
//					break;
//				}
//				
//			}
//		}
//		
//		
//		
//		
//		
//		for (Question question : pertanyaanFix) {
//			System.out.println("==========");
//			System.out.println(question);
//			for (String c : question.kategori) {
//				System.out.println(c);
//			}
//		}
		
		ArrayList<Question> a = Getter.getAllQuestion();
		
		for (int i = 0;i<a.size();i++) {
			System.out.println("==========");
			System.out.println(a.get(i));
			for (String c : a.get(i).kategori) {
				System.out.println(" "+a.get(i).idPertanyaan+" "+c);
			}
		}
		
		System.out.println();
		
		
		
		
	}
}
