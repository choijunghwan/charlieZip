package study.charlieZip.dto;

import lombok.Getter;
import lombok.Setter;
import study.charlieZip.entity.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class MemberForm {

    //NotEmpty는 null과 ""을 허용하지 않는다.
    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String username;

    //NotBlank는 null과 ""와 " "(빈공백문자열)을 허용하지 않는다.
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @Pattern(regexp = "^[a-zA-z0-9]{8}$", message = "8자리로 입력해주세요.")
    private String date;

    private Gender gender;

}
