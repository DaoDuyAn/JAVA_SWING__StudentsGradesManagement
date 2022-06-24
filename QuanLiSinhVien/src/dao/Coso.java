package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Coso {
	public static Connection cn;

	public void KetNoi() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Xac dinh HQTCSDL
			System.out.println("OK da nap Driver");
			String st = "jdbc:sqlserver://DESKTOP-32ITQVJ\\SQLEXPRESS:1433;databaseName=QuanLiSV;user=sa; password=andao882001";
			cn = DriverManager.getConnection(st);
			System.out.println("Da ket noi den csdl QuanLiSV");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Coso cs = new Coso();
		cs.KetNoi();
	}
}
