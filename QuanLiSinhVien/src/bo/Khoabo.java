package bo;

import java.util.ArrayList;

import bean.Khoa;
import dao.Khoadao;

public class Khoabo {
	Khoadao khdao = new Khoadao();
	ArrayList<Khoa> dsk;

	public ArrayList<Khoa> getKhoa() throws Exception {
		dsk = khdao.getKhoa();
		return dsk;
	}

	public int Them(String MaKhoa, String TenKhoa) throws Exception {
		// Kiem tra trung ma
		for (Khoa kh : dsk)
			if (kh.getMaKhoa().equals(MaKhoa))
				return 0;
		// Them vao bo nho
		dsk.add(new Khoa(MaKhoa, TenKhoa));
		// Them vao csdl
		return khdao.Them(MaKhoa, TenKhoa); // return 1.
	}

	public int Xoa(String MaKhoa) throws Exception {
		for (Khoa kh : dsk)
			if (kh.getMaKhoa().equals(MaKhoa)) {
				dsk.remove(kh);// Xoa trong bo nho
				return khdao.Xoa(MaKhoa);// Xoa trong csdl
			}
		return 0;
	}

	public int Sua(String MaKhoa, String TenKhoa) throws Exception {
		for (Khoa kh : dsk)
			if (kh.getMaKhoa().equals(MaKhoa)) {
				kh.setTenKhoa(TenKhoa);
				return khdao.Sua(MaKhoa, TenKhoa);
			}
		return 0;
	}
	
	public ArrayList<Khoa> TimKiem(String key) throws Exception {

		ArrayList<Khoa> temp = new ArrayList<Khoa>();
		for (Khoa kh : dsk) {
			if (kh.getMaKhoa().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| kh.getTenKhoa().toUpperCase().trim().contains(key.toUpperCase().trim())) {
				temp.add(kh);
			}
		}
		return temp;
	}
}
