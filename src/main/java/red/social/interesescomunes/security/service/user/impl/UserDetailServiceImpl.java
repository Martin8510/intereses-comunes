package red.social.interesescomunes.security.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


import red.social.interesescomunes.administrator.application.output.IAdministratorPersistencePort;
import red.social.interesescomunes.administrator.domain.model.Administrator;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;
import red.social.interesescomunes.member.application.output.IMemberPersistencePort;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.owner.application.output.IOwnerPersistencePort;
import red.social.interesescomunes.owner.domain.model.Owner;
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
@RequiredArgsConstructor
public class UserDetailServiceImpl implements IAuthSecurityService {
   private final JwtUtilsImpl jwtUtils;
   private final BCryptPasswordVerificationService verificationPasswordService;
   private final IUserServicePort userService;
   private final IUserPersistenceMapper mapper;
   private final IMemberPersistencePort memberPersistencePort;
   private final IOwnerPersistencePort ownerPersistencePort;
   private final IAdministratorPersistencePort administratorPersistencePort;
   private static final boolean ACCOUNT_ENABLED = true;

    @Override
    public AuthUserResponse authenticateAndGenerateToken(AuthUserRequest request) throws AuthenticationException {
         // Autenticar al usuario
        Authentication authentication = this.verifyCredentials(request.getUserName(), request.getPassword());
        // Establecer el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generar el token JWT
        String accessToken = this.jwtUtils.generateJwtToken(authentication);
        UserEntity userEntity = this.getUserByIdentifier(request.getUserName());

        // Consultar roles del usuario
        Long userId = userEntity.getId();
        Long memberId = memberPersistencePort.findByUserId(userId).map(Member::getId).orElse(null);
        Long ownerId = ownerPersistencePort.findByUserId(userId).map(Owner::getId).orElse(null);
        Long adminId = administratorPersistencePort.findByUserId(userId).map(Administrator::getId).orElse(null);
        // Generar y obtener token
        String accesToken = this.jwtUtils.generateJwtToken(authentication);
        // Devolvemos un objeto AuthUserResponse
        return AuthUserResponse.builder()
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .token(accesToken)
                .msg("Usuario autenticado exitosamente")
                .idUser(userId)
                .idMember(memberId)
                .idOwner(ownerId)
                .idAdmin(adminId)
                .build();
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
        // Consultar roles del usuario
        Long userId = userEntity.getId();
        Long memberId = memberPersistencePort.findByUserId(userId).map(Member::getId).orElse(null);
        Long ownerId = ownerPersistencePort.findByUserId(userId).map(Owner::getId).orElse(null);
        Long adminId = administratorPersistencePort.findByUserId(userId).map(Administrator::getId).orElse(null);
        // Generar y obtener token
        String accesToken = this.jwtUtils.generateJwtToken(authentication);
        // Devolvemos un objeto AuthUserResponse
        return AuthUserResponse.builder()
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .token(accesToken)
                .msg("Usuario registrado exitosamente")
                .idUser(userId)
                .idMember(memberId)
                .idOwner(ownerId)
                .idAdmin(adminId)
                .build();
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
