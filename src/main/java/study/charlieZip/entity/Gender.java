package study.charlieZip.entity;

import lombok.Getter;

@Getter
public enum  Gender {
    MAN("남자"), FEMALE("여자");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
