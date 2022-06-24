package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import bean.SinhVien;

public class SinhViendao {
	public ArrayList<SinhVien> getSinhVien() throws Exception {

		ArrayList<SinhVien> ds = new ArrayList<SinhVien>();
		// b1: Tao cau lenh sql
		String sql = "select * from SinhVien";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		// b3. Thuc hien cau lenh sql
		ResultSet rs = cmd.executeQuery();
		// b4: duyet qua rs
		while (rs.next()) {
			String MaSV = rs.getString("MaSV");
			String HoTen = rs.getString("HoTen");
			String DiaChi = rs.getString("DiaChi");
			Boolean GioiTinh = rs.getBoolean("GioiTinh");
			Date NgaySinh = rs.getDate("NgaySinh");
			String QuocTich = rs.getString("QuocTich");
			String QueQuan = rs.getString("QueQuan");
			String DanToc = rs.getString("DanToc");
			String TonGiao = rs.getString("TonGiao");
			String DiDong = rs.getString("DiDong");
			String Email = rs.getString("Email");
			String MaLop = rs.getString("MaLop");
			SinhVien sv = new SinhVien(MaSV, HoTen, DiaChi, QuocTich, QueQuan, DanToc, TonGiao, DiDong, Email, MaLop,
					GioiTinh, NgaySinh);
			ds.add(sv);// Luu vao mang: ds
		}
		rs.close();
		return ds;

	}

	public int Them(String MaSV, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QuocTich,
			String QueQuan, String DanToc, String TonGiao, String DiDong, String Email, String MaLop) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "INSERT SinhVien (MaSV, HoTen, DiaChi, GioiTinh, NgaySinh, QuocTich, QueQuan, DanToc, TonGiao, DiDong, Email, MaLop) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, MaSV);
		cmd.setString(2, HoTen);
		cmd.setString(3, DiaChi);
		cmd.setBoolean(4, GioiTinh);
		// Doi ngay util sang ngay sql
		cmd.setDate(5, new java.sql.Date(NgaySinh.getTime())); // getTime: doi ra bn giay.
		cmd.setString(6, QuocTich);
		cmd.setString(7, QueQuan);
		cmd.setString(8, DanToc);
		cmd.setString(9, TonGiao);
		cmd.setString(10, DiDong);
		cmd.setString(11, Email);
		cmd.setString(12, MaLop);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Xoa(String MaSV) throws Exception {
		// b1: Tao cau lenh sql
		String sql1 = "delete from SinhVien where MaSV=?";
		String sql2 = "delete from Diem where MaSV=?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd1 = Coso.cn.prepareStatement(sql1);
		PreparedStatement cmd2 = Coso.cn.prepareStatement(sql2);
		cmd1.setString(1, MaSV); // truyen tham so vo ?.
		cmd2.setString(1, MaSV);
		// b3. Thuc hien cau lenh sql
		cmd2.executeUpdate();
		return cmd1.executeUpdate();
	}

	public int Sua(String MaSV, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QuocTich,
			String QueQuan, String DanToc, String TonGiao, String DiDong, String Email, String MaLop) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update SinhVien set HoTen=?, DiaChi=?, GioiTinh=?, NgaySinh = ?, QuocTich=?, QueQuan=?, DanToc=?, TonGiao=?, DiDong=?, Email=?, MaLop =? where MaSV= ?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);

		cmd.setString(1, HoTen);
		cmd.setString(2, DiaChi);
		cmd.setBoolean(3, GioiTinh);
		// Doi ngay util sang ngay sql
		cmd.setDate(4, new java.sql.Date(NgaySinh.getTime()));
		cmd.setString(5, QuocTich);
		cmd.setString(6, QueQuan);
		cmd.setString(7, DanToc);
		cmd.setString(8, TonGiao);
		cmd.setString(9, DiDong);
		cmd.setString(10, Email);
		cmd.setString(11, MaLop);
		cmd.setString(12, MaSV);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

}
