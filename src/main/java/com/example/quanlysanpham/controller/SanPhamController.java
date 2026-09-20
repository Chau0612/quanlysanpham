package com.example.quanlysanpham.controller;

import com.example.quanlysanpham.model.SanPham;
import com.example.quanlysanpham.service.QlySanPham;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@CrossOrigin("*")
public class SanPhamController {
    private final QlySanPham qlySanPham;
    public SanPhamController(QlySanPham qlySanPham) {
        this.qlySanPham = qlySanPham;
    }

    @GetMapping
    public List<SanPham> layDanhSach() {
        return qlySanPham.getDanhSachSanPham();
    }

    @PostMapping
    public String themSanPham(@RequestBody SanPham sanPham) {
        qlySanPham.themSanPham(sanPham);
        return "Thêm sản phẩm thành công!";
    }

    // 3. Sửa sản phẩm theo mã (PUT) 
    @PutMapping("/{maSanPham}")
    public String capNhatSanPham(@PathVariable String maSanPham, @RequestBody SanPham sanPhamMoi) {
        boolean thanhCong = qlySanPham.capNhatSanPham(maSanPham, sanPhamMoi);
        if (thanhCong) {
            return "Cập nhật sản phẩm " + maSanPham + " thành công!";
        }
        return "Không tìm thấy sản phẩm mã: " + maSanPham;
    }

    // 4. Xóa sản phẩm theo mã (DELETE) 
    @DeleteMapping("/{maSanPham}")
    public String xoaSanPham(@PathVariable String maSanPham) {
        boolean thanhCong = qlySanPham.xoaSanPham(maSanPham);
        if (thanhCong) {
            return "Xóa sản phẩm " + maSanPham + " thành công!";
        }
        return "Không tìm thấy sản phẩm mã: " + maSanPham;
    }

    // 5. Tìm kiếm theo chữ cái đầu (GET) 
    @GetMapping("/tim")
    public List<SanPham> timSanPhamTheoChuCai(@RequestParam String chuCai) {
        return qlySanPham.timSanPhamTheoChuCai(chuCai);
    }

    // 6. Sắp xếp danh sách theo giá (GET) 
    @GetMapping("/sap-xep")
    public List<SanPham> sapXepTheoGia() {
        return qlySanPham.sapXepTheoGia();
    }
}