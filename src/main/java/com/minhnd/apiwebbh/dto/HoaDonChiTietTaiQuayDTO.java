package com.minhnd.apiwebbh.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonChiTietTaiQuayDTO {
    Long hoaDonId;
    Long sanPhamChiTietId;
    Long soLuong;
}
