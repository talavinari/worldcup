package worldcup.Services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    @Value("${ldap.url}")
    private String url;

    @Value("${ldap.port}")
    private Integer port;

    @Value("${ldap.username}")
    private String managerUserName;

    @Value("${ldap.password}")
    private String password;

    @Value("${ldap.base}")
    private String baseContext;

    @Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl(url+":"+port);
        contextSource.setBase(baseContext);
        contextSource.setUserDn(managerUserName);
        contextSource.setPassword(password);
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}