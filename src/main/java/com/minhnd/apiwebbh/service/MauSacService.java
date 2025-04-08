package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.KichCoDTO;
import com.minhnd.apiwebbh.dto.MauSacDTO;
import com.minhnd.apiwebbh.entity.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSacDTO> getAll();
    void create(MauSacDTO mauSac);
    MauSacDTO getById(Long id);
    void update(MauSacDTO mauSacDTO, Long id);
    void delete(Long id);

}
