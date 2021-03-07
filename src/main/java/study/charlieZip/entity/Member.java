package study.charlieZip.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
// 기본 생성자 막고 싶은데, JPA 스펙상 protected로 열어두어야 함.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    /**
     * GeneratedValue는 기본키를 자동생성해주는데 DB마다 전략이 달라 4가지 전략을 제공한다.
     * 나는 H2를 사용하므로 시퀀스를 이용해 기본 키를 제공하는 전략 IDENTITY를 선택했다.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;
    private String date;

    @Enumerated(EnumType.STRING)
    private Gender gender;  //성별 [MAN, FEMALE]

    @Embedded
    private Address address;

    @Builder
    public Member(String username, String password, String date, Gender gender, Address address) {
        this.username = username;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.address = address;
    }


    public Member(String username, String password, String date, Gender gender) {
        this.username = username;
        this.password = password;
        this.date = date;
        this.gender = gender;
    }
}
