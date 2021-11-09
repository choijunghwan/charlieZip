package study.charlieZip.domain.member.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberSaveFormTest {

    /**
     * 패턴 테스트
     * 정규표현식이 8자리를 제대로 구별하는지 테스트
     */
    @Test
    public void regex() {
        String pattern = "^[a-zA-z0-9]{8}$";

        assertFalse("1234567".matches(pattern));    //7자리
        assertTrue("12345678".matches(pattern));    //8자리
        assertFalse("123456789".matches(pattern));  //9자리
    }

}