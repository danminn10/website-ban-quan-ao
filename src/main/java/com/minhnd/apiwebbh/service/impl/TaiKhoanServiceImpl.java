package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.TaiKhoanDTO;
import com.minhnd.apiwebbh.entity.GioHang;
import com.minhnd.apiwebbh.entity.Role;
import com.minhnd.apiwebbh.entity.TaiKhoan;
import com.minhnd.apiwebbh.repository.GioHangRepository;
import com.minhnd.apiwebbh.repository.TaiKhoanRepository;
import com.minhnd.apiwebbh.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaiKhoanServiceImpl implements TaiKhoanService {
    private final TaiKhoanRepository taiKhoanRepo;
    private final GioHangRepository gioHangRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String dangNhap(TaiKhoanDTO taiKhoanDTO) {
        return "dang nhap thanh cong";
    }

    @Override
    public String dangKy(TaiKhoanDTO taiKhoanDTO) {
        TaiKhoan taiKhoan = new ModelMapper().map(taiKhoanDTO, TaiKhoan.class);
        taiKhoan.setRole(Role.KHACHHANG);
        taiKhoan.setTongHoaDon(0l);
        taiKhoan.setTongTien(0l);
        taiKhoan.setTrangThai(1);
        taiKhoan.setHangTaiKhoan(1);
        taiKhoan.setSoDienThoai(taiKhoanDTO.getSoDienThoai());
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanDTO.getMatKhau()));
        taiKhoanRepo.save(taiKhoan);
        GioHang gioHang = new GioHang();
        gioHang.setTongSoSanPham(0l);
        gioHang.setTongSoTien(0l);
        gioHang.setTaiKhoan(taiKhoan);
        gioHangRepo.save(gioHang);
        return "dang ky thanh cong";
    }
}
