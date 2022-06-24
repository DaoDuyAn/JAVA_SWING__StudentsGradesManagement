package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import bean.Lop;

public class Lopdao {
	public ArrayList<Lop> getLop() throws Exception {
		ArrayList<Lop> ds = new ArrayList<Lop>();

		// b1: Tao cau lenh sql
		String sql = "select * from Lop";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		// b3. Thuc hien cau lenh sql
		ResultSet rs = cmd.executeQuery();
		// b4: duyet qua rs
		while (rs.next()) {

			String MaLop = rs.getString("MaLop");
			String TenLop = rs.getString("TenLop");
			String MaKhoa = rs.getString("MaKhoa");
			Lop lh = new Lop(MaLop, TenLop, MaKhoa);
			ds.add(lh);// Luu vao mang: ds
		}
		rs.close();

		return ds;
	}

	public int Them(String MaLop, String TenLop, String MaKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "insert into Lop(MaLop,TenLop,MaKhoa) values(?,?,?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, MaLop);
		cmd.setString(2, TenLop);
		cmd.setString(3, MaKhoa);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Sua(String MaLop, String TenLop, String MaKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update Lop set TenLop=?, MaKhoa=? where MaLop=?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, TenLop);
		cmd.setString(2, MaKhoa);
		cmd.setString(3, MaLop);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Xoa(String MaLop) throws Exception {
		// b1: Tao cau lenh sql
		String sql1 = "delete from Lop where MaLop=?";
		String sql2 = "delete from SinhVien where MaLop=?";
		String sql3 = "delete from Diem from SinhVien, Lop where (Lop.MaLop=SinhVien.MaLop and SinhVien.MaSV=Diem.MaSV and Lop.MaLop=?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd1 = Coso.cn.prepareStatement(sql1);
		PreparedStatement cmd2 = Coso.cn.prepareStatement(sql2);
		PreparedStatement cmd3 = Coso.cn.prepareStatement(sql3);
		cmd1.setString(1, MaLop);
		cmd2.setString(1, MaLop);
		cmd3.setString(1, MaLop);
		// b3. Thuc hien cau lenh sql
		cmd3.executeUpdate();
		cmd2.executeUpdate();
		return cmd1.executeUpdate();
	}
}
