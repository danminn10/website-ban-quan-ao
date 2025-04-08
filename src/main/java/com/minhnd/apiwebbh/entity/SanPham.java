package com.minhnd.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma;
    private String ten;
    private long gia;
    private long soLuongTonKho;
    private long soLuongDaBan;
    private String moTa;
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "loai_san_pham_id")
    private LoaiSanPham loaiSanPham;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "anh_san_pham")
    private List<String> images;

}
