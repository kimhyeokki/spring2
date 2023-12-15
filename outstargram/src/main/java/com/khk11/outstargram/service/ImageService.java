package com.khk11.outstargram.service;

import com.khk11.outstargram.dto.CustomUserDetails;
import com.khk11.outstargram.dto.ImageUploadDto;
import com.khk11.outstargram.entity.Image;
import com.khk11.outstargram.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    @Value("${file.path}")
    private String uploadFolder;
    public void upload(ImageUploadDto imageUploadDto, CustomUserDetails customUserDetails){
        String originalFileName = imageUploadDto.getFile().getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+originalFileName;
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        try {
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image image = imageUploadDto.toEntity(imageFileName,customUserDetails.getLoggedMember());
        imageRepository.save(image);
    }
    public Page<Image> loadStory(int customDetailsId, Pageable pageable){
        return imageRepository.loadStroy(customDetailsId,pageable);
    }
}
