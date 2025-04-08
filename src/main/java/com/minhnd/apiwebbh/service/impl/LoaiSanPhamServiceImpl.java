package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.LoaiSanPhamDTO;
import com.minhnd.apiwebbh.entity.LoaiSanPham;
import com.minhnd.apiwebbh.repository.LoaiSanPhamRepository;
import com.minhnd.apiwebbh.service.LoaiSanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    private final LoaiSanPhamRepository loaiSanPhamRepo;
    @Override
    public List<LoaiSanPhamDTO> getAll() {
        List<LoaiSanPham> list = loaiSanPhamRepo.findAll();
        List<LoaiSanPhamDTO> listLSPDTO = new ArrayList<>();
        mapToListDTO(list, listLSPDTO);
        return listLSPDTO;
    }

    @Override
    public LoaiSanPhamDTO getById(Long id) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepo.findById(id).orElse(null);
        LoaiSanPhamDTO loaiSanPhamDTO = new LoaiSanPhamDTO();
        loaiSanPhamDTO.setId(loaiSanPham.getId());
        loaiSanPhamDTO.setTen(loaiSanPham.getTen());
        return loaiSanPhamDTO;
    }

    @Override
    public void create(LoaiSanPhamDTO dto) {
        LoaiSanPham loaiSanPham = new LoaiSanPham();
        loaiSanPham.setTen(dto.getTen());
        loaiSanPhamRepo.save(loaiSanPham);
    }

    @Override
    public void update(LoaiSanPhamDTO LoaiSanPhamDTO, Long id) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepo.findById(id).orElse(null);
        if (loaiSanPham != null) {
            loaiSanPham.setTen(LoaiSanPhamDTO.getTen());
            loaiSanPhamRepo.save(loaiSanPham);
        } else {
            throw new RuntimeException("LoaiSanPham not found");
        }
    }

    @Override
    public void delete(Long id) {
        loaiSanPhamRepo.deleteById(id);
    }

    private void mapToListDTO(List<LoaiSanPham> entities, List<LoaiSanPhamDTO> dtos) {
        for (LoaiSanPham entity : entities) {
            LoaiSanPhamDTO loaiSanPhamDTO = new LoaiSanPhamDTO();
            loaiSanPhamDTO.setId(entity.getId());
            loaiSanPhamDTO.setTen(entity.getTen());
            dtos.add(loaiSanPhamDTO);
        }
    }
}
