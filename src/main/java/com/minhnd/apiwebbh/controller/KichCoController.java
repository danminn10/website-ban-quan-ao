package com.minhnd.apiwebbh.controller;

import com.minhnd.apiwebbh.dto.KichCoDTO;
import com.minhnd.apiwebbh.entity.KichCo;
import com.minhnd.apiwebbh.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kich-co")
public class KichCoController {
    private final KichCoService kichCoService;
    @GetMapping
    public ResponseEntity<List<KichCoDTO>> getAll() {
        return ResponseEntity.ok().body(kichCoService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<KichCoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(kichCoService.getById(id));
    }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody KichCoDTO dto) {
        kichCoService.create(dto);
        return ResponseEntity.ok().body("KichCo created");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody KichCoDTO dto) {
        kichCoService.update(dto, id);
        return ResponseEntity.ok().body("KichCo updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        kichCoService.delete(id);
        return ResponseEntity.ok().body("KichCo deleted");
    }
}
