package com.minhnd.apiwebbh.dto;

import com.minhnd.apiwebbh.entity.HoaDonChiTiet;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongTinHoaDonDTO {
    HoaDonDTO thongTinHoaDon;
    List<HoaDonChiTietDTO> hoaDonChiTietList;
}
