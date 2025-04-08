package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.dto.KichCoDTO;
import com.minhnd.apiwebbh.dto.MauSacDTO;
import com.minhnd.apiwebbh.entity.MauSac;
import com.minhnd.apiwebbh.repository.MauSacRepository;
import com.minhnd.apiwebbh.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MauSacServiceImpl implements MauSacService {
    private final MauSacRepository mauSacRepo;
    public MauSacServiceImpl(MauSacRepository mauSacRepo) {
        this.mauSacRepo = mauSacRepo;
    }
    @Override
    public List<MauSacDTO> getAll() {
        List<MauSac> listEntity = mauSacRepo.findAll();
        List<MauSacDTO> mauSacDTOList = new ArrayList<MauSacDTO>();
        mapToListDTO(listEntity, mauSacDTOList);
        return mauSacDTOList;
    }

    @Override
    public void create(MauSacDTO dto) {
        MauSac mauSac = new MauSac();
        mauSac.setTen(dto.getTen());
        mauSacRepo.save(mauSac);
    }

    @Override
    public MauSacDTO getById(Long id) {
        MauSac mauSac = mauSacRepo.findById(id).orElse(null);
        MauSacDTO dto = new MauSacDTO();
        dto.setId(mauSac.getId());
        dto.setTen(mauSac.getTen());
        return dto;
    }

    @Override
    public void update(MauSacDTO mauSacDTO, Long id) {
        MauSac mauSac = mauSacRepo.findById(id).orElse(null);
        if (mauSac != null) {
            mauSac.setTen(mauSacDTO.getTen());
            mauSacRepo.save(mauSac);
        }
        else {
            throw new RuntimeException("MauSac not found");
        }
    }

    @Override
    public void delete(Long id) {
        mauSacRepo.deleteById(id);
    }

    private void mapToListDTO(List<MauSac> entities, List<MauSacDTO> dtos){
        for (MauSac entity : entities){
            MauSacDTO dto = new MauSacDTO();
            dto.setId(entity.getId());
            dto.setTen(entity.getTen());
            dtos.add(dto);
        }
    }
}
