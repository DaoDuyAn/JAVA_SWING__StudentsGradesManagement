package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import bean.LopHocPhan;

public class LopHocPhandao {
	public ArrayList<LopHocPhan> getLopHocPhan() throws Exception {
		ArrayList<LopHocPhan> ds = new ArrayList<LopHocPhan>();

		// b1: Tao cau lenh sql
		String sql = "select * from LopHocPhan";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		// b3. Thuc hien cau lenh sql
		ResultSet rs = cmd.executeQuery();
		// b4: duyet qua rs
		while (rs.next()) {
			String MaLopHocPhan = rs.getString("MaLopHP");
			String TenLopHocPhan = rs.getString("TenLopHP");
			int SoTinChi = rs.getInt("SoTinChi");
			Date NgayBatDau = rs.getDate("NgayBatDau");
			String MaHocPhan = rs.getString("MaHocPhan");
			String MaGiaoVien = rs.getString("MaGiaoVien");
			LopHocPhan lhp = new LopHocPhan(MaLopHocPhan, TenLopHocPhan, MaHocPhan, MaGiaoVien, SoTinChi, NgayBatDau);
			ds.add(lhp);// Luu vao mang: ds
		}
		rs.close();

		return ds;
	}

	public int Them(String MaLopHocPhan, String TenLopHocPhan, String MaHocPhan, String MaGiaoVien, int SoTinChi,
			Date NgayBatDau) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "insert into LopHocPhan(MaLopHP,TenLopHP,SoTinChi,NgayBatDau,MaHocPhan,MaGiaoVien) values(?,?,?,?,?,?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, MaLopHocPhan);
		cmd.setString(2, TenLopHocPhan);
		cmd.setInt(3, SoTinChi);
		cmd.setDate(4, new java.sql.Date(NgayBatDau.getTime()));
		cmd.setString(5, MaHocPhan);
		cmd.setString(6, MaGiaoVien);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Sua(String MaLopHocPhan, String TenLopHocPhan, String MaHocPhan, String MaGiaoVien, int SoTinChi,
			Date NgayBatDau) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update LopHocPhan set TenLopHP=?, SoTinChi=?, NgayBatDau=?, MaHocPhan=?, MaGiaoVien=? where MaLopHP=?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);

		cmd.setString(1, TenLopHocPhan);
		cmd.setInt(2, SoTinChi);
		cmd.setDate(3, new java.sql.Date(NgayBatDau.getTime()));
		cmd.setString(4, MaHocPhan);
		cmd.setString(5, MaGiaoVien);
		cmd.setString(6, MaLopHocPhan);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Xoa(String MaLopHocPhan) throws Exception {
		// b1: Tao cau lenh sql
		String sql1 = "delete from LopHocPhan where MaLopHP=?";
		String sql2 = "delete from Diem where MaLopHP=?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd1 = Coso.cn.prepareStatement(sql1);
		PreparedStatement cmd2 = Coso.cn.prepareStatement(sql2);
		cmd1.setString(1, MaLopHocPhan);
		cmd2.setString(1, MaLopHocPhan);
		// b3. Thuc hien cau lenh sql
		cmd2.executeUpdate();
		return cmd1.executeUpdate();
	}
}
