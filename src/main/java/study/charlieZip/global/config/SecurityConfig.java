package study.charlieZip.global.config;

//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
/**
 * @EnableWebSecurity
 *  - @Configuration  클래스에 @EnableWebSecurity 어노테이션을 추가하여 Spring Security 설정할 클래스라고 정의한다.
 *  설정은 WebSecurityConfigurerAdapter 클래스를 상속받아 메서드를 구현하는것이 일반적인 방법이다.
 */
/*
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // WebSecurityConfigurerAdater는 WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스이다.

    private final MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과)
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/**").permitAll()
            .and() //로그인 설정
                .formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .permitAll()
            .and() //로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/member/denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
*/
