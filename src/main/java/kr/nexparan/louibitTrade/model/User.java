package kr.nexparan.louibitTrade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//ORM -> JAVA(다른언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User클래스가 Mariadb에 테이블이 생성된다.
@NamedEntityGraph(name = "User.fetchBoard", attributeNodes = @NamedAttributeNode("boards"))
public class User {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private boolean enabled;

    @ColumnDefault("0")
    private BigDecimal point;

    @Builder.Default
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "User_Role",
            joinColumns=@JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private List<Role> roles = new ArrayList<>(); //ROLE_USER, ROLE_ADMIN, ROLE_MANAGER

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
