package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.ThongTinDatHangDTO;
import com.minhnd.apiwebbh.dto.ThongTinHoaDonDTO;

public interface DatHangOnlineService {
    String datHang(ThongTinDatHangDTO dto);
    String updateTrangThai(Long id, Integer trangThai);
    ThongTinHoaDonDTO getById(Long id);
}
