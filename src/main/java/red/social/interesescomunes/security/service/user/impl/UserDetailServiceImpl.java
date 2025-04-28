package red.social.interesescomunes.security.service.user.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;

import red.social.interesescomunes.security.jwt.impl.JwtUtilsImpl;
import red.social.interesescomunes.security.service.api.IAuthSecurityService;
import red.social.interesescomunes.security.service.password.BCryptPasswordVerificationService;
import red.social.interesescomunes.user.application.input.IUserServicePort;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;
import red.social.interesescomunes.user.infrastructure.output.persistence.mapper.IUserPersistenceMapper;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.entity.UserEntity;


import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailServiceImpl implements IAuthSecurityService {
   private JwtUtilsImpl jwtUtils;
   private final BCryptPasswordVerificationService verificationPasswordService;
   private final IUserServicePort userService;
   private final IUserPersistenceMapper mapper;
   private static final boolean ACCOUNT_ENABLED = true;

    public UserDetailServiceImpl(BCryptPasswordVerificationService verificationPasswordService,
                                 IUserServicePort userService, IUserPersistenceMapper mapper,
                                 JwtUtilsImpl jwtUtils) {
        this.verificationPasswordService = verificationPasswordService;
        this.userService = userService;
        this.mapper = mapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public AuthUserResponse authenticateAndGenerateToken(AuthUserRequest request) throws AuthenticationException {
         // Autenticar al usuario
        Authentication authentication = this.verifyCredentials(request.getUserName(), request.getPassword());
        // Establecer el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generar el token JWT
        String accessToken = this.jwtUtils.generateJwtToken(authentication);
        UserEntity user = this.getUserByIdentifier(request.getUserName());
        // Retornar la respuesta
        return new AuthUserResponse(
                user.getUserName(),
                user.getPassword(),
                accessToken,
                "Usuario autenticado exitosamente"
            );
    }

    @Override
    public Authentication verifyCredentials(String userName, String password) throws BadCredentialsException {
        UserDetails userDetails = this.loadUserByUsername(userName);
        boolean isPasswordValid = this.verificationPasswordService.validatePassword(password, userDetails.getPassword());

        if (!isPasswordValid ) {
            throw new BadCredentialsException("Contraseña inválida.");
        }

        Authentication authenticate = new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(),
            userDetails.getPassword(),
            userDetails.getAuthorities()
        );
        return authenticate;
    }

    @Override
    public UserDetails getUserDetail(UserEntity userEntity ){
        Collection<? extends GrantedAuthority> authorities = this.convertToGrantedAuthorities(userEntity);
        return new User(
                  userEntity.getUserName(),
                  userEntity.getPassword(),
                  ACCOUNT_ENABLED,
                  userEntity.isAccountNoExpired(),
                  userEntity.isAccountNoExpired(),
                  userEntity.isAccountNoLocked(),
                  authorities
          );
    }

    @Override
    public UserEntity getUserByIdentifier(String userName) throws UserNotFoundException {
      return this.mapper.toEntity(this.userService.findUserByUserName(userName)) ;
    }

    @Override
    public AuthUserResponse generatePostRegistrationToken(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        UserEntity userEntity = this.getUserByIdentifier(userName);
        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())));
        });

        userEntity.getRoles().stream()
                .flatMap( role -> role.getPermits().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.name())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity.getUserName(),
                userEntity.getPassword(), authorityList);

        String accesToken = this.jwtUtils.generateJwtToken(authentication);

        // Retornar la respuesta
        return new AuthUserResponse(
                userEntity.getUserName(),
                userEntity.getPassword(),
                accesToken,
                "Usuario registrado exitosamente"
        );
    }

    @Override
    public List<SimpleGrantedAuthority> convertToGrantedAuthorities(UserEntity user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // Agregar roles con prefijo "ROLE_"
        user.getRoles().forEach(role ->
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()))
        );
        // Agregar permisos
        user.getRoles().stream()
            .flatMap(role -> role.getPermits().stream())
            .forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.name()))
            );
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.getUserByIdentifier(username);
        return this.getUserDetail(userEntity);
    }
}
