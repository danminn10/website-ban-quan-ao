package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.LoaiSanPhamDTO;
import com.minhnd.apiwebbh.dto.ResponseDTO;
import com.minhnd.apiwebbh.dto.SanPhamChiTietDTO;
import com.minhnd.apiwebbh.dto.SanPhamDTO;
import com.minhnd.apiwebbh.entity.SanPham;
import com.minhnd.apiwebbh.entity.SanPhamChiTiet;
import com.minhnd.apiwebbh.exception.CustomHandlerException;
import com.minhnd.apiwebbh.repository.SanPhamChiTietRepository;
import com.minhnd.apiwebbh.repository.SanPhamRepository;
import com.minhnd.apiwebbh.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.codec.ServerSentEvent.builder;


@Service
@RequiredArgsConstructor
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {
    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    private final SanPhamRepository sanPhamRepo;
    @Override
    public ResponseDTO<List<SanPhamChiTietDTO>> getAll(Integer page, Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 5 : size;
        Page<SanPhamChiTiet> pageElement = sanPhamChiTietRepo.findAll(PageRequest.of(page, size));
        List<SanPhamChiTietDTO> sanPhamChiTietDTO = mapToListDTO(pageElement.getContent());
        return ResponseDTO
                .<List<SanPhamChiTietDTO>>builder()
                .data(sanPhamChiTietDTO)
                .totalPage(pageElement.getTotalPages())
                .totalElement((int) pageElement.getTotalElements())
                .message("getAll seccessfully")
                .status(200)
                .build();

    }


    @Override
    public List<SanPhamChiTietDTO> getBySpId(Long sanPhamId) {
        List<SanPhamChiTiet> list = sanPhamChiTietRepo.findBySanPhamId(sanPhamId);
        return mapToListDTO(list);
    }

    @Override
    public SanPhamChiTietDTO getById(Long id) {
        return null;
    }

    @Override
    public void create(SanPhamChiTietDTO dto) {
        SanPhamChiTiet entity = new ModelMapper().map(dto, SanPhamChiTiet.class);
        SanPham sanPham = sanPhamRepo.findById(dto.getSanPham().getId())
                .orElseThrow(() -> new CustomHandlerException("SanPham not found"));
        entity.setSanPham(sanPham);
        entity.setTen(sanPham.getTen());
        sanPham.setGia(sanPham.getGia() > entity.getGia() ? entity.getGia() : sanPham.getGia());
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() + entity.getSoLuongTonKho());
        sanPhamRepo.save(sanPham);
        sanPhamChiTietRepo.save(entity);
    }

    @Override
    public void update(Long id, SanPhamChiTietDTO dto) {
        SanPhamChiTiet entity = sanPhamChiTietRepo.findById(id)
                .orElseThrow(() -> new CustomHandlerException("SanPham not found"));
        SanPham sanPham = entity.getSanPham();
        entity.setTen(sanPham.getTen());
        sanPham.setGia(sanPham.getGia() > entity.getGia() ? entity.getGia() : sanPham.getGia());
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() + entity.getSoLuongTonKho());
        sanPhamRepo.save(sanPham);
        sanPhamChiTietRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        // nếu sản phẩm chi tiết id này là sản phẩm chi tiêt cuối cùng của sản phẩm thì sẽ set lại trạng thái của sản phẩm
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepo.findById(id)
                .orElseThrow(() -> new CustomHandlerException("SanPham not found"));
        List<SanPhamChiTiet> list = sanPhamChiTietRepo.findBySanPhamId(sanPhamChiTiet.getSanPham().getId());
        if (list.size() == 1) {
            SanPham sanPham = sanPhamChiTiet.getSanPham();
            sanPham.setTrangThai(0);
            sanPhamRepo.save(sanPham);
        }
        sanPhamChiTietRepo.deleteById(id);
    }

    public List<SanPhamChiTietDTO> mapToListDTO (List<SanPhamChiTiet> entities){
        List<SanPhamChiTietDTO> listDTO = new ArrayList<>();
        for (SanPhamChiTiet entity : entities) {
            SanPhamChiTietDTO entityDTO = new ModelMapper().map(entity, SanPhamChiTietDTO.class);
            listDTO.add(entityDTO);
        }
        return listDTO;
    }


}
