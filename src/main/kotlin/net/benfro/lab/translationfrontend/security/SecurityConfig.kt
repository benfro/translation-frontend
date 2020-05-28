package net.benfro.lab.translationfrontend.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        auth!!.inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("password"))
                .roles("USER").and()
                .withUser("admin")
                .password(encoder.encode("password"))
                .roles("USER", "ADMIN")
    }
}