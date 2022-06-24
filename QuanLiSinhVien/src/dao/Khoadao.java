package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import bean.Khoa;

public class Khoadao {

	public ArrayList<Khoa> getKhoa() throws Exception {
		ArrayList<Khoa> dsk = new ArrayList<Khoa>();

		// b1: Tao cau lenh sql
		String sql = "select * from Khoa";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		// b3. Thuc hien cau lenh sql
		ResultSet rs = cmd.executeQuery();
		// b4: duyet qua rs
		while (rs.next()) {

			String MaKhoa = rs.getString("MaKhoa");
			String TenKhoa = rs.getString("TenKhoa");
			Khoa kh = new Khoa(MaKhoa, TenKhoa);
			dsk.add(kh);// Luu vao mang: ds
		}
		rs.close();

		return dsk;
	}

	public int Them(String MaKhoa, String TenKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "insert into Khoa(MaKhoa,TenKhoa) values(?,?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, MaKhoa);
		cmd.setString(2, TenKhoa);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}

	public int Xoa(String MaKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql1 = "delete from Khoa where MaKhoa=?";
		String sql2 = "delete from Lop where MaKhoa=?";
		String sql3 = "delete from SinhVien from Lop, Khoa where (Khoa.MaKhoa=Lop.MaKhoa and Lop.MaLop=SinhVien.MaLop and Khoa.MaKhoa = ?)";
		String sql4 = "delete from Diem from SinhVien, Lop, Khoa where (Khoa.MaKhoa=Lop.MaKhoa and Lop.MaLop=SinhVien.MaLop and SinhVien.MaSV=Diem.MaSV and Khoa.MaKhoa = ?)";
		String sql5 = "delete from GiaoVien where MaKhoa=?";
		String sql6 = "delete from HocPhan where MaKhoa=?";
		String sql7 = "delete from LopHocPhan from HocPhan, Khoa where (Khoa.MaKhoa=HocPhan.MaKhoa  and HocPhan.MaHocPhan=LopHocPhan.MaHocPhan and Khoa.MaKhoa = ?)";
		String sql8 = "delete from Diem from LopHocPhan, HocPhan, Khoa where (Khoa.MaKhoa=HocPhan.MaKhoa  and HocPhan.MaHocPhan=LopHocPhan.MaHocPhan and LopHocPhan.MaLopHP=Diem.MaLopHP and Khoa.MaKhoa = ?)";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd1 = Coso.cn.prepareStatement(sql1);
		PreparedStatement cmd2 = Coso.cn.prepareStatement(sql2);
		PreparedStatement cmd3 = Coso.cn.prepareStatement(sql3);
		PreparedStatement cmd4 = Coso.cn.prepareStatement(sql4);
		PreparedStatement cmd5 = Coso.cn.prepareStatement(sql5);
		PreparedStatement cmd6 = Coso.cn.prepareStatement(sql6);
		PreparedStatement cmd7 = Coso.cn.prepareStatement(sql7);
		PreparedStatement cmd8 = Coso.cn.prepareStatement(sql8);
		cmd1.setString(1, MaKhoa);
		cmd2.setString(1, MaKhoa);
		cmd3.setString(1, MaKhoa);
		cmd4.setString(1, MaKhoa);
		cmd5.setString(1, MaKhoa);
		cmd6.setString(1, MaKhoa);
		cmd7.setString(1, MaKhoa);
		cmd8.setString(1, MaKhoa);
		// b3. Thuc hien cau lenh sql
		cmd4.executeUpdate();
		cmd8.executeUpdate();
		cmd7.executeUpdate();
		cmd3.executeUpdate();
		cmd6.executeUpdate();
		cmd5.executeUpdate();
		cmd2.executeUpdate();
		return cmd1.executeUpdate();
	}

	public int Sua(String MaKhoa, String TenKhoa) throws Exception {
		// b1: Tao cau lenh sql
		String sql = "update Khoa set TenKhoa=? where MaKhoa=?";
		// b2: Tao ra prepareStatement
		PreparedStatement cmd = Coso.cn.prepareStatement(sql);
		cmd.setString(1, TenKhoa);
		cmd.setString(2, MaKhoa);
		// b3. Thuc hien cau lenh sql
		return cmd.executeUpdate();
	}
}
