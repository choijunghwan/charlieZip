package study.charlieZip.domain.member.entity;

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
     * 나는 H2를 사용하므로 시퀀스를 이용해 기본 키를 제공하는 전략인 SEQUENCE를 사용할려고 했으나
     * 내가 원하는건 각 테이블의 PK값이 각각 1부터 상승하는거였는데 SEQUENCE는 모든 테이블을 통합해서 숫자가 상승
     * 그래서 기본키 전략을 IDENTITY를 선택해 각테이블이 독립되게 PK값이 증가하게 설정하였다.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
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
