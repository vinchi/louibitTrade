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

    @Column(nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다. ROLE.USER, ROLE.ADMIN, ROLE.MANAGER등등

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
