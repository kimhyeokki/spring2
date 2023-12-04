package com.khk11.isotope.service;


import com.khk11.isotope.dao.MemberJoinDao;
import com.khk11.isotope.dto.MemberJoin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MemberJoinService {
    private final MemberJoinDao memberJoinDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;
    public int insertMember(@ModelAttribute MemberJoin memberJoin){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMdd");
        String strNow = simpleDateFormat.format(now);
        String originalFile = memberJoin.getProfile().getOriginalFilename();
        String renamedFile = strNow+"_"+originalFile;
        File dir =new File(uploadFolder+File.separator+strNow);
        if(!dir.exists())dir.mkdir();

        Path path = Paths.get(dir+File.separator+renamedFile);
        memberJoin.setProfiles(strNow+"/"+renamedFile);
        MemberJoin insertMemberDto =  memberJoin.builder()
                        .id(memberJoin.getId())
                        .name(memberJoin.getName())
                        .email(memberJoin.getEmail())
                        .password(bCryptPasswordEncoder.encode(memberJoin.getPassword()))
                        .age(memberJoin.getAge())
                        .profiles(memberJoin.getProfiles())
                        .build();


        try {
            Files.write(path,memberJoin.getProfile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result =    memberJoinDao.insertMember(insertMemberDto);
        return result;
    }
}
