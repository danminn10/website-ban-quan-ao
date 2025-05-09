package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.SanPhamDTO;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface SanPhamService {
    ResponseEntity<List<SanPhamDTO>> getAll();

    ResponseEntity<SanPhamDTO> getById(Long id);

    ResponseEntity<String> create(SanPhamDTO dto) throws IOException;

    ResponseEntity<String> update(SanPhamDTO dto, Long id) throws IOException;

    ResponseEntity<String> delete(Long id);
    ResponseEntity<List<SanPhamDTO>> filter(String ten, Long loaiSanPhamId);
}
