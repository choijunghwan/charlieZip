package study.charlieZip.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/*public class AuditorAwareImpl implements AuditorAware<String> {

    *//**
     * Securitycontext 에서 인증정보를 가져와 주입시킨다.
     *//*
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
            return null;
        }

        return Optional.of(authentication.getName());
    }
}*/
