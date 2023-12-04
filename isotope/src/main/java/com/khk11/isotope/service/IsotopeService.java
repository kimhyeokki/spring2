package com.khk11.isotope.service;

import com.khk11.isotope.dao.IsotopeDao;
import com.khk11.isotope.dto.IsotopeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IsotopeService {
    private final IsotopeDao isotopeDao;

    @Value("${file.path}")
    private String uploadFolder;

    public int insertIsotope(IsotopeDto isotopeDto){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String strNow = simpleDateFormat.format(now);
        String originalFile = isotopeDto.getFile().getOriginalFilename();
        String renamedFile = strNow+"_"+originalFile;

        File dir = new File(uploadFolder+File.separator+strNow);
        if(!dir.exists())dir.mkdir();

        Path path = Paths.get(dir+File.separator+renamedFile);

        isotopeDto.setOriginal(originalFile);
        isotopeDto.setRenamed(strNow+"/"+renamedFile);
        try {
            Files.write(path,isotopeDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int result = isotopeDao.insertIsotope(isotopeDto);
        return result;
    }
    public List<IsotopeDto> getAllList(){
        List<IsotopeDto> isotopeList = isotopeDao.getAllList();
        return isotopeList;
    }
}
