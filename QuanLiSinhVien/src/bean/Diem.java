package bean;

public class Diem {
	public String MaLopHP, MaSV, DiemChu;
	public int DiemCC;
	public double DiemKT, DiemThi, DiemKTHP;

	public Diem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Diem(String maLopHP, String maSV, String diemChu, int diemCC, double diemKT, double diemThi,
			double diemKTHP) {
		super();
		MaLopHP = maLopHP;
		MaSV = maSV;
		DiemChu = diemChu;
		DiemCC = diemCC;
		DiemKT = diemKT;
		DiemThi = diemThi;
		DiemKTHP = diemKTHP;
	}

	public Diem(String maLopHP, String maSV) {
		super();
		MaLopHP = maLopHP;
		MaSV = maSV;
	}

	public String getMaLopHP() {
		return MaLopHP;
	}

	public void setMaLopHP(String maLopHP) {
		MaLopHP = maLopHP;
	}

	public String getMaSV() {
		return MaSV;
	}

	public void setMaSV(String maSV) {
		MaSV = maSV;
	}

	public String getDiemChu() {
		return DiemChu;
	}

	public void setDiemChu(String diemChu) {
		DiemChu = diemChu;
	}

	public int getDiemCC() {
		return DiemCC;
	}

	public void setDiemCC(int diemCC) {
		DiemCC = diemCC;
	}

	public double getDiemKT() {
		return DiemKT;
	}

	public void setDiemKT(double diemKT) {
		DiemKT = diemKT;
	}

	public double getDiemThi() {
		return DiemThi;
	}

	public void setDiemThi(double diemThi) {
		DiemThi = diemThi;
	}

	public double getDiemKTHP() {
		return DiemKTHP;
	}

	public void setDiemKTHP(double diemKTHP) {
		DiemKTHP = diemKTHP;
	}

}
