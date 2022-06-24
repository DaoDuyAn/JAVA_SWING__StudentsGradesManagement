package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import bean.HocPhan;

public class HocPhandao {
	public ArrayList<HocPhan> getHocPhan() throws Exception {
		ArrayList<HocPhan> ds = new ArrayList<HocPhan>();

		// b1: Tao cau lenh sql
		String sql = "select * from HocPhan";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		// b3. Thuc hien cau lenh sql
		ResultSet rs = cmd.executeQuery();
		// b4: duyet qua rs
		while (rs.next()) {

			String MaHocPhan = rs.getString("MaHocPhan");
			String TenHocPhan = rs.getString("TenHocPhan");
			String MaKhoa = rs.getString("MaKhoa");
			HocPhan hp = new HocPhan(MaHocPhan, TenHocPhan, MaKhoa);
			ds.add(hp);// Luu vao mang: ds
		}
		rs.close();

		return ds;
	}
	public int Them(String MaHocPhan, String TenHocPhan, String MaKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "insert into HocPhan(MaHocPhan,TenHocPhan,MaKhoa) values(?,?,?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, MaHocPhan);
		cmd.setString(2, TenHocPhan);
		cmd.setString(3, MaKhoa);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Sua(String MaHocPhan, String TenHocPhan, String MaKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update HocPhan set TenHocPhan=?, MaKhoa=? where MaHocPhan=?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, TenHocPhan);
		cmd.setString(2, MaKhoa);
		cmd.setString(3, MaHocPhan);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Xoa(String MaHocPhan) throws Exception {
		// b1: Tao cau lenh sql
		String sql1 = "delete from HocPhan where MaHocPhan=?";
		String sql2 = "delete from LopHocPhan where MaHocPhan=?";
		String sql3 = "delete from Diem from HocPhan, LopHocPhan where (HocPhan.MaHocPhan=LopHocPhan.MaHocPhan and LopHocPhan.MaLopHP=Diem.MaLopHP and HocPhan.MaHocPhan=?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd1 = Coso.cn.prepareStatement(sql1);
		PreparedStatement cmd2 = Coso.cn.prepareStatement(sql2);
		PreparedStatement cmd3 = Coso.cn.prepareStatement(sql3);
		cmd1.setString(1, MaHocPhan);
		cmd2.setString(1, MaHocPhan);
		cmd3.setString(1, MaHocPhan);
		// b3. Thuc hien cau lenh sql
		cmd3.executeUpdate();
		cmd2.executeUpdate();
		return cmd1.executeUpdate();
	}
}
