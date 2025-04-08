package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.KichCoDTO;
import com.minhnd.apiwebbh.dto.LoaiSanPhamDTO;
import com.minhnd.apiwebbh.entity.LoaiSanPham;

import java.util.List;


public interface LoaiSanPhamService {
    List<LoaiSanPhamDTO> getAll();
    LoaiSanPhamDTO getById(Long id);
    void create(LoaiSanPhamDTO dto);
    void update(LoaiSanPhamDTO LoaiSanPhamDTO, Long id);
    void delete(Long id);
}
