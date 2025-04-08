package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.HoaDonChiTietTaiQuayDTO;
import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.ThanhToanTaiQuayDTO;
import com.minhnd.apiwebbh.service.MuaHangTaiQuayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ban-hang-tai-quay")
@RequiredArgsConstructor
public class MuaHangTaiQuayController {
    private final MuaHangTaiQuayService muaHangTaiQuayService;
    @PostMapping("/tao-hoa-don")
    public ResponseDTO<String> create(){
        return muaHangTaiQuayService.taoHoaDon();
    }
    @PostMapping("/them-san-pham")
    public ResponseDTO<String> addToCard(@RequestBody HoaDonChiTietTaiQuayDTO hoaDonChiTietTaiQuayDTO){
        return muaHangTaiQuayService.themSanPhamVaoGioHang(hoaDonChiTietTaiQuayDTO);
    }
    @PostMapping("/them-san-pham-bang-qr/{maSanPham}/{idHoaDon}")
    public ResponseDTO<String> addToByteQRCode(@PathVariable String maSanPham, @PathVariable Long idHoaDon){
        return muaHangTaiQuayService.themSanPhamBangQR(maSanPham,idHoaDon);
    }
    @PostMapping("/thanh-toan")
    public ResponseDTO<String> checkOut(@RequestBody ThanhToanTaiQuayDTO tttq){
        return muaHangTaiQuayService.thanhToanHoaDon(tttq);
    }
}
