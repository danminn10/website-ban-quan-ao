package com.minhnd.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonDTO {
    private Long id;
    private String maHoaDon;
    private Long tongSoSanPham;
    private Long tongSoTien;
    private String soDienThoai;
    private String diaChi;
    private String hoVaTen;
    private String maNhanVien;
    private LocalDate ngayTao; // khi đơn hàng được đặt sẽ có ngày tạo
    private LocalDate ngayHoanThanh;
    private String lyDoHuy;
    private Integer trangThai;
    private Integer loaiHoaDon;
    private TaiKhoanDTO taiKhoan;
}
