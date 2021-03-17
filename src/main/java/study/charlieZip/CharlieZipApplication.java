package study.charlieZip;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import study.charlieZip.config.AuditorAwareImpl;

import javax.persistence.EntityManager;

@EnableJpaAuditing
@SpringBootApplication
public class CharlieZipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharlieZipApplication.class, args);
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

	/**
	 * JPA의 Audit 기능을 사용할 수 있도록 bean을 등록
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
