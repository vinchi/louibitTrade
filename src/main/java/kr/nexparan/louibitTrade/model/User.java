package kr.nexparan.louibitTrade.model;

import com.sun.prism.PixelFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//ORM -> JAVA(다른언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User클래스가 Mariadb에 테이블이 생성된다.
public class User {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private Long id;

    @Column
    private String username;
    @Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private boolean enabled;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "User_Role",
            joinColumns=@JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private List<Role> roles = new ArrayList<>(); //ROLE.USER, ROLE.ADMIN, ROLE.MANAGER

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
