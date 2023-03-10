package com.DanCreate.securityTesting.config;

import com.DanCreate.securityTesting.reponsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfigAuxiliary {
    private final UserRepository userRepository;
    /*
     * cach lay username tu UserRepository bang findByEmail voi parameter la username
     * de phong se nhay ra 1 exception nen chung se .orElseThrow(() -> new UsernameNotFoundException("User not found"));
     * */
    @Bean
    public UserDetailsService getUsernameByEmail(){
        return username -> userRepository
                .findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User does not exist or not found"));
    }
    /*
     * DaoAuthenticationProvider la 1 authenticationProvider trien khai su dung UserDetelsService and PasswordEncode
     * de xac thuc username va password
     * quy trinh : thuc hien filter tu UsernamePasswordAuthenticationToken -> AuthenticationManager de thuc hien ProviderManager
     * -> ProviderManager dinh cau hinh de su dung Authentication Providers(trinh cung cap xac thuc) thuoc loai DaoAuthenticationProvider
     * -> DaoAuthenticationProvider tra cuu UserDetails tu UserDetailsService -> su dung pwEncoder de xac thuc mat khau duoc userdetails tra ve o buoc truoc
     * -> khi thanh cong thi di nguoc dong lai tra ve usernamePasswordAuthenticationToken duoc dat tren SecurityContextHolder
     * duoc xac thuc boi filter
     * neu quen xem lai DaoAuth.png
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUsernameByEmail());
        authProvider.setPasswordEncoder(createPasswordEncoder());
        return authProvider;
    }
    /*
     * chiu trach nhiem quan ly xac thuc, co the co them cac AuthenticationProvider khac nua neu nhu ta setting
     * nhung o day em demo nen chi dung DaoAuthenticationProvider
     * */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    // ma hoa PasswordEncoder
    @Bean
    public PasswordEncoder createPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
