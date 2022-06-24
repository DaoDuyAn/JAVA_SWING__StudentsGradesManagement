package bo;

import java.util.ArrayList;
import java.util.Date;

import bean.SinhVien;
import dao.SinhViendao;

public class SinhVienbo {
	SinhViendao svdao = new SinhViendao();
	ArrayList<SinhVien> ds;

	public ArrayList<SinhVien> getSinhVien() throws Exception {
		ds = svdao.getSinhVien();
		return ds;
	}

	public ArrayList<SinhVien> TimLop(String maLop) throws Exception {
		ArrayList<SinhVien> tam = new ArrayList<SinhVien>();
		for (SinhVien sv : ds) {
			if (sv.getMaLop().equalsIgnoreCase(maLop))
				tam.add(sv);
		}
		return tam;
	}

	public ArrayList<SinhVien> TimKiem(String key) throws Exception {

		ArrayList<SinhVien> temp = new ArrayList<SinhVien>();
		for (SinhVien sv : ds) {
			if (sv.getMaSV().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| sv.getHoTen().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| sv.getMaLop().toUpperCase().trim().contains(key.toUpperCase().trim())) {
				temp.add(sv);
			}
		}
		return temp;
	}

	public int Them(String MaSV, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QuocTich,
			String QueQuan, String DanToc, String TonGiao, String DiDong, String Email, String MaLop) throws Exception {
		for (SinhVien sv : ds)
			if (sv.getMaSV().equals(MaSV))
				return 0;
		// Them vao bo nho
		ds.add(new SinhVien(MaSV, HoTen, DiaChi, QuocTich, QueQuan, DanToc, TonGiao, DiDong, Email, MaLop, GioiTinh,
				NgaySinh));
		return svdao.Them(MaSV, HoTen, DiaChi, GioiTinh, NgaySinh, QuocTich, QueQuan, DanToc, TonGiao, DiDong, Email,
				MaLop);
	}

	public int Xoa(String MaSinhVien) throws Exception {
		for (SinhVien sv : ds)
			if (sv.getMaSV().equals(MaSinhVien)) {
				ds.remove(sv);// Xoa trong bo nho
				return svdao.Xoa(MaSinhVien);// Xoa trong csdl
			}
		return 0;
	}

	public int Sua(String MaSV, String HoTen, String DiaChi, Boolean GioiTinh, Date NgaySinh, String QuocTich,
			String QueQuan, String DanToc, String TonGiao, String DiDong, String Email, String MaLop) throws Exception {
		for (SinhVien sv : ds)
			if (sv.getMaSV().equals(MaSV)) {
				sv.setHoTen(HoTen);
				sv.setDiaChi(DiaChi);
				sv.setGioiTinh(GioiTinh);
				sv.setNgaySinh(NgaySinh);
				sv.setQuocTich(QuocTich);
				sv.setQueQuan(QueQuan);
				sv.setDanToc(DanToc);
				sv.setTonGiao(TonGiao);
				sv.setDiDong(DiDong);
				sv.setEmail(Email);
				sv.setMaLop(MaLop);

				return svdao.Sua(MaSV, HoTen, DiaChi, GioiTinh, NgaySinh, QuocTich, QueQuan, DanToc, TonGiao, DiDong,
						Email, MaLop);
			}
		return 0;
	}
}
