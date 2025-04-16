package red.social.interesescomunes.security.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.security.filters.JwtTokenValidator;
import red.social.interesescomunes.security.jwt.impl.JwtUtilsImpl;
import red.social.interesescomunes.security.model.Permission;
import red.social.interesescomunes.security.service.user.impl.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtilsImpl jwtUtils;
    private final UserDetailServiceImpl userDetailService;

    public SecurityConfig(JwtUtilsImpl jwtUtils, UserDetailServiceImpl userDetailService) {
        this.jwtUtils = jwtUtils;
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
        // 1. Rutas públicas
        .requestMatchers("/api/v1/auth/**").permitAll()
        .requestMatchers("/api/v1/role/**").permitAll()
        .requestMatchers("/api/v1/member/**").permitAll()
        .requestMatchers("/api/v1/admin/**").permitAll()
        .requestMatchers("/api/v1/category/**").permitAll()
        // 2. Rutas con roles específicos
        .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/delete/{id}").hasAnyAuthority(Permission.DELETE.name(),Permission.ALL.name())
        .requestMatchers(HttpMethod.POST, "/api/v1/group/create/{id}").hasAnyRole(TypeRole.MIEMBRO.name(), TypeRole.PROPIETARIO.name())
        .requestMatchers(HttpMethod.PUT,"/api/v1/group/update/{id}").hasAnyRole(TypeRole.PROPIETARIO.name())
      //  .requestMatchers("/api/v1/category/**").hasRole(TypeRole.ADMINISTRADOR.name())
        // 3. Catch-all: cualquier otra ruta
        .anyRequest().authenticated())
        .addFilterBefore(new JwtTokenValidator(this.jwtUtils), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                })
        );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
