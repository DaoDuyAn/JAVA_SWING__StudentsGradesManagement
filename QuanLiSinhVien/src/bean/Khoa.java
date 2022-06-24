package bean;

public class Khoa {
	public String MaKhoa, TenKhoa;

	public Khoa(String maKhoa, String tenKhoa) {
		super();
		MaKhoa = maKhoa;
		TenKhoa = tenKhoa;
	}

	public Khoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaKhoa() {
		return MaKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		MaKhoa = maKhoa;
	}

	public String getTenKhoa() {
		return TenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		TenKhoa = tenKhoa;
	}

	@Override
	public String toString() {
		return "Khoa [MaKhoa=" + MaKhoa + ", TenKhoa=" + TenKhoa + "]";
	}

}
