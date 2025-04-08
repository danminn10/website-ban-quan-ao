package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.ThongTinDatHangDTO;
import com.minhnd.apiwebbh.dto.ThongTinHoaDonDTO;
import com.minhnd.apiwebbh.service.DatHangOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dat-hang-online")
@RequiredArgsConstructor
public class DatHangOnlineController {
    private final DatHangOnlineService datHangOnlineService;
    @PostMapping
    public ResponseDTO<String> create(@RequestBody ThongTinDatHangDTO dto){
        return ResponseDTO.<String>builder()
                .data(datHangOnlineService.datHang(dto))
                .build();
    }
    @PutMapping("/{id}")
    public ResponseDTO<String> update(@PathVariable Long id, @RequestParam Integer trangThai){
        return ResponseDTO.<String>builder()
                .data(datHangOnlineService.updateTrangThai(id, trangThai))
                .build();
    }
    @GetMapping("/{id}")
    public ResponseDTO<ThongTinHoaDonDTO> get(@PathVariable Long id){
        return ResponseDTO.<ThongTinHoaDonDTO>builder()
                .status(200)
                .data(datHangOnlineService.getById(id))
                .build();
    }
}
