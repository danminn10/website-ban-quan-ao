package com.minhnd.apiwebbh.service.impl;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl {
    private final Cloudinary cloudinary;
    public String upLoadFiles(MultipartFile file, String folderName) {
        try {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("folder", folderName);
            Map result = cloudinary.uploader().upload(file.getBytes(), map);
            String publicId = result.get("public_id").toString();
            return cloudinary.url().secure(true).generate(publicId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
