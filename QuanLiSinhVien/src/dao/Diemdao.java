package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Diem;

public class Diemdao {
	public ArrayList<Diem> getDiem() throws Exception {
		ArrayList<Diem> ds = new ArrayList<Diem>();

		// b1: Tao cau lenh sql
		String sql = "select * from Diem";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		// b3. Thuc hien cau lenh sql
		ResultSet rs = cmd.executeQuery();
		// b4: duyet qua rs
		while (rs.next()) {
			String MaLopHP = rs.getString("MaLopHP");
			String MaSV = rs.getString("MaSV");
			int DiemCC = rs.getInt("DiemCC");
			Double DiemKT = rs.getDouble("DiemKT");
			Double DiemThi = rs.getDouble("DiemThi");
			Double DiemKTHP = rs.getDouble("DiemKTHP");
			String DiemChu = rs.getString("DiemChu");
			Diem d = new Diem(MaLopHP, MaSV, DiemChu, DiemCC, DiemKT, DiemThi, DiemKTHP);
			ds.add(d);// Luu vao mang: ds
		}
		rs.close();

		return ds;
	}

	public int Them(String MaLopHP, String MaSV) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "insert into Diem(MaLopHP,MaSV,DiemCC,DiemKT,DiemThi,DiemKTHP,DiemChu) values(?,?,?,?,?,?,?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, MaLopHP);
		cmd.setString(2, MaSV);
		cmd.setInt(3, 0);
		cmd.setDouble(4, 0.0);
		cmd.setDouble(5, 0.0);
		cmd.setDouble(6, 0.0);
		cmd.setString(7, "Chua xep loai");
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Sua(String MaLopHP, String MaSV, int DiemCC, double DiemKT, double DiemThi) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update Diem set DiemCC=?, DiemKT=?, DiemThi=? where (MaLopHP=? and MaSV=?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);

		cmd.setInt(1, DiemCC);
		cmd.setDouble(2, DiemKT);
		cmd.setDouble(3, DiemThi);
		cmd.setString(5, MaSV);
		cmd.setString(4, MaLopHP);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Xoa(String MaLopHP, String MaSV) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "delete from Diem where (MaLopHP=? and MaSV=?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(2, MaSV);
		cmd.setString(1, MaLopHP);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public void chamDiem(String MaLopHP, String MaSV, double DiemKTHP, String DiemChu) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update Diem set DiemKTHP=?, DiemChu=? where (MaLopHP=? and MaSV=?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setDouble(1, DiemKTHP);
		cmd.setString(2, DiemChu);
		cmd.setString(3, MaLopHP);
		cmd.setString(4, MaSV);
		// b3. Thuc hien cau lenh sql
		cmd.executeUpdate();
	}
}
