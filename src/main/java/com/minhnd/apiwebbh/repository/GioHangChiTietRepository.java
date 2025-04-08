package com.minhnd.apiwebbh.repository;

import com.minhnd.apiwebbh.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
    Optional<GioHangChiTiet> findByGioHangIdAndSanPhamChiTietId(Long id, Long id1);
    List<GioHangChiTiet> findByGioHangId(Long id);
}
