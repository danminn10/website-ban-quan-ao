package com.minhnd.apiwebbh.dto;

import com.minhnd.apiwebbh.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaiKhoanDTO {
    private Long id;
    private String ma;
    private String email;
    private String soDienThoai;
    private String matKhau;
    private String hoVaTen;
    private Role role;
    private long tongHoaDon; // 1 hd
    private long tongTien; // 10tr
    private Integer hangTaiKhoan; // 1kh thuong 2 kh vip tong
    private Integer trangThai;
}
