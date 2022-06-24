package bean;

public class HocPhan {
	public String MaHocPhan, TenHocPhan, MaKhoa;

	public HocPhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HocPhan(String maHocPhan, String tenHocPhan, String maKhoa) {
		super();
		MaHocPhan = maHocPhan;
		TenHocPhan = tenHocPhan;
		MaKhoa = maKhoa;
	}

	public String getMaHocPhan() {
		return MaHocPhan;
	}

	public void setMaHocPhan(String maHocPhan) {
		MaHocPhan = maHocPhan;
	}

	public String getTenHocPhan() {
		return TenHocPhan;
	}

	public void setTenHocPhan(String tenHocPhan) {
		TenHocPhan = tenHocPhan;
	}

	public String getMaKhoa() {
		return MaKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		MaKhoa = maKhoa;
	}

	@Override
	public String toString() {
		return "HocPhan [MaHocPhan=" + MaHocPhan + ", TenHocPhan=" + TenHocPhan + ", MaKhoa=" + MaKhoa + "]";
	}

}
