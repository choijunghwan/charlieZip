package study.charlieZip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.charlieZip.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
