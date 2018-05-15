package worldcup.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import java.util.Collection;

public class CustomLdapUserDetails implements LdapUserDetails{

    private LdapUserDetails details;

    public CustomLdapUserDetails(LdapUserDetails details) {
        this.details =details;
    }

    @Override
    public String getDn() {
        return details.getDn();
    }

    @Override
    public void eraseCredentials() {
        details.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return details.getAuthorities();
    }

    @Override
    public String getPassword() {
        return details.getPassword();
    }

    @Override
    public String getUsername() {
        return details.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return details.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return details.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return details.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return details.isEnabled();
    }
}
