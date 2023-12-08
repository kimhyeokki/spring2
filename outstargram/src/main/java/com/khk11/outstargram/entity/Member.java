package com.khk11.outstargram.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@Table(name = "table_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Integer id;

    @Column(nullable = false,unique = true)
    private String userId;
    @Column(nullable = false)
    private String password;

    private String name;

    private String email;

   // private LocalDateTime createDate;

}
