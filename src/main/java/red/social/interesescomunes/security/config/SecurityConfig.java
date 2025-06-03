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
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.security.filters.JwtTokenValidator;
import red.social.interesescomunes.security.jwt.impl.JwtUtilsImpl;
import red.social.interesescomunes.security.model.Permission;
import red.social.interesescomunes.security.service.user.impl.UserDetailServiceImpl;

import java.util.Arrays;


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
                .cors(cors -> cors.configurationSource(this.corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    configurePublicRoutes(auth);
                    configureProtectedRoutes(auth);
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(this.jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                        )
                );
        return http.build();
    }


    private void configurePublicRoutes(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/member/**").permitAll()
                .requestMatchers("/api/v1/admin/**").permitAll()
                .requestMatchers("/api/v1/role/**").permitAll()
                .requestMatchers("/api/v1/user/**").permitAll();
    }

    private void configureProtectedRoutes(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth
                .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/delete/{id}")
                .hasAnyAuthority(Permission.DELETE.name(), Permission.ALL.name())
                .requestMatchers(HttpMethod.POST, "/api/v1/group/create/{id}")
                .hasAnyRole(TypeRole.MIEMBRO.name(), TypeRole.PROPIETARIO.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/group/update/{id}")
                .hasAnyRole(TypeRole.PROPIETARIO.name(), TypeRole.MIEMBRO.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/group/find-all")
                .hasAnyRole(TypeRole.MIEMBRO.name(),TypeRole.PROPIETARIO.name())
                .requestMatchers("api/v1/group-events/**")
                .hasAnyRole(TypeRole.PROPIETARIO.name())
                .requestMatchers("/api/v1/event-attendees/**")
                .hasAnyRole(TypeRole.MIEMBRO.name())
                .requestMatchers("/api/v1/member-group/**")
                .hasAnyRole(TypeRole.MIEMBRO.name())
                .requestMatchers("/api/v1/category/**")
                .hasAnyRole(TypeRole.ADMINISTRADOR.name());
    }

    // Configuración CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",  // Para desarrollo con Vite
                "http://127.0.0.1:5173",  // Alternativa localhost
                "https://tudominio.com"   // Para producción
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L); // Tiempo de cache para config CORS

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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