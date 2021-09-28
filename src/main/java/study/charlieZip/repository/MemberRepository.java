package study.charlieZip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.charlieZip.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);
}
