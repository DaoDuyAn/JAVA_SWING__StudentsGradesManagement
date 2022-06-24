package bo;

import java.util.ArrayList;
import java.util.Date;

import bean.GiaoVien;
import dao.GiaoViendao;

public class GiaoVienbo {
	GiaoViendao gvdao = new GiaoViendao();
	ArrayList<GiaoVien> ds;

	public ArrayList<GiaoVien> getGiaoVien() throws Exception {
		ds = gvdao.getGiaoVien();
		return ds;
	}
	
	public ArrayList<GiaoVien> TimKhoa(String makhoa) throws Exception {
		// Tao ra mot mang dong: tam
		// Duyet qua ds. Neu tim thay, dua vao mang tam
		ArrayList<GiaoVien> tam = new ArrayList<GiaoVien>();
		for (GiaoVien gv : ds) {
			if (gv.getMaKhoa().equalsIgnoreCase(makhoa))
				tam.add(gv);
		}
		return tam;
	}
	public ArrayList<GiaoVien> TimKiem(String key) throws Exception {

		ArrayList<GiaoVien> temp = new ArrayList<GiaoVien>();
		for (GiaoVien gv : ds) {
			if (gv.getMaGiaoVien().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| gv.getHoTen().toUpperCase().trim().contains(key.toUpperCase().trim())) {
				temp.add(gv);
			}
		}
		
		return temp;
	}

	public int Them(String MaGiaoVien, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QueQuan,
			String DiDong, String Email, String MaKhoa) throws Exception {
		for (GiaoVien gv : ds)
			if (gv.getMaGiaoVien().equals(MaGiaoVien))
				return 0;
		// Them vao bo nho
		ds.add(new GiaoVien(MaGiaoVien, HoTen, DiaChi, QueQuan, DiDong, Email, MaKhoa, GioiTinh, NgaySinh));
		return gvdao.Them(MaGiaoVien, HoTen, DiaChi, GioiTinh, NgaySinh, QueQuan, DiDong, Email, MaKhoa);
	}

	public int Xoa(String MaGiaoVien) throws Exception {
		for (GiaoVien gv : ds)
			if (gv.getMaGiaoVien().equals(MaGiaoVien)) {
				ds.remove(gv);// Xoa trong bo nho
				return gvdao.Xoa(MaGiaoVien);// Xoa trong csdl
			}
		return 0;
	}

	public int Sua(String MaGiaoVien, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QueQuan,
			String DiDong, String Email, String MaKhoa) throws Exception {
		for (GiaoVien gv : ds)
			if (gv.getMaGiaoVien().equals(MaGiaoVien)) {
				gv.setHoTen(HoTen);
				gv.setDiaChi(DiaChi);
				gv.setGioiTinh(GioiTinh);
				gv.setNgaySinh(NgaySinh);
				gv.setQueQuan(QueQuan);
				gv.setDiDong(DiDong);
				gv.setEmail(Email);
				gv.setMaKhoa(MaKhoa);

				return gvdao.Sua(MaGiaoVien, HoTen, DiaChi, GioiTinh, NgaySinh, QueQuan, DiDong, Email, MaKhoa);
			}
		return 0;
	}
}
