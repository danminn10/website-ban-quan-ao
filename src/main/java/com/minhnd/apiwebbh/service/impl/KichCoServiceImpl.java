package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.KichCoDTO;
import com.minhnd.apiwebbh.entity.KichCo;
import com.minhnd.apiwebbh.repository.KichCoRepository;
import com.minhnd.apiwebbh.service.KichCoService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KichCoServiceImpl implements KichCoService {
    private final KichCoRepository kichCoRepo;
//    public KichCoServiceImpl(KichCoRepository kichCoRepository) {
//        this.kichCoRepository = kichCoRepository;
//    }
    @Override
    public List<KichCoDTO> getAll() {
        List<KichCo> listEntity = kichCoRepo.findAll();
        List<KichCoDTO> listDTO = new ArrayList<>();
        mapToListDTO(listEntity, listDTO);
        return  listDTO;
    }

    @Override
    public KichCoDTO getById(Long id) {
        KichCo kichCo = kichCoRepo.findById(id).orElse(null);
        KichCoDTO kichCoDTO = new KichCoDTO();
        kichCoDTO.setId(kichCo.getId());
        kichCoDTO.setTen(kichCo.getTen());
        return kichCoDTO;
    }

    @Override
    public void create(KichCoDTO dto) {
        KichCo kichCo = new KichCo();
        kichCo.setTen(dto.getTen());
        kichCoRepo.save(kichCo);
    }

    @Override
    public void update(KichCoDTO kichCoDTO, Long id) {
        KichCo kichCo = kichCoRepo.findById(id).orElse(null);
        if (kichCo != null) {
            kichCo.setTen(kichCoDTO.getTen());
            kichCoRepo.save(kichCo);

        }
        else {
            throw new RuntimeException("KichCo not found");
        }
    }

    @Override
    public void delete(Long id) {
        kichCoRepo.deleteById(id);
    }

    public void mapToListDTO(List<KichCo> entities,  List<KichCoDTO> dtos) {
        for (KichCo entity : entities) {
            KichCoDTO dto = new KichCoDTO();
            dto.setId(entity.getId());
            dto.setTen(entity.getTen());
            dtos.add(dto);
        }
    }
}
