package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.LoaiSanPhamDTO;
import com.minhnd.apiwebbh.dto.SanPhamDTO;
import com.minhnd.apiwebbh.entity.LoaiSanPham;
import com.minhnd.apiwebbh.entity.SanPham;
import com.minhnd.apiwebbh.repository.LoaiSanPhamRepository;
import com.minhnd.apiwebbh.repository.SanPhamRepository;
import com.minhnd.apiwebbh.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {
    private final SanPhamRepository sanPhamRepo;
    private final LoaiSanPhamRepository loaiSanPhamRepo;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;
    @Override
    public ResponseEntity<List<SanPhamDTO>> getAll() {
        List<SanPham> sanPham = sanPhamRepo.findAll();
        List<SanPhamDTO> sanPhamDTOS = new ArrayList<>();
        mapToListDTO(sanPham, sanPhamDTOS);
        return ResponseEntity.ok().body(sanPhamDTOS);
    }

    @Override
    public ResponseEntity<List<SanPhamDTO>> filter(String ten, Long loaiSanPhamId) {
        List<SanPham> entity = sanPhamRepo.filter(ten, loaiSanPhamId);
        List<SanPhamDTO> sanPhamDTOS = new ArrayList<>();
        mapToListDTO(entity, sanPhamDTOS);
        return ResponseEntity.ok(sanPhamDTOS);
    }

    public void mapToListDTO(List<SanPham> entities, List<SanPhamDTO> dto) {
        for (SanPham entity : entities) {
            SanPhamDTO dto2 = new SanPhamDTO();
            dto2.setId(entity.getId());
            dto2.setMa(entity.getMa());
            dto2.setTen(entity.getTen());
            dto2.setGia(entity.getGia());
            dto2.setSoLuongTonKho(entity.getSoLuongTonKho());
            dto2.setSoLuongDaBan(entity.getSoLuongDaBan());
            dto2.setMoTa(entity.getMoTa());
            dto2.setTrangThai(entity.getTrangThai());
            LoaiSanPhamDTO loaiSanPhamDTO = new LoaiSanPhamDTO();
            loaiSanPhamDTO.setId(entity.getId());
            loaiSanPhamDTO.setTen(entity.getTen());
            dto2.setLoaiSanPham(loaiSanPhamDTO);
            dto2.setImages(entity.getImages());
            dto.add(dto2);
        }
    }

    @Override
    public ResponseEntity<SanPhamDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> create(SanPhamDTO sanPhamDTO) throws IOException {
        List<String> images = new ArrayList<>();
        if (sanPhamDTO.getFiles() != null){
            images = processFileSaveToCloud(sanPhamDTO.getFiles());
        } else {
            throw new RuntimeException("files is null");
        }
        SanPham sanPham = new SanPham();
        sanPham.setImages(images);
        sanPham.setSoLuongDaBan(0L);
        mapToEntitySave(sanPham,sanPhamDTO);
        sanPhamRepo.save(sanPham);
        return ResponseEntity.ok("them thanh cong");
    }

    @Override
    public ResponseEntity<String> update(SanPhamDTO dto, Long id) throws IOException {
        SanPham sanPham = sanPhamRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("k tim thay san pham"));
        List<String> images;
        if (dto.getFiles() != null){
            images = processFile(dto.getFiles());
            sanPham.setImages(images);
        }
        mapToEntitySave(sanPham,dto);
        sanPhamRepo.save(sanPham);
        return ResponseEntity.ok("sua thanh cong");
    }

    private List<String> processFileSaveToCloud(List<MultipartFile> file) throws IOException {
        List<String> images = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            try {
                String url = cloudinaryServiceImpl.upLoadFiles(multipartFile,"images" );
                if (url == null)
                    throw new RuntimeException("url is null");

                images.add(url);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return images;
    }

    private List<String> processFile(List<MultipartFile> files) throws IOException {
        List<String> images = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            // lay ten file
            String fileName = multipartFile.getOriginalFilename();
            images.add(fileName);
            String path = "D:\\GDU\\tuHoc\\beJava03\\img";
            File folder = new File(path);
            if (!folder.exists()) {
                // tao moi neu k ton tai
                folder.mkdirs();
            }
            File file1 = new File(path +  "/" + fileName);
            multipartFile.transferTo(file1);
        }


        return images;
    }

    private void mapToEntitySave(SanPham entity, SanPhamDTO dto) {
        entity.setMa(dto.getMa());
        entity.setTen(dto.getTen());
        entity.setGia(dto.getGia());
        entity.setSoLuongTonKho(dto.getSoLuongTonKho());
        entity.setSoLuongDaBan(dto.getSoLuongDaBan());
        entity.setMoTa(dto.getMoTa());
        entity.setTrangThai(dto.getTrangThai());

        LoaiSanPham lsp = loaiSanPhamRepo.findById(dto.getLoaiSanPham().getId())
                .orElseThrow(() -> new RuntimeException("k tim thay loai san pham"));
        entity.setLoaiSanPham(lsp);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        return ResponseEntity.ok("xoa thanh cong");
    }
}
