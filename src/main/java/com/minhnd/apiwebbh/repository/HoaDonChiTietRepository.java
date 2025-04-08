package com.minhnd.apiwebbh.repository;

import com.minhnd.apiwebbh.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    Optional<List<HoaDonChiTiet>> findByHoaDonId(Long id);
    Optional<HoaDonChiTiet> findBySanPhamChiTietIdAndHoaDonId(Long sanPhamId, Long hoaDonId);

}
