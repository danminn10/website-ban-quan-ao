package com.minhnd.apiwebbh.service;

import com.minhnd.apiwebbh.dto.KichCoDTO;

import java.util.List;

public interface KichCoService {
    List<KichCoDTO> getAll();
    KichCoDTO getById(Long id);
    void create(KichCoDTO dto);
    void update(KichCoDTO kichCoDTO, Long id);
    void delete(Long id);
}