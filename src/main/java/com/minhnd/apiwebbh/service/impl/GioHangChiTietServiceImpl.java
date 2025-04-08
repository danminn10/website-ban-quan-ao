package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.GioHangChiTietDTO;
import com.minhnd.apiwebbh.entity.GioHang;
import com.minhnd.apiwebbh.entity.GioHangChiTiet;
import com.minhnd.apiwebbh.entity.SanPhamChiTiet;
import com.minhnd.apiwebbh.repository.GioHangChiTietRepository;
import com.minhnd.apiwebbh.repository.GioHangRepository;
import com.minhnd.apiwebbh.repository.SanPhamChiTietRepository;
import com.minhnd.apiwebbh.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    private final GioHangChiTietRepository gioHangChiTietRepo;
    private final GioHangRepository gioHangRepo;
    private final SanPhamChiTietRepository spChiTietRepo;

    @Override
    public List<GioHangChiTietDTO> getByGioHangId(Long gioHangId) {
        return gioHangChiTietRepo.findByGioHangId(gioHangId)
                .stream().map(X -> new ModelMapper().map(X,GioHangChiTietDTO.class)).collect(Collectors.toList());
    }

    @Override
    public String themVaoGioHang(GioHangChiTietDTO dto) {
        GioHangChiTiet entity = new GioHangChiTiet();
        GioHang gioHang = gioHangRepo.findById(dto.getGioHang().getId())
                .orElseThrow(() -> new RuntimeException("gio hang not found"));
        entity.setGioHang(gioHang);
        SanPhamChiTiet sanPhamChiTiet = spChiTietRepo.findById(dto.getSanPhamChiTiet().getId())
                .orElseThrow(() -> new RuntimeException("san pham chi tiet not found"));
        entity.setSanPhamChiTiet(sanPhamChiTiet);

        GioHangChiTiet isExists = gioHangChiTietRepo.findByGioHangIdAndSanPhamChiTietId(dto.getGioHang().getId(),dto.getSanPhamChiTiet().getId())
                .orElse(null);
        if (isExists != null) {
            entity.setId(isExists.getId());
            entity.setSoLuong(isExists.getSoLuong() + dto.getSoLuong());
        } else {
            entity.setSoLuong(dto.getSoLuong());
            gioHang.setTongSoSanPham(gioHang.getTongSoSanPham() + 1);
            gioHangRepo.save(gioHang);
        }
        gioHangChiTietRepo.save(entity);
        return "them san pham vao gio hang thanh cong";
    }

    @Override
    public String update(Long soLuong, Long id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("gio hang not found"));
        gioHangChiTiet.setSoLuong(soLuong);
        gioHangChiTietRepo.save(gioHangChiTiet);
        return "sua san pham thanh cong";
    }

    @Override
    public String xoaKhoiGioHang(Long id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("gio hang not found"));
        GioHang gioHang = gioHangChiTiet.getGioHang();
        gioHang.setTongSoSanPham(gioHang.getTongSoSanPham() - 1);
        gioHangRepo.save(gioHang);
        gioHangChiTietRepo.deleteById(gioHangChiTiet.getId());
        return "xoa thanh cong";
    }
}
