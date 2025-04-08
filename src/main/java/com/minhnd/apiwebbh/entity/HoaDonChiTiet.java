package com.minhnd.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long donGia;
    private Long soLuong;
    private Long thanhTien;

    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet;
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;

    /*hoa_don_chi_tiet: id bigint (khóa chính), don_gia bigint, so_luong bigint, thanh_tien
    bigint, san_pham_chi_tiet_id bigint (khóa phụ), hoa_don_id bigint (khóa phụ)
    **/
}
