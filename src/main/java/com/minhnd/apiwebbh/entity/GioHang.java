package com.minhnd.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tongSoSanPham;
    private Long tongSoTien;

    @OneToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;


    //gio_hang: id bigint (khóa chính), tong_so_san_pham bigint, tong_so_tien bigint, tai_khoan_id bigint (khóa phụ)
}
