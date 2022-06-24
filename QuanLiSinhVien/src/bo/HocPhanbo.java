package bo;

import java.util.ArrayList;
import bean.HocPhan;
import dao.HocPhandao;

public class HocPhanbo {
	HocPhandao hpdao = new HocPhandao();
	ArrayList<HocPhan> ds;

	public ArrayList<HocPhan> getHocPhan() throws Exception {
		ds = hpdao.getHocPhan();
		return ds;
	}

	public int Them(String MaHocPhan, String TenHocPhan, String MaKhoa) throws Exception {
		// Kiem tra trung ma
		for (HocPhan hp : ds)
			if (hp.getMaHocPhan().equals(MaHocPhan))
				return 0;
		// Them vao bo nho
		ds.add(new HocPhan(MaHocPhan, TenHocPhan, MaKhoa));
		// Them vao csdl
		return hpdao.Them(MaHocPhan, TenHocPhan, MaKhoa); // return 1.
	}

	public int Sua(String MaHocPhan, String TenHocPhan, String MaKhoa) throws Exception {
		for (HocPhan hp : ds)
			if (hp.getMaHocPhan().equals(MaHocPhan)) {
				hp.setTenHocPhan(TenHocPhan);
				hp.setMaKhoa(MaKhoa);
				return hpdao.Sua(MaHocPhan, TenHocPhan, MaKhoa);
			}
		return 0;
	}

	public int Xoa(String MaHocPhan) throws Exception {
		for (HocPhan hp : ds)
			if (hp.getMaHocPhan().equals(MaHocPhan)) {
				ds.remove(hp);// Xoa trong bo nho
				return hpdao.Xoa(MaHocPhan);// Xoa trong csdl
			}
		return 0;
	}

	public ArrayList<HocPhan> TimKiem(String key) throws Exception {

		ArrayList<HocPhan> temp = new ArrayList<HocPhan>();
		for (HocPhan hp : ds) {
			if (hp.getMaKhoa().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| hp.getTenHocPhan().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| hp.getMaHocPhan().toUpperCase().trim().contains(key.toUpperCase().trim())) {
				temp.add(hp);
			}
		}
		return temp;
	}

	public ArrayList<HocPhan> TimHocPhan(String makhoa) throws Exception {
		ArrayList<HocPhan> tam = new ArrayList<HocPhan>();
		for (HocPhan hp : ds) {
			if (hp.getMaKhoa().equalsIgnoreCase(makhoa))
				tam.add(hp);
		}
		return tam;
	}
}
