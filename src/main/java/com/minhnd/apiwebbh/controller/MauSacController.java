package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.MauSacDTO;
import com.minhnd.apiwebbh.service.MauSacService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mau-sac")
public class MauSacController {
    private final MauSacService mauSacService;
    @GetMapping
    public List<MauSacDTO> getAll() {
        return mauSacService.getAll();
    }
    @GetMapping("/{id}")
    public MauSacDTO findById(@PathVariable Long id) {
       return mauSacService.getById(id);
    }
    @PostMapping
    public void create(@RequestBody MauSacDTO mauSacDTO) {
        mauSacService.create(mauSacDTO);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody MauSacDTO mauSacDTO) {
        mauSacService.create(mauSacDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mauSacService.delete(id);
    }
}
