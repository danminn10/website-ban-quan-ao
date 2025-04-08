package com.minhnd.apiwebbh.dto;

import com.minhnd.apiwebbh.entity.TaiKhoan;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GioHangDTO {
    private Long id;
    private Long tongSoSanPham;
    private Long tongSoTien;
    private TaiKhoanDTO taiKhoan;
}
