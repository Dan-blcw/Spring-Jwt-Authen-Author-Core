package com.DanCreate.securityTesting.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationFilter authenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;
        /*
        *.logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        */
        return http.build();
    }
    /*  #GIAI THICH CHI TIET CODE :: written by Huong(alias :: Dan)
    * .csrf().disable();  chan tat cac cac request duoc gui den va yeu cau su dụng token de xac thuc. Khi mot request đuoc submit, server phai kiem tra token nay, khong đung => fail
                        Bang cach tranh viec long random token vào HTTP GET, ta co the ngan viec token bi lo ra ngoai
                        doc them https://viblo.asia/p/tim-hieu-ve-ky-thuat-tan-cong-csrf-va-cach-xu-ly-trong-spring-boot-gDVK2jPwKLj

    * .authorizeHttpRequests()// uy quyen HttpRequests

    * .requestMatchers("/api/v1/auth/**")
      .permitAll() :  cho phep tat ca request o tren vi khong co logic nghiep vu va endpoint trong qua trinh xac thuc nen la vie nay oke

    * .anyRequest() : voi 1 vai yeu cau khac thi yeu cau xac thuc voi authenticated()
      .authenticated()

    * .and() :chung ta khong nen luu authenState(trang thai thuc) or sessionSate(trang thai phien)
             dam bao rang moi yeu cau deu duoc xac thuc
      .sessionManagement() : chinh sach quan ly phien
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS): chinh sach tao phien de o trang thai statelest(khong trang thai)

    * .and() : noi voi spring biet rang authenticationProvider(nha cung cap dich vu xac thuc) nao ma chung ta muon
            //de hieu them ve authenticationProvider dan cung cap thi sang ApplicationConfig(co giai thich)
      .authenticationProvider(authenticationProvider)//object ta se tao(cu the o day la DaoAuthenticationProvider)
            //su dung addFilterBefore voi muc dich muon thuc thi bo loc xac thuc JWT truoc sau do set va update
            //securitycontextholder (neu quen thi xem lai JwtAuthenticationFilter)
            //voi 2 parameter : dau tien la jwtAuthFilter(giai thich o dong tren), va
            //UsernamePasswordAuthenticationFilter.class voi muc dich xac thuc bo loc authen usn va pw
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

    *.logout() : cau hinh logoutConfigurer
     .logoutUrl("/api/v1/auth/logout") : address api cho logout
     .addLogoutHandler(logoutHandler): them logoutHandler - xu ly logout voi parameter ta chuyen vao duoc viet boi spring security
     .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())

    */
}
