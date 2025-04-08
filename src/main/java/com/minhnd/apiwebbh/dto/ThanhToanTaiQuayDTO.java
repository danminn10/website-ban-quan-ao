package com.minhnd.apiwebbh.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThanhToanTaiQuayDTO {
    Long hoaDonId;
    String soDienThoai;
}
