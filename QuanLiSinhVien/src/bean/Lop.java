package bean;

public class Lop {
	public String MaLop, TenLop, MaKhoa;

	public Lop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lop(String maLop, String tenLop, String maKhoa) {
		super();
		MaLop = maLop;
		TenLop = tenLop;
		MaKhoa = maKhoa;
	}

	public String getMaLop() {
		return MaLop;
	}

	public void setMaLop(String maLop) {
		MaLop = maLop;
	}

	public String getTenLop() {
		return TenLop;
	}

	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}

	public String getMaKhoa() {
		return MaKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		MaKhoa = maKhoa;
	}

	@Override
	public String toString() {
		return "Lop [MaLop=" + MaLop + ", TenLop=" + TenLop + ", MaKhoa=" + MaKhoa + "]";
	}

}
