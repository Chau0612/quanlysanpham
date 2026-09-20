package com.example.quanlysanpham.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@DiscriminatorValue("KHUYEN_MAI")
public class SanPhamKM extends SanPham {
    private double phanTramGiam;

    public SanPhamKM(String tenSanPham, double giaSanPham, int soLuongSanPham, Date ngayNhapSanPham, String moTaSanPham, double phamTramGiam) {
        super(tenSanPham, giaSanPham, soLuongSanPham, ngayNhapSanPham, moTaSanPham);
        this.phanTramGiam = phamTramGiam;
    }

    public SanPhamKM() {
        super();
    }

    public double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    @Override 
    public double tinhGiaBan() {
        return super.tinhGiaBan() * ((100 - phanTramGiam) / 100);
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String ngayNhap = df.format(getNgayNhapSanPham());

        return String.format("SP KHUYEN MAI - Ma sp: %s\nTen sp: %s\nGia sp: %,.0f vnd\nSo Luong: %d\nNgay nhap: %s\nMo ta: %s\nGia khuyen mai: %,.0f vnd\n----------------------------------------------------", 
                getMaSanPham(), getTenSanPham(), getGiaSanPham(), getSoLuongSanPham(), ngayNhap, getMoTaSanPham(), tinhGiaBan());
    }

    
}
