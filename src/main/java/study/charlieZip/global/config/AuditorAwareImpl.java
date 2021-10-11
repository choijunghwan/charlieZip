package study.charlieZip.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import study.charlieZip.domain.member.entity.Member;
import study.charlieZip.global.common.GlobalConst;

import java.util.Optional;

/*@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Member findMember = (Member) servletRequestAttributes.getRequest().getSession().getAttribute(GlobalConst.LOGIN_MEMBER);
        log.info("Audit findMember {}", findMember);

        return Optional.ofNullable(findMember.getUsername());
    }
}*/
