package com.minhnd.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long soLuong;

    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet; // 1 gio hang chi tiet se co 1 sp chi tiet
    // 1 sp chi tiet co the nam trong nhieu gio hang chi tiet

    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang;

    //gio_hang_chi_tiet: id bigint (khóa chính), so_luong bigint, san_pham_chi_tiet_id bigint (khóa phụ), cart_id bigint (khóa phụ)
}
