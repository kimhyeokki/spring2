package com.khk11.outstargram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khk11.outstargram.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@Table(name="table_member")
@EntityListeners(AuditingEntityListener.class)
public class Member {
    // join되게 해보기....
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="member_id")
    private int id;

    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String mbti;

    private String description;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    private String profileImageUrl;

    @OneToMany(mappedBy = "member")
    @JsonIgnoreProperties({"member"})
    private List<Image> imageList;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;
}
