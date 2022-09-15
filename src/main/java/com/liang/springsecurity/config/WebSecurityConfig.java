package com.liang.springsecurity.config;

import com.liang.springsecurity.filter.JwtAuthenticationTokenFilter;
import com.liang.springsecurity.handler.RestAccessDeniedHandler;
import com.liang.springsecurity.handler.RestAuthenticationEntryPoint;
import com.liang.springsecurity.handler.RestAuthenticationFailureHandler;
import com.liang.springsecurity.handler.RestAuthenticationSuccessHandler;
import com.liang.springsecurity.handler.RestLogoutSuccessHandler;
import com.liang.springsecurity.provider.ThirdPartyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurity 配置类
 *
 * @author Liang
 * 2022-09-10
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestLogoutSuccessHandler restLogoutSuccessHandler;

    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private ThirdPartyAuthenticationProvider thirdPartyAuthenticationProvider;

    /**
     * 密码解析器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager 构造器
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider, thirdPartyAuthenticationProvider);
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return web -> {
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 关闭跨站请求伪造保护
        http.csrf().disable();
        // 关闭表单登录
        http.formLogin().disable();
        // 关闭基础认证方式
        http.httpBasic().disable();
        // X-Frame-Options HTTP 响应头是用来给浏览器指示允许一个页面
        // 可否在 <frame>, </iframe> 或者 <object>中展现的标记。
        // 网站可以使用此功能，来确保自己网站的内容没有被嵌套到别人的网站中去，
        // 也从而避免了点击劫持 (clickjacking) 的攻击。
        http.headers().frameOptions().disable();

        // 配置请求校验
        http.authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/thirdPartyLogin").permitAll()
                .anyRequest()
                .authenticated();

        // 关闭session
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 异常处理器
        http
                .exceptionHandling()
                // 登录后,访问没有权限处理类
                .accessDeniedHandler(restAccessDeniedHandler)
                // 匿名访问,没有权限的处理类
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        // 登出配置
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(restLogoutSuccessHandler)
                .permitAll();
        // 过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
