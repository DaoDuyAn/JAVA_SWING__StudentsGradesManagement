package bo;

import java.util.ArrayList;
import java.util.Date;

import bean.LopHocPhan;
import dao.LopHocPhandao;

public class LopHocPhanbo {
	LopHocPhandao lhpdao = new LopHocPhandao();
	ArrayList<LopHocPhan> ds;

	public ArrayList<LopHocPhan> getLopHocPhan() throws Exception {
		ds = lhpdao.getLopHocPhan();
		return ds;
	}

	public ArrayList<LopHocPhan> TimHocPhan(String mahp) throws Exception {
		// Tao ra mot mang dong: tam
		// Duyet qua ds. Neu tim thay thi dua vao mang tam
		ArrayList<LopHocPhan> tam = new ArrayList<LopHocPhan>();
		for (LopHocPhan lhp : ds) {
			if (lhp.getMaHocPhan().equalsIgnoreCase(mahp))
				tam.add(lhp);
		}
		return tam;
	}

	public ArrayList<LopHocPhan> TimLopHocPhan(String mahp) throws Exception {
		ArrayList<LopHocPhan> tam = new ArrayList<LopHocPhan>();
		for (LopHocPhan lhp : ds) {
			if (lhp.getMaHocPhan().equalsIgnoreCase(mahp))
				tam.add(lhp);
		}
		return tam;
	}

	public ArrayList<LopHocPhan> TimKiem(String key) throws Exception {

		ArrayList<LopHocPhan> temp = new ArrayList<LopHocPhan>();
		for (LopHocPhan lhp : ds) {
			if (lhp.getMaLopHP().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| lhp.getTenLopHP().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| lhp.getMaGiaoVien().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| lhp.getMaHocPhan().toUpperCase().trim().contains(key.toUpperCase().trim())) {
				temp.add(lhp);
			}
		}
		return temp;
	}

	public int Them(String MaLopHocPhan, String TenLopHocPhan, int SoTinChi, Date NgayBatDau, String MaHocPhan,
			String MaGiaoVien) throws Exception {
		for (LopHocPhan lhp : ds)
			if (lhp.getMaLopHP().equals(MaLopHocPhan))
				return 0;
		// Them vao bo nho
		ds.add(new LopHocPhan(MaLopHocPhan, TenLopHocPhan, MaHocPhan, MaGiaoVien, SoTinChi, NgayBatDau));
		return lhpdao.Them(MaLopHocPhan, TenLopHocPhan, MaHocPhan, MaGiaoVien, SoTinChi, NgayBatDau);
	}

	public int Xoa(String MaLopHocPhan) throws Exception {
		for (LopHocPhan lhp : ds)
			if (lhp.getMaLopHP().equals(MaLopHocPhan)) {
				ds.remove(lhp);// Xoa trong bo nho
				return lhpdao.Xoa(MaLopHocPhan);// Xoa trong csdl
			}
		return 0;
	}

	public int Sua(String MaLopHocPhan, String TenLopHocPhan, int SoTinChi, Date NgayBatDau, String MaHocPhan,
			String MaGiaoVien) throws Exception {
		for (LopHocPhan lhp : ds)
			if (lhp.getMaLopHP().equals(MaLopHocPhan)) {
				lhp.setTenLopHP(TenLopHocPhan);
				lhp.setSoTinChi(SoTinChi);
				lhp.setNgayBatDau(NgayBatDau);
				lhp.setMaHocPhan(MaHocPhan);
				lhp.setMaGiaoVien(MaGiaoVien);

				return lhpdao.Sua(MaLopHocPhan, TenLopHocPhan, MaHocPhan, MaGiaoVien, SoTinChi, NgayBatDau);
			}
		return 0;
	}

}
