package com.minhnd.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HoaDon {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String maHoaDon;
    private Long tongSoSanPham;
    private Long tongSoTien;
    private String soDienThoai;
    private String diaChi;
    private String hoVaTen;
    private String maNhanVien;
    private LocalDate ngayTao; // khi đơn hàng được đặt sẽ có ngày tạo
    private LocalDate ngayHoanThanh;
    private String lyDoHuy;
    private Integer trangThai; /*   0 là Chờ xác nhận: đơn hàng đang trong giai đoạn xác nhận tính hợp lệ bởi hệ thống
                                    1 Chờ lấy hàng: đơn hàng đã được chuyển thông tin tới Người bán để giao cho đơn vị vận chuyển
                                    2 Đang giao: đơn hàng đang được giao tới Người mua
                                    3 Đánh giá: đơn hàng đang chờ được đánh giá sản phẩm
                                    4 Đã giao: đơn hàng đã được giao thành công tới Người mua
                                    5 Đã hủy: đơn hàng đã được hủy thành công
                                    6 Trả hàng: đơn hàng đã được trả hàng thành công*/
    private Integer loaiHoaDon; // 2 thanh toán onl (ck )  và 1 thanh toán tại quầy ( tiền mặt )

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;
    /*
    hoa_don: id bigint (khóa chính), ma_hoa_don varchar(20), tong_so_san_pham bigint,
    tong_so_tien bigint, so_dien_thoai varchar(12), dia_chi varchar(255),
    ho_va_ten varchar(100), ma_nhan_vien varchar(10), ngay_tao date,
    ngay_hoan_thanh date, ly_do_huy varchar(255), trang_thai int, loai_hoa_don int,
    trang_thai int, tai_khoan_id bigint (khóa phụ)*/
}
