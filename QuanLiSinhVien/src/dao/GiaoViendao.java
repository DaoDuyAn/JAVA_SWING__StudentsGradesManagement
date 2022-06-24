package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import bean.GiaoVien;

public class GiaoViendao {
	public ArrayList<GiaoVien> getGiaoVien() throws Exception {
		ArrayList<GiaoVien> ds = new ArrayList<GiaoVien>();

		// b1: Tao cau lenh sql
		String sql = "select * from GiaoVien";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		// b3. Thuc hien cau lenh sql
		ResultSet rs = cmd.executeQuery();
		// b4: duyet qua rs
		while (rs.next()) {
			String MaGiaoVien = rs.getString("MaGiaoVien");
			String HoTen = rs.getString("HoTen");
			String DiaChi = rs.getString("DiaChi");
			Boolean GioiTinh = rs.getBoolean("GioiTinh");
			Date NgaySinh = rs.getDate("NgaySinh");
			String QueQuan = rs.getString("QueQuan");
			String DiDong = rs.getString("DiDong");
			String Email = rs.getString("Email");
			String MaKhoa = rs.getString("MaKhoa");

			GiaoVien lhp = new GiaoVien(MaGiaoVien, HoTen, DiaChi, QueQuan, DiDong, Email, MaKhoa, GioiTinh, NgaySinh);
			ds.add(lhp);// Luu vao mang: ds
		}
		rs.close();

		return ds;
	}

	public int Them(String MaGiaoVien, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QueQuan,
			String DiDong, String Email, String MaKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "insert into GiaoVien(MaGiaoVien,HoTen,DiaChi,GioiTinh,NgaySinh,QueQuan,DiDong,Email,MaKhoa) values(?,?,?,?,?,?,?,?,?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, MaGiaoVien);
		cmd.setString(2, HoTen);
		cmd.setString(3, DiaChi);
		cmd.setBoolean(4, GioiTinh);
		cmd.setDate(5, new java.sql.Date(NgaySinh.getTime()));
		cmd.setString(6, QueQuan);
		cmd.setString(7, DiDong);
		cmd.setString(8, Email);
		cmd.setString(9, MaKhoa);

		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Sua(String MaGiaoVien, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QueQuan,
			String DiDong, String Email, String MaKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update GiaoVien set HoTen=?, DiaChi=?, GioiTinh=?, NgaySinh=?, QueQuan=?, DiDong=?, Email=?, MaKhoa=? where MaGiaoVien=?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);

		cmd.setString(1, HoTen);
		cmd.setString(2, DiaChi);
		cmd.setBoolean(3, GioiTinh);
		cmd.setDate(4, new java.sql.Date(NgaySinh.getTime()));
		cmd.setString(5, QueQuan);
		cmd.setString(6, DiDong);
		cmd.setString(7, Email);
		cmd.setString(8, MaKhoa);
		cmd.setString(9, MaGiaoVien);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Xoa(String MaGiaoVien) throws Exception {
		// b1: Tao cau lenh sql
		String sql1 = "delete from GiaoVien where MaGiaoVien=?";
		String sql2 = "delete from LopHocPhan where MaGiaoVien=?";
		String sql3 = "delete from Diem from GiaoVien, LopHocPhan where (GiaoVien.MaGiaoVien=LopHocPhan.MaGiaoVien and LopHocPhan.MaLopHP=Diem.MaLopHP and GiaoVien.MaGiaoVien=?) ";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd1 = Coso.cn.prepareStatement(sql1);
		PreparedStatement cmd2 = Coso.cn.prepareStatement(sql2);
		PreparedStatement cmd3 = Coso.cn.prepareStatement(sql3);
		cmd1.setString(1, MaGiaoVien);
		cmd2.setString(1, MaGiaoVien);
		cmd3.setString(1, MaGiaoVien);
		// b3. Thuc hien cau lenh sql
		cmd3.executeUpdate();
		cmd2.executeUpdate();
		return cmd1.executeUpdate();
	}
}
