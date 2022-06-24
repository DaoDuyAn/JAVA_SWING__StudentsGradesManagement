package bean;

import java.util.Date;

public class GiaoVien {
	public String MaGiaoVien, HoTen, DiaChi, QueQuan, DiDong, Email, MaKhoa;
	public Boolean GioiTinh;
	public Date NgaySinh;

	public GiaoVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GiaoVien(String maGiaoVien, String hoTen, String diaChi, String queQuan, String diDong, String email,
			String maKhoa, Boolean gioiTinh, Date ngaySinh) {
		super();
		MaGiaoVien = maGiaoVien;
		HoTen = hoTen;
		DiaChi = diaChi;
		QueQuan = queQuan;
		DiDong = diDong;
		Email = email;
		MaKhoa = maKhoa;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
	}

	public String getMaGiaoVien() {
		return MaGiaoVien;
	}

	public void setMaGiaoVien(String maGiaoVien) {
		MaGiaoVien = maGiaoVien;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getQueQuan() {
		return QueQuan;
	}

	public void setQueQuan(String queQuan) {
		QueQuan = queQuan;
	}

	public String getDiDong() {
		return DiDong;
	}

	public void setDiDong(String diDong) {
		DiDong = diDong;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMaKhoa() {
		return MaKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		MaKhoa = maKhoa;
	}

	public Boolean getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	@Override
	public String toString() {
		return "GiaoVien [MaGiaoVien=" + MaGiaoVien + ", HoTen=" + HoTen + ", DiaChi=" + DiaChi + ", QueQuan=" + QueQuan
				+ ", DiDong=" + DiDong + ", Email=" + Email + ", MaKhoa=" + MaKhoa + ", GioiTinh=" + GioiTinh
				+ ", NgaySinh=" + NgaySinh + "]";
	}

}
