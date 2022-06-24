package bo;

import java.util.ArrayList;

import bean.Lop;
import dao.Lopdao;

public class Lopbo {
	Lopdao lhdao = new Lopdao();
	ArrayList<Lop> ds;

	public ArrayList<Lop> getLop() throws Exception {
		ds = lhdao.getLop();
		return ds;
	}

	public ArrayList<Lop> TimLop(String makhoa) throws Exception {
		ArrayList<Lop> tam = new ArrayList<Lop>();
		for (Lop lh : ds) {
			if (lh.getMaKhoa().equalsIgnoreCase(makhoa))
				tam.add(lh);
		}
		return tam;
	}

	public int Them(String MaLop, String TenLop, String MaKhoa) throws Exception {
		// Kiem tra trung ma
		for (Lop lh : ds)
			if (lh.getMaLop().equals(MaLop))
				return 0;
		// Them vao bo nho
		ds.add(new Lop(MaLop, TenLop, MaKhoa));
		// Them vao csdl
		return lhdao.Them(MaLop, TenLop, MaKhoa); // return 1.
	}

	public int Sua(String MaLop, String TenLop, String MaKhoa) throws Exception {
		for (Lop lh : ds)
			if (lh.getMaLop().equals(MaLop)) {
				lh.setTenLop(TenLop);
				lh.setMaKhoa(MaKhoa);
				return lhdao.Sua(MaLop, TenLop, MaKhoa);
			}
		return 0;
	}

	public int Xoa(String MaLop) throws Exception {
		for (Lop lh : ds)
			if (lh.getMaLop().equals(MaLop)) {
				ds.remove(lh);// Xoa trong bo nho
				return lhdao.Xoa(MaLop);// Xoa trong csdl
			}
		return 0;
	}

	public ArrayList<Lop> TimKiem(String key) throws Exception {

		ArrayList<Lop> temp = new ArrayList<Lop>();
		for (Lop lh : ds) {
			if (lh.getMaKhoa().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| lh.getTenLop().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| lh.getMaLop().toUpperCase().trim().contains(key.toUpperCase().trim())) {
				temp.add(lh);
			}
		}
		return temp;
	}
}
