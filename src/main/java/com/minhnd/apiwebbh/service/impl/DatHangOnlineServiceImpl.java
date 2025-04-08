package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.HoaDonChiTietDTO;
import com.minhnd.apiwebbh.dto.HoaDonDTO;
import com.minhnd.apiwebbh.dto.ThongTinDatHangDTO;
import com.minhnd.apiwebbh.dto.ThongTinHoaDonDTO;
import com.minhnd.apiwebbh.entity.*;
import com.minhnd.apiwebbh.repository.*;
import com.minhnd.apiwebbh.service.DatHangOnlineService;
import com.minhnd.apiwebbh.service.GioHangChiTietService;
import com.minhnd.apiwebbh.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatHangOnlineServiceImpl implements DatHangOnlineService {
    private final HoaDonRepository hoaDonRepo;
    private final HoaDonChiTietRepository hoaDonChiTietRepo;
    private final TaiKhoanRepository taiKhoanRepo;
    private final GioHangRepository gioHangRepo;
    private final GioHangChiTietRepository gioHangChiTietRepo;
    private final SanPhamRepository sanPhamRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;

    @Override
    public String datHang(ThongTinDatHangDTO dto) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setSoDienThoai(dto.getSoDienThoai());
        hoaDon.setDiaChi(dto.getDiaChi());
        hoaDon.setHoVaTen(dto.getHoVaTen());
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setTrangThai(1);
        hoaDon.setTrangThai(2);

        TaiKhoan taiKhoan = taiKhoanRepo.findById(dto.getTaiKhoanId())
                .orElseThrow(() -> new RuntimeException("tai khoan not found"));
        hoaDon.setTaiKhoan(taiKhoan);

        GioHang gioHang = gioHangRepo.findByTaiKhoanId(dto.getTaiKhoanId()).get();
        hoaDon.setTongSoSanPham(gioHang.getTongSoSanPham());
        hoaDon.setTongSoTien(gioHang.getTongSoTien());
        hoaDon = hoaDonRepo.save(hoaDon);

        List<GioHangChiTiet> lgh = gioHangChiTietRepo.findByGioHangId(gioHang.getId());
        List<HoaDonChiTiet> lhd = new ArrayList<>();
        for (GioHangChiTiet x : lgh) {
            //set lai spct
            SanPhamChiTiet spct = x.getSanPhamChiTiet();
            spct.setSoLuongDaBan(spct.getSoLuongDaBan() + x.getSoLuong());
            spct.setSoLuongTonKho(spct.getSoLuongTonKho() - x.getSoLuong());
            sanPhamChiTietRepo.save(spct);
            //set lai sp
            SanPham sp = spct.getSanPham();
            sp.setSoLuongDaBan(sp.getSoLuongDaBan() + x.getSoLuong());
            sp.setSoLuongTonKho(sp.getSoLuongTonKho() - x.getSoLuong());
            sanPhamRepo.save(sp);
            HoaDonChiTiet hd = HoaDonChiTiet.builder()
                    .donGia(x.getSanPhamChiTiet().getGia())
                    .soLuong(x.getSoLuong())
                    .sanPhamChiTiet(x.getSanPhamChiTiet())
                    .thanhTien(x.getSoLuong() * x.getSanPhamChiTiet().getGia())
                    .hoaDon(hoaDon)
                    .build();
            lhd.add(hd);
            gioHangChiTietRepo.deleteById(x.getId());
        }
        gioHang.setTongSoTien(0l);
        gioHang.setTongSoSanPham(0l);
        hoaDonChiTietRepo.saveAll(lhd);
        return "dat hang thanh cong";
    }

    @Override
    public String updateTrangThai(Long id, Integer trangThai) {
        // 0 đã huy, 1 đang chờ, 2 là chờ lấy hàng, 3 đang giao hàng, 4 : Đã hoàn thành
        checkStatus(id, trangThai);
        return "Cap nhat thanh cong";
    }

    @Override
    public ThongTinHoaDonDTO getById(Long id) {
        HoaDon hoaDon = hoaDonRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("hoa don not found"));
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepo.findByHoaDonId(id)
                .orElseThrow(() -> new RuntimeException("hoa don not found"));
        ThongTinHoaDonDTO thongTinHoaDonDTO = new ThongTinHoaDonDTO();
        // map to dto
        mapToDto(hoaDon,hoaDonChiTietList, thongTinHoaDonDTO);
        return null;
    }

    private void mapToDto(HoaDon hoaDon, List<HoaDonChiTiet> hoaDonChiTietList, ThongTinHoaDonDTO thongTinHoaDonDTO) {
        HoaDonDTO hoaDonDTO = new ModelMapper().map(hoaDon, HoaDonDTO.class);
        thongTinHoaDonDTO.setThongTinHoaDon(hoaDonDTO);
        List<HoaDonChiTietDTO> listHdctDTO = new ArrayList<>();
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietList) {
            HoaDonChiTietDTO hd = new ModelMapper().map(hoaDonChiTiet, HoaDonChiTietDTO.class);
            listHdctDTO.add(hd);
        }
        thongTinHoaDonDTO.setHoaDonChiTietList(listHdctDTO);

    }

    public void checkStatus(Long id, Integer trangThai) {
        if (trangThai != 0) {
            HoaDon hoaDon = hoaDonRepo.findByIdAndTrangThai(id, trangThai - 1)
                    .orElseThrow(() -> new RuntimeException("trang thai not found"));
            hoaDon.setTrangThai(trangThai);
            if (trangThai == 4){
                hoaDon.setNgayHoanThanh(LocalDate.now());
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTongHoaDon(taiKhoan.getTongHoaDon() + 1);
                taiKhoan.setTongTien(taiKhoan.getTongTien() + hoaDon.getTongSoTien());
                if (taiKhoan.getTongHoaDon() > 25 && taiKhoan.getTongTien() > 10000000)
                    taiKhoan.setHangTaiKhoan(2);
                taiKhoanRepo.save(taiKhoan);
            }
            hoaDonRepo.save(hoaDon);
        } else {
            huyHoaDon(id);
        }
    }
    private void huyHoaDon(Long id) {
        HoaDon hoaDon = hoaDonRepo.findById(id).orElseThrow(() -> new RuntimeException("hoa don not found"));
        hoaDon.setTrangThai(0);
        hoaDonRepo.save(hoaDon);
        List<HoaDonChiTiet> chiTiets = hoaDonChiTietRepo.findByHoaDonId(id).orElse(new ArrayList<>());
        for (HoaDonChiTiet x : chiTiets) {
            SanPhamChiTiet spct = x.getSanPhamChiTiet();
            spct.setSoLuongDaBan(spct.getSoLuongDaBan() - x.getSoLuong());
            spct.setSoLuongTonKho(spct.getSoLuongTonKho() + x.getSoLuong());
            sanPhamChiTietRepo.save(spct);
            SanPham sp = spct.getSanPham();
            sp.setSoLuongDaBan(sp.getSoLuongDaBan() - x.getSoLuong());
            sp.setSoLuongTonKho(sp.getSoLuongTonKho() + x.getSoLuong());
            sanPhamRepo.save(sp);
        }

    }
}
