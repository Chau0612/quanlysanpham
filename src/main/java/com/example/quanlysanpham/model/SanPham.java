package com.example.quanlysanpham.model;
import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@Entity
@Table(name = "san_pham")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loai_san_pham", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("THUONG")

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "loaiSanPham", defaultImpl = SanPham.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SanPham.class, name = "THUONG"),
    @JsonSubTypes.Type(value = SanPhamKM.class, name = "KHUYEN_MAI")
})
public class SanPham {
    
    
    @Id
    @Column(name = "ma_san_pham")
    private String maSanPham;

    @Column(name = "ten_san_pham", nullable = false)
    private String tenSanPham;

    @Column(name = "gia_san_pham")
    private double giaSanPham;

    @Column(name = "so_luong_san_pham")
    private int soLuongSanPham;

    @Column(name = "ngay_nhap_san_pham")
    private Date ngayNhapSanPham;

    @Column(name = "mo_ta_san_pham")
    private String moTaSanPham;

    public SanPham(String tenSanPham, double giaSanPham, int soLuongSanPham, Date ngayNhapSanPham, String moTaSanPham) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuongSanPham = soLuongSanPham;
        this.ngayNhapSanPham = ngayNhapSanPham;
        this.moTaSanPham = moTaSanPham;
    }


    public SanPham() {
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }
    
    public Date getNgayNhapSanPham() {
        return ngayNhapSanPham;
    }

    public void setNgayNhapSanPham(Date ngayNhapSanPham) {
        this.ngayNhapSanPham = ngayNhapSanPham;
    }


    public double tinhGiaBan() {
        return giaSanPham;
    }


    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String ngayNhap = df.format(ngayNhapSanPham);

        return String.format("SP THUONG - Ma sp: %s\nTen sp: %s\nGia sp: %,.0f vnd\nSo Luong: %d\nNgay nhap: %s\nMo ta: %s\n----------------------------------------------------", 
        maSanPham, tenSanPham, giaSanPham, soLuongSanPham, ngayNhap, moTaSanPham);
    }

    



}
