package bo;

import java.util.ArrayList;

import bean.Diem;
import dao.Diemdao;

public class Diembo {
	Diemdao diemdao = new Diemdao();
	ArrayList<Diem> ds;

	public ArrayList<Diem> getDiem() throws Exception {
		ds = diemdao.getDiem();
		return ds;
	}

	public ArrayList<Diem> TimKiem(String key) throws Exception {

		ArrayList<Diem> temp = new ArrayList<Diem>();
		for (Diem d : ds) {
			if (d.getMaSV().toUpperCase().trim().contains(key.toUpperCase().trim())
					|| d.getMaLopHP().toUpperCase().trim().contains(key.toUpperCase().trim())) {
				temp.add(d);
			}
		}
		return temp;
	}

	public int Them(String MaLopHP, String MaSV) throws Exception {
		for (Diem d : ds)
			if (d.getMaSV().equals(MaSV) && d.getMaLopHP().equals(MaLopHP))
				return 0;
		// Them vao bo nho
		ds.add(new Diem(MaLopHP, MaSV));
		return diemdao.Them(MaLopHP, MaSV);

	}

	public int Xoa(String MaLopHP, String MaSV) throws Exception {
		for (Diem d : ds)
			if (d.getMaSV().equals(MaSV) && d.getMaLopHP().equals(MaLopHP)) {
				ds.remove(d);// Xoa trong bo nho
				return diemdao.Xoa(MaLopHP, MaSV);// Xoa trong csdl
			}
		return 0;
	}

	public int Sua(String MaLopHP, String MaSV, int DiemCC, double DiemKT, double DiemThi) throws Exception {
		for (Diem d : ds)
			if (d.getMaSV().equals(MaSV) && d.getMaLopHP().equals(MaLopHP)) {
				d.setDiemCC(DiemCC);
				d.setDiemKT(DiemKT);
				d.setDiemThi(DiemThi);
				return diemdao.Sua(MaLopHP, MaSV, DiemCC, DiemKT, DiemThi);
			}
		return 0;
	}

	public String diemChu(double dkt) {
		if (dkt >= 8.5) {
			return "A";
		} else if (dkt >= 7.0 && dkt < 8.5) {
			return "B";
		} else if (dkt >= 5.5 && dkt < 7.0) {
			return "C";
		} else if (dkt >= 4.0 && dkt < 5.5) {
			return "D";
		} else {
			return "F";
		}
	}

	public void chamDiem(String MaLopHP, int tinh) throws Exception {
		double dkthp, ltd;
		for (Diem d : ds) {
			if (d.getMaLopHP().equals(MaLopHP)) {
				if (tinh == 40) {
					dkthp = ((double) d.getDiemCC() * 0.1 + d.getDiemKT() * 0.3 + d.getDiemThi() * 0.6);
					ltd = (double) Math.round(dkthp * 10d) / 10d;
					d.setDiemKTHP(ltd);
					d.setDiemChu(diemChu(ltd));
					diemdao.chamDiem(MaLopHP, d.getMaSV(), ltd, diemChu(ltd));
				} else if (tinh == 50) {
					dkthp = ((double) d.getDiemCC() * 0.1 + d.getDiemKT() * 0.4 + d.getDiemThi() * 0.5);
					ltd = (double) Math.round(dkthp * 10d) / 10d;
					d.setDiemKTHP(ltd);
					d.setDiemChu(diemChu(ltd));
					diemdao.chamDiem(MaLopHP, d.getMaSV(), ltd, diemChu(ltd));
				} else if (tinh == 30) {
					dkthp = ((double) d.getDiemCC() * 0.1 + d.getDiemKT() * 0.2 + d.getDiemThi() * 0.7);
					ltd = (double) Math.round(dkthp * 10d) / 10d;
					d.setDiemKTHP(ltd);
					d.setDiemChu(diemChu(ltd));
					diemdao.chamDiem(MaLopHP, d.getMaSV(), ltd, diemChu(ltd));
				}

			}
		}
	}
}
