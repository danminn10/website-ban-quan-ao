package com.minhnd.apiwebbh.repository;

import com.minhnd.apiwebbh.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    //@Query(nativeQuery = true, value = "select * from SanPhamChiTiet where san_pham_id = :sanPhamId")
    @Query(value = "select s from SanPhamChiTiet s where s.sanPham.id = :sanPhamId")
    List<SanPhamChiTiet> findBySanPhamId(@Param("sanPhamId") Long sanPhamId);

    Optional<SanPhamChiTiet> findFirstByMa(String ma);
}
