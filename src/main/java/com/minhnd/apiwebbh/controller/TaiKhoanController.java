package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.TaiKhoanDTO;
import com.minhnd.apiwebbh.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;
    @PostMapping("/register")
    public ResponseDTO<String> dangKy(@RequestBody TaiKhoanDTO dto) {
        return ResponseDTO.<String>builder()
                .message(taiKhoanService.dangKy(dto))
                .status(200)
                .build();
    }
    @PostMapping("/login")
    public  ResponseDTO<String> login(@RequestBody TaiKhoanDTO dto) {
        return ResponseDTO.<String>builder()
                .message(taiKhoanService.dangNhap(dto))
                .status(200)
                .build();
    }
}
