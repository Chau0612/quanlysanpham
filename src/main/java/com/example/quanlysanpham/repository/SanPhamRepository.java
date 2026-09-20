package com.example.quanlysanpham.repository;

import com.example.quanlysanpham.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, String> {
    List<SanPham> findByTenSanPhamStartingWithIgnoreCase(String chuCai);
}