package com.minhnd.apiwebbh.dto;

import com.minhnd.apiwebbh.entity.GioHang;
import com.minhnd.apiwebbh.entity.SanPhamChiTiet;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTietDTO {
    private Long id;
    private Long soLuong;
    private SanPhamChiTietDTO sanPhamChiTiet; // 1 gio hang chi tiet se co 1 sp chi tiet
    // 1 sp chi tiet co the nam trong nhieu gio hang chi tiet
    private GioHangDTO gioHang;
}
