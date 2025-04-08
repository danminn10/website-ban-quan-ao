package com.minhnd.apiwebbh.repository;

import com.minhnd.apiwebbh.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    Optional<GioHang> findByTaiKhoanId(Long taiKhoanId);
}
