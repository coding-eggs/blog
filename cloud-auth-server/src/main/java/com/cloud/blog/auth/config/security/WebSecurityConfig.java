package com.cloud.blog.auth.config.security;

import com.cloud.blog.auth.filter.QQAuthenticationFilter;
import com.cloud.blog.auth.handler.FuryAuthSuccessHandler;
import com.cloud.blog.auth.mapper.sys.BlogUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * @ClassName WebSecurityConfig
 * @Description security
 * @Author Ailuoli
 * @Date 2019/12/17 10:36
 **/

@Order(1)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private UserDetailsService baseUserDetailsService;

    @Autowired
    private BlogUserMapper blogUserMapper;


    @Value("${security.remember.time}")
    private Integer rememberTime;

    @Value("${security.oauth2.secret}")
    private String secret;


    /*
     自定义登录成功处理器
      */
    @Autowired
    private FuryAuthSuccessHandler furyAuthSuccessHandler;



    /*
配置TokenRepository
 */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder(secret);
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(baseUserDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    @Primary
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @Author myk
     * @Description //用户验证
     * @Date 10:05 2019/12/24
     * @Param [auth]
     * @return void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(baseUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 允许匿名访问所有接口 主要是 oauth 接口
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().csrf().disable()
                .authorizeRequests()

                .antMatchers("/security/login","/blog/user_info","/security/logout","/oauth/**","/actuator/**").permitAll()
                .antMatchers("/js/**","/html/**","/img/**","/font/**","/css/**","/layer/**").permitAll()
                .anyRequest()
                .authenticated()
//                .access("@securityAuthorityDecision.hasPermission(request,authentication)")
                .and()
                .addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage("/html/login.html")
                .loginProcessingUrl("/security/login")
                .and()
                .logout().logoutUrl("/security/logout")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                //两个星期
                .tokenValiditySeconds(rememberTime);
        http
                .headers().frameOptions().sameOrigin();

    }

    /**
     * 自定义 QQ登录 过滤器
     */
    private QQAuthenticationFilter qqAuthenticationFilter() {
        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/qqlogin/success");
        authenticationFilter.setAuthenticationSuccessHandler(furyAuthSuccessHandler);
        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager(blogUserMapper));
        return authenticationFilter;
    }

}
