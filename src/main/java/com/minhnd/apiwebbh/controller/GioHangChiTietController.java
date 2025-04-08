package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.GioHangChiTietDTO;
import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gio-hang-chi-tiet")
@RequiredArgsConstructor
public class GioHangChiTietController {
    private final GioHangChiTietService gioHangChiTietService;

    @GetMapping
    public ResponseDTO<List<GioHangChiTietDTO>> getByGioHangId(@RequestParam Long gioHangId) {
        return ResponseDTO.<List<GioHangChiTietDTO>>builder()
                .status(200)
                .data(gioHangChiTietService.getByGioHangId(gioHangId))
                .build();
    }
    @PostMapping
    public ResponseDTO<String> create(@RequestBody GioHangChiTietDTO dto) {
        return ResponseDTO.<String>builder()
                .status(201)
                .message(gioHangChiTietService.themVaoGioHang(dto))
                .build();
    }
    @PutMapping("/{id}")
    public ResponseDTO<String> update(@RequestParam Long soLuong, @PathVariable Long id) {
        return ResponseDTO.<String>builder()
                .message(gioHangChiTietService.update(soLuong,id))
                .status(200)
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseDTO<String> delete(@PathVariable Long id) {
        return ResponseDTO.<String>builder()
                .message(gioHangChiTietService.xoaKhoiGioHang(id))
                .status(200)
                .build();
    }
}
