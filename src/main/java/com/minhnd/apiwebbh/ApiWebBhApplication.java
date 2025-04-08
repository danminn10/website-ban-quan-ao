package com.minhnd.apiwebbh;

import com.minhnd.apiwebbh.entity.MauSac;
import com.minhnd.apiwebbh.entity.Role;
import com.minhnd.apiwebbh.entity.TaiKhoan;
import com.minhnd.apiwebbh.repository.MauSacRepository;
import com.minhnd.apiwebbh.service.MauSacService;
import com.minhnd.apiwebbh.service.impl.MauSacServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiWebBhApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ApiWebBhApplication.class, args);
    }

}
