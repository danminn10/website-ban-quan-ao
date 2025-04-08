package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.SanPhamChiTietDTO;
import com.minhnd.apiwebbh.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/san-pham-chi-tiet")
public class SanPhamChiTietController {
    private final SanPhamChiTietService sanPhamChiTietService;
    @GetMapping
    public ResponseDTO<List<SanPhamChiTietDTO>> getAll(@RequestParam Integer page,@RequestParam Integer size){
        return sanPhamChiTietService.getAll(page,size);
    }
    @GetMapping("/getBySpId")
    public List<SanPhamChiTietDTO> getBySpId(@RequestParam Long spId){
        return sanPhamChiTietService.getBySpId(spId);
    }
    @PostMapping
    public  ResponseDTO<Void> create(@RequestBody SanPhamChiTietDTO dto){
        sanPhamChiTietService.create(dto);
        return ResponseDTO.<Void>builder()
                .message("them san pham chi tiet thanh cong")
                .status(201)
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        sanPhamChiTietService.delete(id);
        return ResponseDTO.<Void>builder()
                .message("xoa san pham chi tiet thanh cong")
                .build();
    }
}
