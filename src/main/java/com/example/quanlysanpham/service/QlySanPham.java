package com.example.quanlysanpham.service;

import com.example.quanlysanpham.model.SanPham;
import com.example.quanlysanpham.repository.SanPhamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QlySanPham {

    private final SanPhamRepository sanPhamRepository;

    public QlySanPham(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }

    public List<SanPham> getDanhSachSanPham() {
        return sanPhamRepository.findAll();
    }

    public String sinhMaSanPhamTuDong() {
        List<SanPham> list = sanPhamRepository.findAll();
        int maxId = 0;
        for (SanPham sp : list) {
            if (sp.getMaSanPham() != null && sp.getMaSanPham().toUpperCase().startsWith("SP")) {
                try {
                    int idNum = Integer.parseInt(sp.getMaSanPham().substring(2).trim());
                    if (idNum > maxId) {
                        maxId = idNum;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return String.format("SP%03d", maxId + 1);
    }

    public void themSanPham(SanPham sanPham) {
        if (sanPham.getMaSanPham() == null || sanPham.getMaSanPham().trim().isEmpty()) {
            sanPham.setMaSanPham(sinhMaSanPhamTuDong());
        }
        sanPhamRepository.save(sanPham);
    }

    public boolean xoaSanPham(String maSanPham) {
        if (sanPhamRepository.existsById(maSanPham)) {
            sanPhamRepository.deleteById(maSanPham);
            return true;
        }
        return false;
    }

    public boolean capNhatSanPham(String maSanPham, SanPham sanPhamMoi) {
        if (sanPhamRepository.existsById(maSanPham)) {
            sanPhamMoi.setMaSanPham(maSanPham);
            sanPhamRepository.save(sanPhamMoi);
            return true;
        }
        return false;
    }

    public List<SanPham> sapXepTheoGia() {
        List<SanPham> list = sanPhamRepository.findAll();
        list.sort((sp1, sp2) -> Double.compare(sp1.tinhGiaBan(), sp2.tinhGiaBan()));
        return list;
    }

    public List<SanPham> timSanPhamTheoChuCai(String chuCai) {
        return sanPhamRepository.findByTenSanPhamStartingWithIgnoreCase(chuCai);
    }
}