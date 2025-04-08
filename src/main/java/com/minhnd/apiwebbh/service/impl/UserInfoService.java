package com.minhnd.apiwebbh.service.impl;

import com.minhnd.apiwebbh.entity.TaiKhoan;
import com.minhnd.apiwebbh.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepo.findBySoDienThoai(username)
                .orElseThrow(() -> new UsernameNotFoundException("khong tim thay tai khoan co so dien thoai " + username));
        return taiKhoan;
    }
}
