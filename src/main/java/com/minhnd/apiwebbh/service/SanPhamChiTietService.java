package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.SanPhamChiTietDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SanPhamChiTietService {
    ResponseDTO<List<SanPhamChiTietDTO>> getAll(Integer page, Integer size);
    List<SanPhamChiTietDTO> getBySpId(Long sanPhamId);
    SanPhamChiTietDTO getById(Long id);
    void create(SanPhamChiTietDTO dto);
    void update(Long id, SanPhamChiTietDTO dto);
    void delete(Long id);
}
