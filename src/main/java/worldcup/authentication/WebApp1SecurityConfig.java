package worldcup.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@ConfigurationProperties
@Order(1)
public class WebApp1SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ldap.url}")
    private String url;

    @Value("${ldap.port}")
    private Integer port;

    @Value("${ldap.username}")
    private String managerUserName;

    @Value("${ldap.password}")
    private String password;

    @Value("${ldap.user.search.base}")
    private String userSearchBase;

    @Value("${ldap.group.search.base}")
    private String groupSearchBase;

    @Value("${ldap.search.filter}")
    private String searchFilter;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .ldapAuthentication()
        .userSearchFilter(searchFilter)
        .userSearchBase(userSearchBase)
        .groupSearchBase(groupSearchBase)
        .contextSource()
        .url(url).port(port)
        .managerDn(managerUserName)
        .managerPassword(password);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().httpBasic().and().csrf().disable();
    }

}