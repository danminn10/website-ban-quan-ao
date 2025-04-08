package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.LoaiSanPhamDTO;
import com.minhnd.apiwebbh.entity.LoaiSanPham;
import com.minhnd.apiwebbh.service.LoaiSanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loai-san-pham")
public class LoaiSanPhamController {
    private final LoaiSanPhamService loaiSanPhamService;
    @GetMapping
    public List<LoaiSanPhamDTO> getAll(){
        return loaiSanPhamService.getAll();
    }

    @GetMapping("/{id}")
    public LoaiSanPhamDTO getById(@PathVariable Long id){
        return loaiSanPhamService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody LoaiSanPhamDTO loaiSanPhamDTO){
        loaiSanPhamService.create(loaiSanPhamDTO);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody LoaiSanPhamDTO loaiSanPhamDTO){
        loaiSanPhamService.update(loaiSanPhamDTO,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        loaiSanPhamService.delete(id);
    }
}
