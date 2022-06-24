package bean;

import java.util.Date;

public class SinhVien {
	public String MaSV, HoTen, DiaChi, QuocTich, QueQuan, DanToc, TonGiao, DiDong, Email, MaLop;
	private Boolean GioiTinh;
	private Date NgaySinh;

	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SinhVien(String maSV, String hoTen, String diaChi, String quocTich, String queQuan, String danToc,
			String tonGiao, String diDong, String email, String maLop, Boolean gioiTinh, Date ngaySinh) {
		super();
		MaSV = maSV;
		HoTen = hoTen;
		DiaChi = diaChi;
		QuocTich = quocTich;
		QueQuan = queQuan;
		DanToc = danToc;
		TonGiao = tonGiao;
		DiDong = diDong;
		Email = email;
		MaLop = maLop;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
	}

	public String getMaSV() {
		return MaSV;
	}

	public void setMaSV(String maSV) {
		MaSV = maSV;
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

	public String getQuocTich() {
		return QuocTich;
	}

	public void setQuocTich(String quocTich) {
		QuocTich = quocTich;
	}

	public String getQueQuan() {
		return QueQuan;
	}

	public void setQueQuan(String queQuan) {
		QueQuan = queQuan;
	}

	public String getDanToc() {
		return DanToc;
	}

	public void setDanToc(String danToc) {
		DanToc = danToc;
	}

	public String getTonGiao() {
		return TonGiao;
	}

	public void setTonGiao(String tonGiao) {
		TonGiao = tonGiao;
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

	public String getMaLop() {
		return MaLop;
	}

	public void setMaLop(String maLop) {
		MaLop = maLop;
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
		return "SinhVien [MaSV=" + MaSV + ", HoTen=" + HoTen + ", DiaChi=" + DiaChi + ", QuocTich=" + QuocTich
				+ ", QueQuan=" + QueQuan + ", DanToc=" + DanToc + ", TonGiao=" + TonGiao + ", DiDong=" + DiDong
				+ ", Email=" + Email + ", MaLop=" + MaLop + ", GioiTinh=" + GioiTinh + ", NgaySinh=" + NgaySinh + "]";
	}

}
