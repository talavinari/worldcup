package worldcup.authentication;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomUserMapper  extends LdapUserDetailsMapper{
//    @Override
//    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
// Collection<? extends GrantedAuthority> authorities) {
//        UserDetails details = super.mapUserFromContext(ctx, username, authorities);
//        return new CustomLdapUserDetails((LdapUserDetails) details, env);
//    }


    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
                                          Collection<? extends GrantedAuthority> authorities) {
        UserDetails details = super.mapUserFromContext(ctx, username, authorities);
        return new CustomLdapUserDetails((LdapUserDetails) details);
    }
}
