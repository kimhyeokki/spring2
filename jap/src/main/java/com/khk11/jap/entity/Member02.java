package com.khk11.jap.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Setter  //엔티티는 setter는 만들지 않는다.
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // protect 빈생성자를 private로 만들지마라
@ToString
@Entity
@DynamicUpdate
public class Member02 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(length = 30,unique = true)
    private String userId;
    @Column(length = 100,nullable = true)
    private String password;
    @Column(length = 20,nullable = true)
    private String role;
    private String nickName;
    private String gender;
    private String email;
    private Integer age;
}
