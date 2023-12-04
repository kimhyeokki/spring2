package com.khk11.jap.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Setter  //엔티티는 setter는 만들지 않는다.
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DynamicUpdate
public class Member02 {
    @Id
    @Column(length = 30,unique = true)
    private String userId;
    private String nickName;
    private String gender;
    private String email;
    private Integer age;
}
