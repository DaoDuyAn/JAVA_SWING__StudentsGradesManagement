package bean;

import java.util.Date;

public class LopHocPhan {
	public String MaLopHP, TenLopHP, MaHocPhan, MaGiaoVien;
	public int SoTinChi;
	private Date NgayBatDau;

	public LopHocPhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LopHocPhan(String maLopHP, String tenLopHP, String maHocPhan, String maGiaoVien, int soTinChi,
			Date ngayBatDau) {
		super();
		MaLopHP = maLopHP;
		TenLopHP = tenLopHP;
		MaHocPhan = maHocPhan;
		MaGiaoVien = maGiaoVien;
		SoTinChi = soTinChi;
		NgayBatDau = ngayBatDau;
	}

	public String getMaLopHP() {
		return MaLopHP;
	}

	public void setMaLopHP(String maLopHP) {
		MaLopHP = maLopHP;
	}

	public String getTenLopHP() {
		return TenLopHP;
	}

	public void setTenLopHP(String tenLopHP) {
		TenLopHP = tenLopHP;
	}

	public String getMaHocPhan() {
		return MaHocPhan;
	}

	public void setMaHocPhan(String maHocPhan) {
		MaHocPhan = maHocPhan;
	}

	public String getMaGiaoVien() {
		return MaGiaoVien;
	}

	public void setMaGiaoVien(String maGiaoVien) {
		MaGiaoVien = maGiaoVien;
	}

	public int getSoTinChi() {
		return SoTinChi;
	}

	public void setSoTinChi(int soTinChi) {
		SoTinChi = soTinChi;
	}

	public Date getNgayBatDau() {
		return NgayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}

	@Override
	public String toString() {
		return "LopHocPhan [MaLopHP=" + MaLopHP + ", TenLopHP=" + TenLopHP + ", MaHocPhan=" + MaHocPhan
				+ ", MaGiaoVien=" + MaGiaoVien + ", SoTinChi=" + SoTinChi + ", NgayBatDau=" + NgayBatDau + "]";
	}

}
