package study.charlieZip.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private String description;

    Role(String description) {
        this.description = description;
    }
}
