package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.GioHangChiTietDTO;
import com.minhnd.apiwebbh.entity.GioHangChiTiet;

import java.util.List;

public interface GioHangChiTietService {
    List<GioHangChiTietDTO> getByGioHangId(Long gioHangId);
    String themVaoGioHang(GioHangChiTietDTO dto);
    String update(Long soLuong, Long id);
    String xoaKhoiGioHang(Long id);
}
