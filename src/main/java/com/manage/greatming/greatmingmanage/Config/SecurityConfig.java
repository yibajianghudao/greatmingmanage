package com.manage.greatming.greatmingmanage.Config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfiguration {
    
}
