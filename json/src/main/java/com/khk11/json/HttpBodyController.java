package com.khk11.json;

import com.khk11.json.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //json으로 받으려면
@Slf4j
public class HttpBodyController {
    //get/post/put/delete

  //  public String xwwwformurlencoded(String name){
    @PostMapping("/body04")
    public  String applicationjsonToObject(@RequestBody Member member){
        log.info("넘어온 data==={}",member);
        log.info("member=={}",member);
        log.info("name=={}",member.getName());
        log.info("age=={}",member.getAge());
        return "application/json";
    }
}
