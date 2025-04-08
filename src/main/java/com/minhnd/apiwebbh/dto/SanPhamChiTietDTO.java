package com.minhnd.apiwebbh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTietDTO   {
    private long id;
    private String ma;
    private String ten;
    private long gia;
    private long soLuongTonKho;
    private long soLuongDaBan;
    private Integer trangThai;
    private SanPhamDTO sanPham;
    private MauSacDTO mauSac;
    private KichCoDTO kichCo;

}
