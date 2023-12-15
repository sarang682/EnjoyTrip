package com.ssafy.enjoytrip.config.auth;

import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

    private String id;
    private String name;
    private String emailId;
    private String emailDomain;
    private Role role;

    public static PrincipalDetails fromEntity(Member entity) {
        return new PrincipalDetails(
                entity.getId(),
                entity.getName(),
                entity.getEmailId(),
                entity.getEmailDomain(),
                entity.getRole()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
