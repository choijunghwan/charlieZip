package study.charlieZip.dto;

import lombok.Getter;
import lombok.Setter;
import study.charlieZip.entity.Gender;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String username;

    private String password;
    private String date;
    private Gender gender;

}
