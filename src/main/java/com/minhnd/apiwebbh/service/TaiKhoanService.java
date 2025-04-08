package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.TaiKhoanDTO;

public interface TaiKhoanService {
    String dangNhap(TaiKhoanDTO taiKhoanDTO);
    String dangKy(TaiKhoanDTO taiKhoanDTO);
}
