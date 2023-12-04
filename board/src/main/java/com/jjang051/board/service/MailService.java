package com.jjang051.board.service;

import com.jjang051.board.dao.MemberDao;
import com.jjang051.board.dto.LoginDto;
import com.jjang051.board.dto.MailDto;
import com.jjang051.board.dto.UpdateDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;
    private final MemberDao memberDao;
    public void sendMail(MailDto mailDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailDto.getReceiver());
        simpleMailMessage.setFrom("kimhg1103@naver.com");
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getContent());
        javaMailSender.send(simpleMailMessage);
    }
    private String randomNumber;
    public void createRandomNumber(){
        randomNumber = ""+((int)(Math.random()*90000) +10000);
        log.info("randomNumber==={}",randomNumber);
    }
    public MimeMessage createMail(String mail){
         createRandomNumber();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setFrom("kimhg1103@naver.com");
            message.setRecipients(MimeMessage.RecipientType.TO,mail);
            message.setSubject("이메일 검증");
            String contnet="<h2>요청하신 인증번호 입니다.</h2>";
            contnet +="<h1>"+randomNumber+"</h1>";
            message.setText(contnet,"UTF-8","html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
    public String sendMailAuthEmail(String mail){
        MimeMessage message = createMail(mail);
        //db 비밀번호를 생성된 랜덤으로 나온  비밀번호를 암호화해서 넣어둔다. 즉 업데이트를 해야 한다.
        // 비밀번호 랜덤으로 생성된 비밀번호 서비스 만들고,
        // 그거를 암호화해서 db에 업데이트
       /* String newPassword =bCryptPasswordEncoder.encode("1234");
        Map<String,String> map = new HashMap<>();
        map.put("mail",mail);
        map.put("password",newPassword);
        memberDao.updatePassword(map);*/
        javaMailSender.send(message);
        return randomNumber;
    }

    @Transactional
    public void sendMailAndChangePassword(UpdateDto updateDto){
        String randomNum = sendMailAuthEmail(updateDto.getEmail());
        UpdateDto dbUpdateDto = UpdateDto.builder()
                .email(updateDto.getEmail())
                .password(bCryptPasswordEncoder.encode(randomNum))
                .build();
        memberDao.updatePassword(dbUpdateDto);
    }

   /* public int sendNewPassword(String mail){
        LoginDto loginDto = new LoginDto();
        MimeMessage message = createMail(mail);
        loginDto.setPassword(bCryptPasswordEncoder.encode("1234"));
        memberDao.updatePassword(loginDto);
        javaMailSender.send(message);
        return 0;
    }*/
}
