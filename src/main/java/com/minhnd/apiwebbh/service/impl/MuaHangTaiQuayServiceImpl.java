package com.minhnd.apiwebbh.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.minhnd.apiwebbh.dto.HoaDonChiTietTaiQuayDTO;
import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.ThanhToanTaiQuayDTO;
import com.minhnd.apiwebbh.entity.*;
import com.minhnd.apiwebbh.repository.*;
import com.minhnd.apiwebbh.service.MuaHangTaiQuayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MuaHangTaiQuayServiceImpl implements MuaHangTaiQuayService {
    private final HoaDonChiTietRepository hoaDonChiTietRepo;
    private final HoaDonRepository hoaDonRepo;
    private final TaiKhoanRepository taiKhoanRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    private final SanPhamRepository sanPhamRepo;
    @Override
    public ResponseDTO<String> taoHoaDon() {
        HoaDon hoaDon = HoaDon.builder()
                .loaiHoaDon(1)
                .ngayTao(LocalDate.now())
                .tongSoTien(0l)
                .tongSoSanPham(0l)
                .trangThai(1)
                .build();
        hoaDonRepo.save(hoaDon);
        return ResponseDTO.<String>builder()
                .status(200)
                .message("Tao hoa don thanh cong")
                .build();
    }

    @Override
    public ResponseDTO<String> themSanPhamVaoGioHang(HoaDonChiTietTaiQuayDTO hdcttq) {
        HoaDon hoaDon = hoaDonRepo.findById(hdcttq.getHoaDonId())
                .orElseThrow(()-> new RuntimeException("Hoa don not found"));
        SanPhamChiTiet spct = sanPhamChiTietRepo.findById(hdcttq.getSanPhamChiTietId())
                .orElseThrow(()-> new RuntimeException("SanPhamChiTiet not found"));
        // soluong sp > soLuongThem
        HoaDonChiTiet hdct = hoaDonChiTietRepo.findBySanPhamChiTietIdAndHoaDonId(hdcttq.getSanPhamChiTietId(),hdcttq.getHoaDonId())
                .orElse(new HoaDonChiTiet());
        if (hdct.getId() == null){
            hdct.setSanPhamChiTiet(spct);
            hdct.setHoaDon(hoaDon);
            hdct.setSoLuong(hdcttq.getSoLuong());
            hdct.setDonGia(spct.getGia());
            hdct.setThanhTien(spct.getGia() * hdct.getSoLuong());
            hoaDon.setTongSoSanPham(hoaDon.getTongSoSanPham() + 1);
            hoaDon.setTongSoTien(hoaDon.getTongSoTien() + hdct.getThanhTien());
        }else {
            hdct.setSoLuong(hdct.getSoLuong() + hdcttq.getSoLuong());
            hdct.setThanhTien(hdct.getThanhTien() + spct.getGia() * hdcttq.getSoLuong());
        }

        spct.setSoLuongTonKho(spct.getSoLuongTonKho() - hdcttq.getSoLuong());
        spct.setSoLuongDaBan(spct.getSoLuongDaBan() + 1);
        hoaDonRepo.save(hoaDon);
        hoaDonChiTietRepo.save(hdct);
        sanPhamChiTietRepo.save(spct);
        return ResponseDTO.<String>builder()
                .status(200)
                .message("Them san pham vao gio hang thanh cong")
                .build();
    }

    @Override
    public ResponseDTO<String> themSanPhamBangQR(String maSpct, Long hoaDonId) {
       SanPhamChiTiet spct = sanPhamChiTietRepo.findFirstByMa(maSpct)
                .orElseThrow(()-> new RuntimeException("SanPhamChiTiet not found"));
        HoaDon hd = hoaDonRepo.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Hoa don not found"));
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findById(hoaDonId)
                .orElse(new HoaDonChiTiet());
        if (hoaDonChiTiet.getId() == null){
            hoaDonChiTiet.setHoaDon(hd);
            hoaDonChiTiet.setSanPhamChiTiet(spct);
            hoaDonChiTiet.setSoLuong(1L);  // Thêm 1 sản phẩm
            hoaDonChiTiet.setDonGia(spct.getGia());
            hoaDonChiTiet.setThanhTien(spct.getGia());
            // Cập nhật số lượng sản phẩm và tổng tiền trong hóa đơn
            hd.setTongSoSanPham(hd.getTongSoSanPham() + 1);
            hd.setTongSoTien(hd.getTongSoTien() + hoaDonChiTiet.getThanhTien());
        } else {
            // Nếu sản phẩm đã có trong chi tiết hóa đơn, cập nhật số lượng và thành tiền
            // Cập nhật tổng số tiền trong hóa đơn
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + 1);
            hoaDonChiTiet.setThanhTien(hoaDonChiTiet.getThanhTien() + spct.getGia() * hoaDonChiTiet.getSoLuong());
            hd.setTongSoTien(hd.getTongSoTien() + spct.getGia());
        }
        spct.setSoLuongTonKho(spct.getSoLuongTonKho() - 1);
        spct.setSoLuongDaBan(spct.getSoLuongDaBan() + 1);

        hoaDonRepo.save(hd);
        hoaDonChiTietRepo.save(hoaDonChiTiet);
        sanPhamChiTietRepo.save(spct);
        return ResponseDTO.<String>builder()
                .message("them thanh cong")
                .status(200)
                .build();
    }

    @Override
    public ResponseDTO<String> thanhToanHoaDon(ThanhToanTaiQuayDTO tthdtqDto) {
        HoaDon hoaDon = hoaDonRepo.findById(tthdtqDto.getHoaDonId())
                .orElseThrow(() -> new RuntimeException("Hoa don not found"));
        TaiKhoan taiKhoan = new TaiKhoan();
        if (tthdtqDto.getSoDienThoai() != null){
            taiKhoan = taiKhoanRepo.findBySoDienThoai(tthdtqDto.getSoDienThoai())
                    .orElse(new TaiKhoan());
            if (taiKhoan.getId() != null){
                taiKhoan.setTongHoaDon(taiKhoan.getTongHoaDon() + 1);
                taiKhoan.setTongTien(taiKhoan.getTongTien() + hoaDon.getTongSoTien());
                Integer hangTaiKhoan = (taiKhoan.getTongHoaDon() > 25 && taiKhoan.getTongTien() > 10000000) ? 2 : 1;
                taiKhoan.setHangTaiKhoan(hangTaiKhoan);
            } else {
                taiKhoan.setHangTaiKhoan(1);
                taiKhoan.setTongHoaDon(1l);
                taiKhoan.setTongTien(hoaDon.getTongSoTien());
                taiKhoan.setRole(Role.KHACHHANG);
                taiKhoan.setSoDienThoai(tthdtqDto.getSoDienThoai());
                taiKhoan.setTrangThai(1);
            }
            taiKhoan = taiKhoanRepo.save(taiKhoan);
        }
        hoaDon.setTrangThai(4);
        hoaDon.setSoDienThoai(taiKhoan.getSoDienThoai());
        hoaDon.setTaiKhoan(taiKhoan);
        hoaDon.setNgayHoanThanh(LocalDate.now());
        hoaDonRepo.save(hoaDon);
        return ResponseDTO.<String>builder()
                .message("thanh toan thanh cong")
                .status(200)
                .build();
    }

    @Override
    public ResponseDTO<String> xoaSanPhamKhoiGioHang(Long id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("hoa don chi tiet not found"));
        HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
        hoaDon.setTongSoSanPham(hoaDon.getTongSoSanPham() - 1);
        hoaDon.setTongSoTien(hoaDon.getTongSoTien() - hoaDonChiTiet.getThanhTien());
        hoaDonRepo.save(hoaDon);

        SanPhamChiTiet spct = hoaDonChiTiet.getSanPhamChiTiet();
        SanPham sp = spct.getSanPham();


        spct.setSoLuongTonKho(spct.getSoLuongTonKho() + hoaDonChiTiet.getSoLuong());
        spct.setSoLuongDaBan(spct.getSoLuongDaBan() - hoaDonChiTiet.getSoLuong());

        sp.setSoLuongDaBan(sp.getSoLuongDaBan() - hoaDonChiTiet.getSoLuong());
        sp.setSoLuongTonKho(sp.getSoLuongTonKho() + hoaDonChiTiet.getSoLuong());
        sanPhamChiTietRepo.save(spct);
        sanPhamRepo.save(sp);
        return ResponseDTO.<String>builder()
                .message("xoa thanh cong")
                .status(200)
                .build();
    }

    @Override
    public ResponseDTO<String> capNhatSoLuong(Long soLuongMoi, Long id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("hoa don chi tiet not found"));

        Long soLuongCu = hoaDonChiTiet.getSoLuong();
        Long thanhTienCu = hoaDonChiTiet.getThanhTien();
        Long thanhTienMoi = soLuongMoi * hoaDonChiTiet.getDonGia();

        hoaDonChiTiet.setSoLuong(soLuongMoi);
        hoaDonChiTiet.setThanhTien(soLuongMoi * hoaDonChiTiet.getDonGia());
        hoaDonChiTietRepo.save(hoaDonChiTiet);

        HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
        hoaDon.setTongSoTien(hoaDon.getTongSoTien() - thanhTienCu + thanhTienMoi);
        hoaDonRepo.save(hoaDon);

        // 5 2 1
        // update spct
        SanPhamChiTiet spct = hoaDonChiTiet.getSanPhamChiTiet();
        spct.setSoLuongTonKho(spct.getSoLuongTonKho() + soLuongCu - soLuongMoi);
        spct.setSoLuongDaBan(spct.getSoLuongDaBan() - soLuongCu + soLuongMoi);
        sanPhamChiTietRepo.save(spct);

        SanPham sp = spct.getSanPham();
        sp.setSoLuongTonKho(sp.getSoLuongTonKho() + soLuongCu - soLuongMoi);
        sp.setSoLuongDaBan(sp.getSoLuongDaBan() - soLuongCu + soLuongMoi);
        sanPhamRepo.save(sp);
        return ResponseDTO.<String>builder()
                .message("sua thanh cong")
                .status(200)
                .build();
    }
}
