package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.HoaDonChiTietTaiQuayDTO;
import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.ThanhToanTaiQuayDTO;

public interface MuaHangTaiQuayService {
    ResponseDTO<String> taoHoaDon();
    ResponseDTO<String> themSanPhamVaoGioHang(HoaDonChiTietTaiQuayDTO hdcttq);
    ResponseDTO<String> themSanPhamBangQR(String maSpct, Long hoaDonId);
    ResponseDTO<String> thanhToanHoaDon(ThanhToanTaiQuayDTO tthdtqDto);
    ResponseDTO<String> xoaSanPhamKhoiGioHang(Long id);
    ResponseDTO<String> capNhatSoLuong(Long soLuongMoi, Long id);
}
